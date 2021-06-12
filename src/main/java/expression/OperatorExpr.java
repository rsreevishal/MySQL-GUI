package expression;

import java.util.HashMap;

import ftl_templates.FTLConvertor;

public class OperatorExpr extends Expression {
	public String operator;
	
	public OperatorExpr(String operator) {
		this.operator = operator;
	}

	@Override
	public String toHTML() {
		return String.format("<span style='color:red'>%s</span>", operator);
	}

	@Override
	public String toFTL() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("operator", operator);
		return FTLConvertor.convert(data, "${operator}");
	}
}
