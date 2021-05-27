package expression;

import java.util.ArrayList;

import antlr.CrudqlBaseVisitor;
import antlr.CrudqlParser.ProgContext;
import crud.TableCrud;
import model.Table;

public class ParseTreeToProgram extends CrudqlBaseVisitor<Program> {
	public ArrayList<String> semanticErrors;
	public ArrayList<Table> tables;
	private TableCrud tableCrud;
	
	public ParseTreeToProgram() {
		semanticErrors = new ArrayList<String>();
		tableCrud = new TableCrud();
		tables = tableCrud.getAll();
	}
	
	@Override
	public Program visitProg(ProgContext ctx) {
		Program program = new Program();
		ParseTreeToExpression exprVisitor = new ParseTreeToExpression(semanticErrors, tables);
		for(int i=0; i<ctx.getChildCount(); i++) {
			if(i == ctx.getChildCount() - 1) {
				// EOF
				continue;
			} else {
				program.addExpression(exprVisitor.visit(ctx.getChild(i)));
			}
		}
		return program;
	}

}
