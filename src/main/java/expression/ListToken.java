package expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ftl_templates.FTLConvertor;

public class ListToken extends Expression {
	public List<String> values;
	
	public ListToken(List<String> values) {
		this.values = values;
	}
	@Override
	public String toHTML() {
		return values.toString();
	}
	@Override
	public String toFTL() {
		String args = "";
		for(String val: values) {
			args += String.format(",%s", val);
		}
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("args", args.substring(1));
		String result = FTLConvertor.convert(this, data);
		return result;
	}
	@Override
	public String toString() {
		String result = "";
		for(String val: values) {
			val = "'" + val + "'";
			result += ("," + val);
		}
		return result.substring(1);
	}
	
	public static ArrayList<String> fromString(String val) {
		return new ArrayList<String>(Arrays.asList(val.split(",")));
	}
}
