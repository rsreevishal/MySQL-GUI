package expression;

public class ColViewExpr extends Expression {
	public IdToken idToken;
	public IdToken colToken;
	public UidToken uidToken;
	public ColViewExpr(IdToken idToken, IdToken colToken, UidToken uidToken) {
		this.idToken = idToken;
		this.colToken = colToken;
		this.uidToken = uidToken;
	}
}
