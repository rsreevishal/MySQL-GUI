package expression;

import java.util.ArrayList;
import java.util.Arrays;
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
import antlr.CrudqlParser.UpdateFormContext;
import antlr.CrudqlParser.ViewAllContext;
import antlr.CrudqlParser.ViewContext;
import model.User;

public class ParseTreeToExpression extends CrudqlBaseVisitor<Expression> {
	public HashMap<String, String> vars;
	public User user;

	public ParseTreeToExpression(HashMap<String, String> vars, User _user) {
		this.vars = vars;
		this.user = _user;
	}
	@Override
	public Expression visitCreateForm(CreateFormContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
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
			FormInputExpr formInput = new FormInputExpr(fIdToken, inputType, new ListToken(args));
			formInputs.add(formInput);
		}	
		FormExpr expr = new FormExpr(idToken, formInputs);
		expr.setUser(user);
		return expr;
	}

	@Override
	public Expression visitUpdateForm(UpdateFormContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
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
		return new ColViewExpr(idToken, colToken, uidToken);
	}

	@Override
	public Expression visitColViewAll(ColViewAllContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().get(0).getText());
		IdToken colToken = new IdToken(ctx.ID().get(1).getText());
		return new ColViewAllExpr(idToken, colToken);
	}

	@Override
	public Expression visitStoreColView(StoreColViewContext ctx) {
		VarToken varToken = new VarToken(ctx.VAR().getText());
		IdToken idToken = new IdToken(ctx.colView().ID().get(0).getText());
		IdToken colToken = new IdToken(ctx.colView().ID().get(1).getText());
		if (!this.vars.containsKey(varToken.var)) {
			System.out.println("Token to be stored: " + varToken.var);
			this.vars.put(varToken.var, "");
		}
		UidToken uidToken = new UidToken(Integer.parseInt(ctx.colView().UID().getText()));
		ColViewExpr colViewExpr = new ColViewExpr(idToken, colToken, uidToken);
		return new StoreColViewExpr(varToken, colViewExpr);
	}

	@Override
	public Expression visitAdd(AddContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		String valuesStr = ctx.LIST().getText();
		String contentStr = valuesStr.substring(valuesStr.indexOf("(") + 1, valuesStr.indexOf(")"));
		String[] content = contentStr.split(",");
		ListToken listToken = new ListToken(new ArrayList<String>(Arrays.asList(content)));
		return new AddExpr(idToken, listToken);
	}

	@Override
	public Expression visitUpdate(UpdateContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		String valuesStr = ctx.ULIST().getText();
		String contentStr = valuesStr.substring(valuesStr.indexOf("(") + 1, valuesStr.indexOf(")"));
		String[] content = contentStr.split(",");
		int uid = Integer.parseInt(content[0]);
		UidToken uidToken = new UidToken(uid);
		UListToken uListToken = new UListToken(uidToken, new ArrayList<String>(Arrays.asList(content).subList(1, content.length)));
		return new UpdateExpr(idToken, uListToken);
	}

	@Override
	public Expression visitDelete(DeleteContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		int uid = Integer.parseInt(ctx.UID().getText());
		UidToken uidToken = new UidToken(uid);
		return new DeleteExpr(idToken, uidToken);
	}

	@Override
	public Expression visitView(ViewContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		int uid = Integer.parseInt(ctx.UID().getText());
		UidToken uidToken = new UidToken(uid);
		return new ViewExpr(idToken, uidToken);
	}

	@Override
	public Expression visitViewAll(ViewAllContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		return new ViewAllExpr(idToken);
	}

}
