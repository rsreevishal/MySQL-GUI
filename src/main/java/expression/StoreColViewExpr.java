package expression;

public class StoreColViewExpr extends Expression {
	public VarToken varToken;
	public ColViewExpr colViewExpr;
	public StoreColViewExpr(VarToken varToken, ColViewExpr colViewExpr) {
		this.varToken = varToken;
		this.colViewExpr = colViewExpr;
	}
	@Override
	public String toHTML() {
		return String.format("<div><h3>%s</h3>%s</div>", varToken.toHTML(), colViewExpr.toHTML());
	}
	@Override
	public String toFTL() {
		return "";
	}
}
