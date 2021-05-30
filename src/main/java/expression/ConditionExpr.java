package expression;

public class ConditionExpr extends Expression {
	public IdToken colName;
	public TextToken colValue;
	public OperatorExpr operatorExpr;
	
	public ConditionExpr(IdToken idToken, TextToken textToken, OperatorExpr operatorExpr) {
		this.colName = idToken;
		this.colValue = textToken;
		this.operatorExpr = operatorExpr;
	}
}
