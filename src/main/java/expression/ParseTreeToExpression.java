package expression;

import java.util.ArrayList;
import java.util.HashMap;

import antlr.CrudqlBaseVisitor;
import antlr.CrudqlParser.AddContext;
import antlr.CrudqlParser.ColViewAllContext;
import antlr.CrudqlParser.ColViewContext;
import antlr.CrudqlParser.DeleteContext;
import antlr.CrudqlParser.StoreColViewContext;
import antlr.CrudqlParser.UpdateContext;
import antlr.CrudqlParser.ViewAllContext;
import antlr.CrudqlParser.ViewContext;
import model.Field;
import model.Table;

public class ParseTreeToExpression extends CrudqlBaseVisitor<Expression> {
	@Override
	public Expression visitColView(ColViewContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().get(0).getText());
		IdToken colToken = new IdToken(ctx.ID().get(1).getText());
		UidToken uidToken = new UidToken(Integer.parseInt(ctx.UID().getText()));
		Table table = containTable(idToken);
		if(table == null) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		} else {
			if(!containColumn(table, colToken)) {
				semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>", colToken.id, table.getName()));
			}
		}
		return new ColViewExpr(idToken, colToken, uidToken);
	}
	@Override
	public Expression visitColViewAll(ColViewAllContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().get(0).getText());
		IdToken colToken = new IdToken(ctx.ID().get(1).getText());
		Table table = containTable(idToken);
		if(table == null) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		} else {
			if(!containColumn(table, colToken)) {
				semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>", colToken.id, table.getName()));
			}
		}
		
		return new ColViewAllExpr(idToken, colToken);
	}
	@Override
	public Expression visitStoreColView(StoreColViewContext ctx) {
		VarToken varToken = new VarToken(ctx.VAR().getText());
		IdToken idToken = new IdToken(ctx.colView().ID().get(0).getText());
		IdToken colToken = new IdToken(ctx.colView().ID().get(1).getText());
		Table table = containTable(idToken);
		if(table == null) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		} else {
			if(!containColumn(table, colToken)) {
				semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>", colToken.id, table.getName()));
			}
		}
		if(this.vars.containsKey(varToken.var)) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> already declared!</p>", varToken.var));
		} else {
			System.out.println("Token to be stored: " + varToken.var);
			this.vars.put(varToken.var, "");
		}
		UidToken uidToken = new UidToken(Integer.parseInt(ctx.colView().UID().getText()));
		ColViewExpr colViewExpr = new ColViewExpr(idToken, colToken, uidToken);
		return new StoreColViewExpr(varToken, colViewExpr);
	}

	public ArrayList<String> semanticErrors;
	public HashMap<String, String> vars;
	private ArrayList<Table> tables;
	public ParseTreeToExpression(ArrayList<String> semanticErrors, HashMap<String, String> vars, ArrayList<Table> tables) {
		this.semanticErrors = semanticErrors;
		this.tables = tables;
		this.vars = vars;
	}
	private Table containTable(IdToken idToken) {
		for(Table t: tables) {
			if(t.getName().equals(idToken.id)) {
				return t;
			}
		}
		return null;
	}
	private boolean containColumn(Table table, IdToken col) {
		for(Table t: tables) {
			if(t.getId() == table.getId()) {
				for(Field f: t.getFields()) {
					if(f.getName().equals(col.id)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	@Override
	public Expression visitAdd(AddContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(containTable(idToken) == null) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		ArrayList<String> values = new ArrayList<String>();
		String valuesStr = ctx.LIST().getText();
		String contentStr = valuesStr.substring(valuesStr.indexOf("(")+1, valuesStr.indexOf(")"));
		String[] content = contentStr.split(",");
		for(String val: content) {
			String valContent = val.substring(1, val.length()-1);
			System.out.println("Insert Field: " + valContent);
			if(valContent.charAt(0) == '$' && !this.vars.containsKey(valContent)) {
				semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> haven't declared!</p>", valContent));
			} else {
				values.add(val);	
			}
		}
		ListToken listToken = new ListToken(values);
		return new AddExpr(idToken, listToken);
	}

	@Override
	public Expression visitUpdate(UpdateContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(containTable(idToken) == null) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		ArrayList<String> values = new ArrayList<String>();
		String valuesStr = ctx.ULIST().getText();
		String contentStr = valuesStr.substring(valuesStr.indexOf("(")+1, valuesStr.indexOf(")"));
		String[] content = contentStr.split(",");
		int uid = Integer.parseInt(content[0]);
		UidToken uidToken = new UidToken(uid);
		for(int i=1; i<content.length; i++) {
			String val = content[i];
			String valContent = val.substring(1, val.length()-1);
			if(val.charAt(1) == '$' && !this.vars.containsKey(valContent)) {
				semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> haven't declared!</p>", valContent));
			} else if(this.vars.containsKey(valContent)) {
				values.add("'" + this.vars.get(valContent) + "'");
			} else {
				values.add(val);	
			}
		}
		UListToken uListToken = new UListToken(uidToken, values);
		return new UpdateExpr(idToken, uListToken);
	}

	@Override
	public Expression visitDelete(DeleteContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(containTable(idToken) == null) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		int uid = Integer.parseInt(ctx.UID().getText());
		UidToken uidToken = new UidToken(uid);
		return new DeleteExpr(idToken, uidToken);
	}

	@Override
	public Expression visitView(ViewContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(containTable(idToken) == null) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		int uid = Integer.parseInt(ctx.UID().getText());
		UidToken uidToken = new UidToken(uid);
		return new ViewExpr(idToken, uidToken);
	}

	@Override
	public Expression visitViewAll(ViewAllContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(containTable(idToken) == null) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		return new ViewAllExpr(idToken);
	}

}
