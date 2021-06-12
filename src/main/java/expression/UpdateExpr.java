package expression;

public class UpdateExpr extends Expression {
	public IdToken idToken;
	public UListToken uListToken;
	
	public UpdateExpr(IdToken idToken, UListToken uListToken) {
		this.idToken = idToken;
		this.uListToken = uListToken;
	}

	@Override
	public String toHTML() {
		return String.format(
				"<p>%s <span style='color: green;'>updated</span> successfully in %s with values %s!</p>",
				uListToken.uidToken.toHTML(), idToken.toHTML(), uListToken.toHTML());
	}

	@Override
	public String toFTL() {
		return "";
	}
}
