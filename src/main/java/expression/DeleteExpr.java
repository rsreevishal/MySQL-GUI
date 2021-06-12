package expression;

public class DeleteExpr extends Expression {
	public IdToken idToken;
	public UidToken uidToken;
	
	public DeleteExpr(IdToken idToken, UidToken uidToken) {
		this.idToken = idToken;
		this.uidToken = uidToken;
	}

	@Override
	public String toHTML() {
		return String.format(
				"<p>%s <span style='color: red;'>deleted</span> successfully in %s!</p>",
				uidToken.toHTML(), idToken.toHTML());
	}

	@Override
	public String toFTL() {
		return "";
	}
}
