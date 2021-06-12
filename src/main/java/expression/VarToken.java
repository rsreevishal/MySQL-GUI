package expression;

public class VarToken extends Expression {
	public String var;
	
	public VarToken(String var) {
		this.var = var;
	}

	@Override
	public String toHTML() {
		return String.format("<span style='color:green;'>%s</span>", var);
	}

	@Override
	public String toFTL() {
		return "";
	}
}
