package exception_handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import expression.AddExpr;
import expression.ColViewAllExpr;
import expression.ColViewExpr;
import expression.ConditionExpr;
import expression.DeleteExpr;
import expression.Expression;
import expression.FormExpr;
import expression.FormInputExpr;
import expression.FormReportExpr;
import expression.IdToken;
import expression.InputType;
import expression.StoreColViewExpr;
import expression.UpdateExpr;
import expression.ViewAllExpr;
import expression.ViewExpr;
import model.Field;
import model.Pair;
import model.Table;

public class SemanticExceptionHandler {
	public ArrayList<String> semanticErrors;
	private HashMap<String, String> vars;
	private ArrayList<String> varsCheck;
	private ArrayList<Table> tablesDB;
	private ArrayList<String> tables;
	private ArrayList<Pair<String, String>> linkedTables;
	private boolean createNew;
	public SemanticExceptionHandler(ArrayList<Table> _tablesDB, HashMap<String, String> _vars, boolean _createNew) {
		semanticErrors = new ArrayList<String>();
		linkedTables = new ArrayList<Pair<String, String>>();
		tables = new ArrayList<String>();
		varsCheck =  new ArrayList<String>();
		vars = _vars;
		tablesDB = _tablesDB;
		createNew = _createNew;
	}
	
	public void handle(List<Expression> expressions) {
		for(Expression expression: expressions) {
			if(expression instanceof FormExpr) {
				Table table = containTable(((FormExpr) expression).idToken);
				if ((table != null && this.createNew) || (tables.contains(((FormExpr) expression).idToken.id))) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> already exists in the database!</p>", ((FormExpr) expression).idToken.id));
				} else {
					tables.add(((FormExpr) expression).idToken.id);
				}
				for(FormInputExpr fie: ((FormExpr) expression).formInputs) {
					if(fie.inputType == InputType.LINK) {
						linkedTables.add(new Pair<String, String>(fie.args.values.get(0), fie.args.values.get(1)));
					}
				}
			}
			if(expression instanceof FormReportExpr) {
				Table table = containTable(((FormReportExpr) expression).table);
				if (table == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> doesn't exist in the database!</p>", ((FormReportExpr) expression).table.id));
				} else {
					for(ConditionExpr cExpr: ((FormReportExpr) expression).conditions) {
						if (!containColumn(table, cExpr.colName)) {
							semanticErrors
									.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>",
											cExpr.colName.id, table.getName()));
						}
					}
				}
			}
			if(expression instanceof ColViewExpr) {
				Table table = containTable(((ColViewExpr) expression).idToken);
				if (table == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", ((ColViewExpr) expression).idToken.id));
				} else {
					if (!containColumn(table, ((ColViewExpr) expression).colToken)) {
						semanticErrors
								.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>",
										((ColViewExpr) expression).colToken.id, table.getName()));
					}
				}
			}
			if(expression instanceof ColViewAllExpr) {
				Table table = containTable(((ColViewAllExpr) expression).idToken);
				if (table == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", ((ColViewAllExpr) expression).idToken.id));
				} else {
					if (!containColumn(table, ((ColViewAllExpr) expression).colToken)) {
						semanticErrors
								.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>",
										((ColViewAllExpr) expression).colToken.id, table.getName()));
					}
				}
			}
			if(expression instanceof StoreColViewExpr) {
				Table table = containTable(((StoreColViewExpr) expression).colViewExpr.idToken);
				if (table == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", ((StoreColViewExpr) expression).colViewExpr.idToken.id));
				} else {
					if (!containColumn(table, ((StoreColViewExpr) expression).colViewExpr.colToken)) {
						semanticErrors
								.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>",
										((StoreColViewExpr) expression).colViewExpr.colToken.id, table.getName()));
					}
				}
				if (this.varsCheck.contains(((StoreColViewExpr) expression).varToken.var)) {
					semanticErrors
							.add(String.format("<p><span style='color:red;'>%s</span> already declared!</p>", ((StoreColViewExpr) expression).varToken.var));
				} else {
					this.varsCheck.add(((StoreColViewExpr) expression).varToken.var);
				}
			}
			if(expression instanceof AddExpr) {
				if (containTable(((AddExpr) expression).idToken) == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", ((AddExpr) expression).idToken.id));
				}
				for(String val: ((AddExpr) expression).listToken.values) {
					val = val.substring(1, val.length() -1 );
					if(val.length() > 0) {
						if (val.charAt(0) == '$' && !this.vars.containsKey(val)) {
							semanticErrors
									.add(String.format("<p><span style='color:red;'>%s</span> haven't declared!</p>", val));
						}
					}
				}
			}
			if(expression instanceof UpdateExpr) {
				if (containTable(((UpdateExpr) expression).idToken) == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", ((UpdateExpr) expression).idToken.id));
				}
				for(String val: ((UpdateExpr) expression).uListToken.values) {
					System.out.println("UpdateExpr errhandle: " + val);
					val = val.substring(1, val.length() -1 );
					if(val.length() > 0) {
						if (val.charAt(0) == '$' && !this.vars.containsKey(val)) {
							semanticErrors
									.add(String.format("<p><span style='color:red;'>%s</span> haven't declared!</p>", val));
						}
					}
				}
			}
			if(expression instanceof DeleteExpr) {
				if (containTable(((DeleteExpr) expression).idToken) == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", ((DeleteExpr) expression).idToken));
				}
			}
			if(expression instanceof ViewExpr) {
				if (containTable(((ViewExpr) expression).idToken) == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", ((ViewExpr) expression).idToken.id));
				}
			}
			if(expression instanceof ViewAllExpr) {
				if (containTable(((ViewAllExpr) expression).idToken) == null) {
					semanticErrors.add(String
							.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", ((ViewAllExpr) expression).idToken.id));
				}
			}
		}
		HashMap<String, ArrayList<String>> tables = new HashMap<String, ArrayList<String>>();
		for(Expression e: expressions) {
			if(e instanceof FormExpr) {
				ArrayList<String> fields = new ArrayList<String>();
				FormExpr expr = (FormExpr)e;
				for(FormInputExpr fie: expr.formInputs) {
					fields.add(fie.idToken.id);
				}
				tables.putIfAbsent(expr.idToken.id, fields);
			}
		}
		for(Table table: tablesDB) {
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
	}
	private Table containTable(IdToken idToken) {
		for (Table t : tablesDB) {
			if (t.getName().equals(idToken.id)) {
				return t;
			}
		}
		return null;
	}
	private boolean containColumn(Table table, IdToken col) {
		for (Table t : tablesDB) {
			if (t.getId() == table.getId()) {
				for (Field f : t.getFields()) {
					if (f.getName().equals(col.id)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
