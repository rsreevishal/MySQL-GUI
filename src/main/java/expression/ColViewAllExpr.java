package expression;

public class ColViewAllExpr extends Expression {
	public IdToken idToken;
	public IdToken colToken;
	public ColViewAllExpr(IdToken idToken, IdToken colToken) {
		this.idToken = idToken;
		this.colToken = colToken;
	}
}
