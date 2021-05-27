package expression;

public class StoreColViewExpr extends Expression {
	public VarToken varToken;
	public ColViewExpr colViewExpr;
	public StoreColViewExpr(VarToken varToken, ColViewExpr colViewExpr) {
		this.varToken = varToken;
		this.colViewExpr = colViewExpr;
	}
}
