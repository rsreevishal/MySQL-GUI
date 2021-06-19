package expression;

import java.util.ArrayList;
import java.util.HashMap;

import ftl_templates.FTLConvertor;
import model.FieldType;

public class FormInputExpr extends Expression {
	public IdToken idToken;
	public InputType inputType;
	public ListToken args;
	private ArrayList<String> linkedValues;
	
	public ArrayList<String> getLinkedValues() {
		return linkedValues;
	}

	public void setLinkedValues(ArrayList<String> linkedValues) {
		this.linkedValues = linkedValues;
	}

	public static int cbCount = 0;
	public static int rCount = 0;

	public FormInputExpr(IdToken idToken, InputType inputType, ListToken args) {
		this.idToken = idToken;
		this.inputType = inputType;
		this.args = args;
	}

	@Override
	public String toHTML() {
		String output = "";
		output += String.format("<input type='hidden' name='fieldName' value='%s' />", idToken.id);
		output += String.format("<input type='hidden' name='fieldType' value='%s'/>",
				FieldType.fromInputType(inputType));
		switch (inputType) {
		case EMAIL:
		case PASSWORD:
		case TEXT: {
			output += String.format("<label>%s</label>", idToken.id);
			output += String.format(
					"<input class='form-control' type='%s' minlength='%s' maxlength='%s' name='fieldValue' required/>",
					inputType.toString().toLowerCase(), args.values.get(0), args.values.get(1), idToken.id);
			break;
		}
		case TEXTAREA: {
			output += String.format("<label>%s</label>", idToken.id);
			output += String.format(
					"<textarea class='form-control' minlength='%s' maxlength='%s' name='fieldValue' required></textarea>",
					args.values.get(0), args.values.get(1), idToken.id);
			break;
		}
		case LINK: {
			output += String.format("<label>%s</label>", idToken.id);
			String options = "";
			for (String value : linkedValues) {
				options += String.format("<option value='%s'>%s</option>", value, value);
				
			}
			output += String.format("<select class='form-control' name='fieldValue' required>%s</select>", options);
			break;
		}
		case CHECKBOX: {
			for (String val : args.values) {
				output += String.format("<input type='%s' name='fieldValue' value='cb%d_%s' />",
						inputType.toString().toLowerCase(), FormInputExpr.cbCount, val);
				output += String.format("<label>%s</label>", val);
			}
			FormInputExpr.cbCount += 1;
			break;
		}
		case RADIO: {
			for (String val : args.values) {
				output += String.format("<input type='%s' name='radio%d' value='%s'/>",
						inputType.toString().toLowerCase(), rCount, val);
				output += String.format("<label>%s</label>", val);
			}
			break;
		}
		case NUMBER: {
			output += String.format("<label>%s</label>", idToken.id);
			output += String.format(
					"<input class='form-control' type='%s' min='%s' max='%s' name='fieldValue' required/>",
					inputType.toString().toLowerCase(), args.values.get(0), args.values.get(1), idToken.id);
			break;
		}
		default: {
		}
		}
		FormInputExpr.rCount += 1;
		return output;
	}

	@Override
	public String toFTL() {
		idToken.toFTL();
		args.toFTL();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("input", idToken.toFTL());
		data.put("inputType", inputType.toString());
		data.put("args", args.toFTL());
		String result = FTLConvertor.convert(this, data);
		return result;
	}
}
