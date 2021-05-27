package expression;

public class AddExpr extends Expression {
	public IdToken idToken;
	public ListToken listToken;
	public AddExpr(IdToken idToken, ListToken listToken) {
		this.idToken = idToken;
		this.listToken = listToken;
	}
}
