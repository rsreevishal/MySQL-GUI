package expression;

import java.util.ArrayList;

public class CreateFormReportExpr extends Expression {
	public IdToken name, table;
	public ArrayList<ConditionExpr> conditions;
	
	public CreateFormReportExpr(IdToken name, IdToken table, ArrayList<ConditionExpr> conditions) {
		this.name = name;
		this.table = table;
		this.conditions = conditions;
	}
}
