package expression;

import java.util.ArrayList;
import java.util.HashMap;

import ftl_templates.FTLConvertor;

public class FormExpr extends Expression {
	public IdToken idToken;
	public ArrayList<FormInputExpr> formInputs;
	public FormExpr() {}
	public FormExpr(IdToken idToken, ArrayList<FormInputExpr> formInputs) {
		this.idToken = idToken;
		this.formInputs = formInputs;
	}
	
	public void addInput(FormInputExpr input) {
		formInputs.add(input);
	}

	@Override
	public String toHTML() {
		String output = "<form class='form' id='table_form' method='post' action='/mysqlgui/InsertTableRecord'><h3>%s</h3><input type='hidden' name='tab' value='tab2'/><input type='hidden' name='tablename' value='%s'/>%s<button class='btn btn-success' type='submit'>Insert record</button></form>";
		String inputs = "";
		for(FormInputExpr expr: formInputs) {
			inputs += expr.toHTML();		
		}
		return String.format(output, idToken.id, idToken.id, inputs);
	}

	@Override
	public String toFTL() {
		idToken.toFTL();
		for(FormInputExpr fie: formInputs) {
			fie.toFTL();
		}
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("table", idToken.toFTL());
		ArrayList<String> inputs = new ArrayList<String>();
		for(FormInputExpr input: formInputs) {
			inputs.add(input.toFTL());
		}
		data.put("inputs", inputs);
		String result = FTLConvertor.convert(data, "CREATE FORM ${table} [ <#list inputs as input>${input} </#list> ]");
		return result;
	}
}
