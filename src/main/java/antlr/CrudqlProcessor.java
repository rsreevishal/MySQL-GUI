package antlr;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import crud.antlr.AntlrTableRecordCrud;
import expression.AddExpr;
import expression.CrudqlErrorListener;
import expression.DeleteExpr;
import expression.Expression;
import expression.ParseTreeToProgram;
import expression.Program;
import expression.UpdateExpr;
import expression.ViewAllExpr;
import expression.ViewExpr;

public class CrudqlProcessor {
	public static String process(String expression) {
		CrudqlParser parser = getParser(expression);
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
				for(Expression e: prog.expressions) {
					if(e instanceof AddExpr) {
						System.out.println("Adding values");
						return antlrRecordCrud.create((AddExpr)e);
					} else if (e instanceof UpdateExpr) {
						System.out.println("Updating values");
						return antlrRecordCrud.update((UpdateExpr)e);
					} else if (e instanceof DeleteExpr) {
						System.out.println("Deleting values");
						return antlrRecordCrud.delete((DeleteExpr)e);
					} else if (e instanceof ViewExpr) {
						System.out.println("Viewing values");
						return antlrRecordCrud.view((ViewExpr)e);
					} else if (e instanceof ViewAllExpr) {
						System.out.println("Viewing all values");
						return antlrRecordCrud.viewAll((ViewAllExpr)e);
					}
				}
			}
			return "Error occured";
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
