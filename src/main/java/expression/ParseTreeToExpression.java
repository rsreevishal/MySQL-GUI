package expression;

import java.util.ArrayList;

import antlr.CrudqlBaseVisitor;
import antlr.CrudqlParser.AddContext;
import antlr.CrudqlParser.DeleteContext;
import antlr.CrudqlParser.UpdateContext;
import antlr.CrudqlParser.ViewAllContext;
import antlr.CrudqlParser.ViewContext;
import model.Table;

public class ParseTreeToExpression extends CrudqlBaseVisitor<Expression> {
	public ArrayList<String> semanticErrors;
	private ArrayList<Table> tables;
	public ParseTreeToExpression(ArrayList<String> semanticErrors, ArrayList<Table> tables) {
		this.semanticErrors = semanticErrors;
		this.tables = tables;
	}
	private boolean containTable(IdToken idToken) {
		for(Table t: tables) {
			if(t.getName().equals(idToken.id)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public Expression visitAdd(AddContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(!containTable(idToken)) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		ArrayList<String> values = new ArrayList<String>();
		String valuesStr = ctx.LIST().getText();
		String contentStr = valuesStr.substring(valuesStr.indexOf("(")+1, valuesStr.indexOf(")"));
		String[] content = contentStr.split(",");
		for(String val: content) {
			values.add(val);
		}
		ListToken listToken = new ListToken(values);
		return new AddExpr(idToken, listToken);
	}

	@Override
	public Expression visitUpdate(UpdateContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(!containTable(idToken)) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		ArrayList<String> values = new ArrayList<String>();
		String valuesStr = ctx.ULIST().getText();
		String contentStr = valuesStr.substring(valuesStr.indexOf("(")+1, valuesStr.indexOf(")"));
		String[] content = contentStr.split(",");
		int uid = Integer.parseInt(content[0]);
		UidToken uidToken = new UidToken(uid);
		for(int i=1; i<content.length; i++) {
			values.add(content[i]);
		}
		UListToken uListToken = new UListToken(uidToken, values);
		return new UpdateExpr(idToken, uListToken);
	}

	@Override
	public Expression visitDelete(DeleteContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(!containTable(idToken)) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		int uid = Integer.parseInt(ctx.UID().getText());
		UidToken uidToken = new UidToken(uid);
		return new DeleteExpr(idToken, uidToken);
	}

	@Override
	public Expression visitView(ViewContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(!containTable(idToken)) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		int uid = Integer.parseInt(ctx.UID().getText());
		UidToken uidToken = new UidToken(uid);
		return new ViewExpr(idToken, uidToken);
	}

	@Override
	public Expression visitViewAll(ViewAllContext ctx) {
		IdToken idToken = new IdToken(ctx.ID().getText());
		if(!containTable(idToken)) {
			semanticErrors.add(String.format("<p><span style='color:red;'>%s</span> does not contain in the database!</p>", idToken.id));
		}
		return new ViewAllExpr(idToken);
	}

}
