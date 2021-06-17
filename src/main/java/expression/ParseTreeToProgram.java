package expression;

import java.util.ArrayList;
import java.util.HashMap;

import antlr.CrudqlBaseVisitor;
import antlr.CrudqlParser.ProgContext;
import crud.TableCrud;
import model.Field;
import model.Pair;
import model.Table;
import model.User;

public class ParseTreeToProgram extends CrudqlBaseVisitor<Program> {
	public ArrayList<String> semanticErrors;
	public HashMap<String, String> vars;
	public ArrayList<Table> tables;
	private ArrayList<Pair<String, String>> linkedTables;
	private TableCrud tableCrud;
	private boolean createNew;
	public User user;
	public ParseTreeToProgram(User _user, boolean _createNew) {
		semanticErrors = new ArrayList<String>();
		vars = new HashMap<String, String>();
		tableCrud = new TableCrud();
		user = _user;
		tables = tableCrud.getAll(user);
		createNew = _createNew;
		linkedTables = new ArrayList<Pair<String, String>>();
	}
	
	@Override
	public Program visitProg(ProgContext ctx) {
		Program program = new Program();
		ParseTreeToExpression exprVisitor = new ParseTreeToExpression(semanticErrors, vars, tables, createNew, linkedTables, user);
		for(int i=0; i<ctx.getChildCount(); i++) {
			if(i == ctx.getChildCount() - 1) {
				// EOF
				continue;
			} else {
				program.addExpression(exprVisitor.visit(ctx.getChild(i)));
			}
		}
		HashMap<String, ArrayList<String>> tables = new HashMap<String, ArrayList<String>>();
		for(Expression e: program.expressions) {
			if(e instanceof FormExpr) {
				ArrayList<String> fields = new ArrayList<String>();
				FormExpr expr = (FormExpr)e;
				for(FormInputExpr fie: expr.formInputs) {
					fields.add(fie.idToken.id);
				}
				tables.putIfAbsent(expr.idToken.id, fields);
			}
		}
		for(Table table: tableCrud.getAll(user)) {
			ArrayList<String> fields = new ArrayList<String>();
			for(Field f: table.getFields()) {
				fields.add(f.getName());
			}
			tables.putIfAbsent(table.getName(), fields);
		}
		for(Pair<String, String> table: linkedTables) {
			if(!tables.containsKey(table.getKey())) {
				semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> doesn't contain in the database</p>", table.getKey()));
				break;
			} else {
				if(!tables.get(table.getKey()).contains(table.getValue())) {
					semanticErrors.add(String.format("<p>Field <span style='color:red;'>%s</span> doesn't contain in the %s</p>", table.getValue(), table.getKey()));
					break;
				}
			}
		}
		return program;
	}

}
