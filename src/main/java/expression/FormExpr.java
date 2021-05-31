package expression;

import java.util.ArrayList;

public class FormExpr extends Expression {
	private String query;
	public IdToken idToken;
	public ArrayList<FormInputExpr> formInputs;
	
	public FormExpr(IdToken idToken, ArrayList<FormInputExpr> formInputs) {
		this.idToken = idToken;
		this.formInputs = formInputs;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
