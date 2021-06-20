package expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Program {
	public List<Expression> expressions;
	public HashMap<String, String> vars;
	
	public Program() {
		this.expressions = new ArrayList<Expression>();
	}
	
	public void addExpression(Expression e) {
		this.expressions.add(e);
	}
}
