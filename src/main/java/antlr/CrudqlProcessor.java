package antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import crud.antlr.AntlrTableRecordCrud;
import expression.*;
import model.User;

public class CrudqlProcessor {
	public static String process(Program prog, User user, boolean createNew) {
		AntlrTableRecordCrud antlrRecordCrud = new AntlrTableRecordCrud(user);
		String result = "";
		int counter = 0;
		for (Expression e : prog.expressions) {
			counter += 1;
			if (e instanceof FormExpr) {
				result += String.format(
						"<li class='list-group-item tab2'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>%s</button>",
						counter, ((FormExpr) e).idToken.id);
			} else if (e instanceof FormReportExpr) {
				result += String.format(
						"<li class='list-group-item tab2'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>%s</button>",
						counter, ((FormReportExpr) e).name.id);
			} else {
				result += String.format(
						"<li class='list-group-item tab3'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>",
						counter, counter);
			}
			if (e instanceof AddExpr) {

				System.out.println("Adding values");
				AddExpr expr = (AddExpr) e;
				for (int i = 0; i < expr.listToken.values.size(); i++) {
					String val = expr.listToken.values.get(i);
					String valContent = val.substring(1, val.length() - 1);
					if (valContent.charAt(0) == '$' && prog.vars.containsKey(valContent)) {
						System.out.println("Yes addexpr has variable: " + valContent);
						expr.listToken.values.set(i, "'" + prog.vars.get(valContent) + "'");
					}
				}
				String output = antlrRecordCrud.create(expr);
				result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof UpdateExpr) {

				System.out.println("Updating values");
				UpdateExpr expr = (UpdateExpr) e;
				for (int i = 0; i < expr.uListToken.values.size(); i++) {
					String val = expr.uListToken.values.get(i);
					String valContent = val.substring(1, val.length() - 1);
					if (valContent.charAt(0) == '$' && prog.vars.containsKey(valContent)) {
						System.out.println("Yes addexpr has variable: " + valContent);
						expr.uListToken.values.set(i, "'" + prog.vars.get(valContent) + "'");
					}
				}
				String output = antlrRecordCrud.update((UpdateExpr) e);
				result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof DeleteExpr) {

				System.out.println("Deleting values");
				String output = antlrRecordCrud.delete((DeleteExpr) e);
				result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof ViewExpr) {

				System.out.println("Viewing value");
				String output = antlrRecordCrud.view((ViewExpr) e);
				result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof ViewAllExpr) {

				System.out.println("Viewing all values");
				String output = antlrRecordCrud.viewAll((ViewAllExpr) e);
				result += String.format("<div class='collapse' id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof ColViewExpr) {

				System.out.println("Viewing column value");
				String output = antlrRecordCrud.view((ColViewExpr) e);
				result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof ColViewAllExpr) {

				System.out.println("Viewing all column values");
				String output = antlrRecordCrud.viewAll((ColViewAllExpr) e);
				result += String.format("<div class='collapse' id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof StoreColViewExpr) {

				System.out.println("Storing column value");
				StoreColViewExpr expr = (StoreColViewExpr) e;
				String value = antlrRecordCrud.getValue(expr);
				prog.vars.replace(expr.varToken.var, value);
				String output = "<p style='color: green;'>Value stored</p>";
				result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof FormExpr) {

				System.out.println("Creating form");
				FormExpr expr = (FormExpr) e;
				String output = antlrRecordCrud.createFormTable(expr, createNew);
				result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);

			} else if (e instanceof FormReportExpr) {

				System.out.println("Creating form report");
				FormReportExpr expr = (FormReportExpr) e;
				String output = antlrRecordCrud.createReport(expr, createNew);
				result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);

			}
		}
		return String.format("<li class='list-group'>%s</li>", result);

	}

	public static Program getParseTree(String expression, User user, boolean createNew) {
		CrudqlParser parser = null;
		CharStream input = CharStreams.fromString(expression);
		CrudqlLexer lexer = new CrudqlLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new CrudqlParser(tokens);
		// syntax error handling
		parser.removeErrorListeners();
		parser.addErrorListener(new CrudqlErrorListener());
		ParseTree antlrAST = parser.prog();
		if (CrudqlErrorListener.hasError) {
			CrudqlErrorListener.hasError = false;
			return null;
		}
		ParseTreeToProgram progVisitor = new ParseTreeToProgram(user, createNew);
		Program prog = progVisitor.visit(antlrAST);
		prog.vars = progVisitor.vars;
		return prog;
	}
}
