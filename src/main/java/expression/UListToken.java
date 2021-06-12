package expression;

import java.util.List;

public class UListToken extends Expression {
	public UidToken uidToken;
	public List<String> values;
	UListToken(UidToken uidToken, List<String> values) {
		this.uidToken = uidToken;
		this.values = values;
	}
	@Override
	public String toHTML() {
		return values.toString();
	}
	@Override
	public String toFTL() {
		return "";
	}
}
