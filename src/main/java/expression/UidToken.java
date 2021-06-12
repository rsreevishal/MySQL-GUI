package expression;

public class UidToken extends Expression {
	public int uid;
	
	public UidToken(int uid) {
		this.uid = uid;
	}

	@Override
	public String toHTML() {
		return String.format("<span style='color:red;'>Record: %d</span>", uid);
	}

	@Override
	public String toFTL() {
		return "";
	}
}
