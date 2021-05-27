package expression;

public class DeleteExpr extends Expression {
	public IdToken idToken;
	public UidToken uidToken;
	
	public DeleteExpr(IdToken idToken, UidToken uidToken) {
		this.idToken = idToken;
		this.uidToken = uidToken;
	}
}
