package expression;

import java.util.ArrayList;
import java.util.HashMap;
import antlr.CrudqlBaseVisitor;
import antlr.CrudqlParser.AddContext;
import antlr.CrudqlParser.ColViewAllContext;
import antlr.CrudqlParser.ColViewContext;
import antlr.CrudqlParser.ConditionContext;
import antlr.CrudqlParser.CreateFormContext;
import antlr.CrudqlParser.CreateFormReportContext;
import antlr.CrudqlParser.DeleteContext;
import antlr.CrudqlParser.FormInputContext;
import antlr.CrudqlParser.StoreColViewContext;
import antlr.CrudqlParser.UpdateContext;
import antlr.CrudqlParser.ViewAllContext;
import antlr.CrudqlParser.ViewContext;
import model.Field;
import model.Pair;
import model.Table;
import model.User;

public class ParseTreeToExpression extends CrudqlBaseVisitor<Expression> {
	public ArrayList<String> semanticErrors;
	public HashMap<String, String> vars;
	private ArrayList<Table> tables;
	private boolean createNew;
	private ArrayList<Pair<String, String>> linkedTables;
	public User user;

	public ParseTreeToExpression(ArrayList<String> semanticErrors, HashMap<String, String> vars,
			ArrayList<Table> tables, boolean _createNew, ArrayList<Pair<String, String>> _linkedTables, User _user) {
		this.semanticErrors = semanticErrors;
		this.tables = tables;
		this.vars = vars;
		this.createNew = _createNew;
		this.linkedTables = _linkedTables;
		this.user = _user;
	}
	@Override
	public Expression visitCreateForm(CreateFormContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		Table table = containTable(idToken);
		if (table != null && this.createNew) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> already exists in the database!</p>", idToken.id));
		}
		ArrayList<FormInputExpr> formInputs = new ArrayList<FormInputExpr>();
		for(FormInputContext fictx: ctx.formInput()) {
			IdToken fIdToken = new IdToken(fictx.ID().getText());
			String argStr = fictx.LIST().getText();
			String[] argsStr = argStr.substring(1, argStr.length() - 1).split(",");
			ArrayList<String> args = new ArrayList<String>();
			for(String arg: argsStr) {
				args.add(arg.substring(1, arg.length() - 1));
			}
			InputType inputType = InputType.valueOf(fictx.inputType().getText());
			if(inputType == InputType.LINK) {
				linkedTables.add(new Pair<String, String>(args.get(0),args.get(1)));
			}
			FormInputExpr formInput = new FormInputExpr(fIdToken, inputType, new ListToken(args));
			formInputs.add(formInput);
		}	
		FormExpr expr = new FormExpr(idToken, formInputs);
		expr.setUser(user);
		return expr;
	}

	@Override
	public Expression visitCreateFormReport(CreateFormReportContext ctx) {
		IdToken reportIdToken = new IdToken(ctx.ID().get(0).getText());
		IdToken tableIdToken = new IdToken(ctx.ID().get(1).getText());
		ArrayList<ConditionExpr> conditions = new ArrayList<ConditionExpr>();
		for(ConditionContext cctx: ctx.condition()) {
			IdToken colIdToken = new IdToken(cctx.ID().getText());
			OperatorExpr operator = new OperatorExpr(cctx.operator().getText());
			TextToken value = new TextToken(cctx.TEXT().getText().substring(1, cctx.TEXT().getText().length() - 1));
			conditions.add(new ConditionExpr(colIdToken, value, operator));
		}
		FormReportExpr expr = new FormReportExpr(reportIdToken, tableIdToken, conditions);
		return expr;
	}

	@Override
	public Expression visitColView(ColViewContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().get(0).getText());
		IdToken colToken = new IdToken(ctx.ID().get(1).getText());
		UidToken uidToken = new UidToken(Integer.parseInt(ctx.UID().getText()));
		Table table = containTable(idToken);
		if (table == null) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		} else {
			if (!containColumn(table, colToken)) {
				semanticErrors
						.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>",
								colToken.id, table.getName()));
			}
		}
		return new ColViewExpr(idToken, colToken, uidToken);
	}

	@Override
	public Expression visitColViewAll(ColViewAllContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().get(0).getText());
		IdToken colToken = new IdToken(ctx.ID().get(1).getText());
		Table table = containTable(idToken);
		if (table == null) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		} else {
			if (!containColumn(table, colToken)) {
				semanticErrors
						.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>",
								colToken.id, table.getName()));
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
		if (table == null) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		} else {
			if (!containColumn(table, colToken)) {
				semanticErrors
						.add(String.format("<p><span style='color:red;'>%s</span> does not contain in table %s!</p>",
								colToken.id, table.getName()));
			}
		}
		if (this.vars.containsKey(varToken.var)) {
			semanticErrors
					.add(String.format("<p><span style='color:red;'>%s</span> already declared!</p>", varToken.var));
		} else {
			System.out.println("Token to be stored: " + varToken.var);
			this.vars.put(varToken.var, "");
		}
		UidToken uidToken = new UidToken(Integer.parseInt(ctx.colView().UID().getText()));
		ColViewExpr colViewExpr = new ColViewExpr(idToken, colToken, uidToken);
		return new StoreColViewExpr(varToken, colViewExpr);
	}

	private Table containTable(IdToken idToken) {
		for (Table t : tables) {
			if (t.getName().equals(idToken.id)) {
				return t;
			}
		}
		return null;
	}

	private boolean containColumn(Table table, IdToken col) {
		for (Table t : tables) {
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

	@Override
	public Expression visitAdd(AddContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if (containTable(idToken) == null) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		ArrayList<String> values = new ArrayList<String>();
		String valuesStr = ctx.LIST().getText();
		String contentStr = valuesStr.substring(valuesStr.indexOf("(") + 1, valuesStr.indexOf(")"));
		String[] content = contentStr.split(",");
		for (String val : content) {
			String valContent = val.substring(1, val.length() - 1);
			System.out.println("Insert Field: " + valContent);
			if (valContent.charAt(0) == '$' && !this.vars.containsKey(valContent)) {
				semanticErrors
						.add(String.format("<p><span style='color:red;'>%s</span> haven't declared!</p>", valContent));
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
		if (containTable(idToken) == null) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		ArrayList<String> values = new ArrayList<String>();
		String valuesStr = ctx.ULIST().getText();
		String contentStr = valuesStr.substring(valuesStr.indexOf("(") + 1, valuesStr.indexOf(")"));
		String[] content = contentStr.split(",");
		int uid = Integer.parseInt(content[0]);
		UidToken uidToken = new UidToken(uid);
		for (int i = 1; i < content.length; i++) {
			String val = content[i];
			String valContent = val.substring(1, val.length() - 1);
			if (val.charAt(1) == '$' && !this.vars.containsKey(valContent)) {
				semanticErrors
						.add(String.format("<p><span style='color:red;'>%s</span> haven't declared!</p>", valContent));
			} else if (this.vars.containsKey(valContent)) {
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
		if (containTable(idToken) == null) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		int uid = Integer.parseInt(ctx.UID().getText());
		UidToken uidToken = new UidToken(uid);
		return new DeleteExpr(idToken, uidToken);
	}

	@Override
	public Expression visitView(ViewContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if (containTable(idToken) == null) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		int uid = Integer.parseInt(ctx.UID().getText());
		UidToken uidToken = new UidToken(uid);
		return new ViewExpr(idToken, uidToken);
	}

	@Override
	public Expression visitViewAll(ViewAllContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if (containTable(idToken) == null) {
			semanticErrors.add(String
					.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		return new ViewAllExpr(idToken);
	}

}
