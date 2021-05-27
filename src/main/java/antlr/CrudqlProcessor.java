package antlr;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import crud.antlr.AntlrTableRecordCrud;
import expression.*;

public class CrudqlProcessor {
	public static String process(String expressions) {
		CrudqlParser parser = getParser(expressions);
		AntlrTableRecordCrud antlrRecordCrud = new AntlrTableRecordCrud();
		// Tell ANTLR to build a parse tree
		// parse from the start symbol 'prog'
		ParseTree antlrAST = parser.prog();
		if(CrudqlErrorListener.hasError) {
			// let the syntax error be reported
			CrudqlErrorListener.hasError = false;
			return CrudqlErrorListener.errorMsg;
		} else {
			// Create a visitor for converting the parse tree into Program/Expression object
			ParseTreeToProgram progVisitor = new ParseTreeToProgram();
			Program prog = progVisitor.visit(antlrAST);
			if(progVisitor.semanticErrors.size() > 0) {
				String errors = "";
				for(String error: progVisitor.semanticErrors) {
					errors += (error + "<br/>");
				}
				return errors;
			} else {
				String result = "";
				int counter = 0;
				for(Expression e: prog.expressions) {
					counter += 1;
					if(e instanceof AddExpr) {
						
						System.out.println("Adding values");
						result += String.format("<li class='list-group-item'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>", counter, counter);
						AddExpr expr = (AddExpr)e;
						for(int i=0; i<expr.listToken.values.size(); i++) {
							String val = expr.listToken.values.get(i);
							String valContent = val.substring(1, val.length()-1);
							if(valContent.charAt(0) == '$' && progVisitor.vars.containsKey(valContent)) {
								expr.listToken.values.set(i, "'" + progVisitor.vars.get(valContent) + "'");
							}
						}
						String output = antlrRecordCrud.create(expr);
						result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);
						
					} else if (e instanceof UpdateExpr) {
						
						System.out.println("Updating values");
						result += String.format("<li class='list-group-item'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>", counter, counter);
						String output = antlrRecordCrud.update((UpdateExpr)e);
						result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);
						
					} else if (e instanceof DeleteExpr) {
						
						System.out.println("Deleting values");
						result += String.format("<li class='list-group-item'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>", counter, counter);
						String output = antlrRecordCrud.delete((DeleteExpr)e);
						result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);
						
					} else if (e instanceof ViewExpr) {
						
						System.out.println("Viewing value");
						result += String.format("<li class='list-group-item'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>", counter, counter);
						String output = antlrRecordCrud.view((ViewExpr)e);
						result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);
						
					} else if (e instanceof ViewAllExpr) {
						
						System.out.println("Viewing all values");
						result += String.format("<li class='list-group-item'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>", counter, counter);
						String output = antlrRecordCrud.viewAll((ViewAllExpr)e);
						result += String.format("<div class='collapse' id='%s'>%s</div>", "result_" + counter, output);
						
					} else if (e instanceof ColViewExpr) {
						
						System.out.println("Viewing column value");
						result += String.format("<li class='list-group-item'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>", counter, counter);
						String output = antlrRecordCrud.view((ColViewExpr)e);
						result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);
						
					} else if (e instanceof ColViewAllExpr) {
						
						System.out.println("Viewing all column values");
						result += String.format("<li class='list-group-item'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>", counter, counter);
						String output = antlrRecordCrud.viewAll((ColViewAllExpr)e);
						result += String.format("<div class='collapse' id='%s'>%s</div>", "result_" + counter, output);
						
					} else if (e instanceof StoreColViewExpr) {
						
						StoreColViewExpr expr = (StoreColViewExpr)e;
						String value = antlrRecordCrud.getValue(expr);
						progVisitor.vars.replace(expr.varToken.var, value);
						System.out.println("Storing column value");
						result += String.format("<li class='list-group-item'><button data-toggle='collapse' data-target='#result_%d' class='btn btn-warning'>Result %d</button>", counter, counter);
						String output = "<p style='color: green;'>Value stored</p>";
						result += String.format("<div id='%s'>%s</div>", "result_" + counter, output);
						
					}
				}
				return String.format("<li class='list-group'>%s</li>", result);
			}
		}
	}
	private static CrudqlParser getParser(String expression) {
		CrudqlParser parser = null;
		CharStream input = CharStreams.fromString(expression);
		CrudqlLexer lexer = new CrudqlLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new CrudqlParser(tokens);
		// syntax error handling
		parser.removeErrorListeners();
		parser.addErrorListener(new CrudqlErrorListener());
		return parser;
	}
}
