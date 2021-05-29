package expression;

import java.util.ArrayList;

public class FormInputExpr extends Expression {
	public IdToken idToken;
	public InputType inputType;
	public ArrayList<String> args;
	
	public FormInputExpr(IdToken idToken, InputType inputType, ArrayList<String> args) {
		this.idToken = idToken;
		this.inputType = inputType;
		this.args = args;
	}
}
