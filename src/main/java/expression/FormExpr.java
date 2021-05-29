package expression;

import java.util.ArrayList;

public class FormExpr extends Expression {
	public IdToken idToken;
	public ArrayList<FormInputExpr> formInputs;
	
	public FormExpr(IdToken idToken, ArrayList<FormInputExpr> formInputs) {
		this.idToken = idToken;
		this.formInputs = formInputs;
	}
}
