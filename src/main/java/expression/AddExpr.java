package expression;

public class AddExpr extends Expression {
	public IdToken idToken;
	public ListToken listToken;
	public AddExpr(IdToken idToken, ListToken listToken) {
		this.idToken = idToken;
		this.listToken = listToken;
	}
	@Override
	public String toHTML() {
		return String.format(
				"<p><span style='color: #4287f5;'>Record: %s </span> <span style='color: green;'>inserted</span> successfully in %s!</p>",
				listToken.toHTML(), idToken.toHTML());
	}
	@Override
	public String toFTL() {
		return "";
	}
}
