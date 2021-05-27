package expression;

public class UpdateExpr extends Expression {
	public IdToken idToken;
	public UListToken uListToken;
	
	public UpdateExpr(IdToken idToken, UListToken uListToken) {
		this.idToken = idToken;
		this.uListToken = uListToken;
	}
}
