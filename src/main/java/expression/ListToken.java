package expression;

import java.util.List;

public class ListToken extends Expression {
	public List<String> values;
	public ListToken(List<String> values) {
		this.values = values;
	}
}
