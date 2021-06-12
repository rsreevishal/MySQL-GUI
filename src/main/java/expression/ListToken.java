package expression;

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
		String result = FTLConvertor.convert(data, "(${args})");
		return result;
	}
}
