package expression;

import java.util.HashMap;

import ftl_templates.FTLConvertor;

public class ConditionExpr extends Expression {
	public IdToken colName;
	public TextToken colValue;
	public OperatorExpr operatorExpr;
	
	public ConditionExpr(IdToken idToken, TextToken textToken, OperatorExpr operatorExpr) {
		this.colName = idToken;
		this.colValue = textToken;
		this.operatorExpr = operatorExpr;
	}

	@Override
	public String toHTML() {
		return String.format("<p>%s %s %s</p>", colName.toHTML(), operatorExpr.toHTML(), colValue.toHTML());
	}

	@Override
	public String toFTL() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("colName", colName.toFTL());
		data.put("colValue", colValue.toFTL());
		data.put("operator", operatorExpr.toFTL());
		return FTLConvertor.convert(this, data);
	}
}
