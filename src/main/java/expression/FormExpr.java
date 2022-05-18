package expression;

import java.util.ArrayList;
import java.util.HashMap;

import ftl_templates.FTLConvertor;
import model.User;

public class FormExpr extends Expression {
	private User user;
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
	
	public FormInputExpr getInputByName(String name) {
		for(FormInputExpr fie: formInputs) {
			if(fie.idToken.id.equals(name)) {
				return fie;
			}
		}
		return null;
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
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("table", idToken.toFTL());
		ArrayList<String> inputs = new ArrayList<String>();
		for(FormInputExpr input: formInputs) {
			inputs.add(input.toFTL());
		}
		data.put("inputs", inputs);
		String result = FTLConvertor.convert(this, data);
		return result;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<ArrayList<String>> trieKeys() {
		ArrayList<ArrayList<String>> keys = new ArrayList<ArrayList<String>>();
		for(FormInputExpr fie: formInputs) {
			ArrayList<String> key1 = new ArrayList<String>();
			key1.add("t_" + idToken.id);
			key1.add("fn_" + fie.idToken.id);
			key1.add("ft_" + fie.inputType.toString());
			key1.add("fa_" + fie.args.values);
			keys.add(key1);
		}
		return keys;
	}
	public String trieKeysToString() {
		ArrayList<ArrayList<String>> keys = trieKeys();
		String result = "";
		for(ArrayList<String> key: keys) {
			String sKey = "";
			for(String val: key) {
				sKey += "&" + val;
			}
			result += "#" + sKey.substring(1);
		}
		return result.substring(1);
	}
}
