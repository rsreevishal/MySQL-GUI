package expression;

public class ViewExpr extends Expression {
	public IdToken idToken;
	public UidToken uidToken;
	
	public ViewExpr(IdToken idToken, UidToken uidToken) {
		this.idToken = idToken;
		this.uidToken = uidToken;
	}
}
