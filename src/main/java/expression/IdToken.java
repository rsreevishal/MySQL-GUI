package expression;

import java.util.HashMap;

import ftl_templates.FTLConvertor;

public class IdToken extends Expression {
	public String id;
	
	public IdToken(String id) {
		this.id = id;
	}

	@Override
	public String toHTML() {
		return String.format("<span style='color:green;'>%s</span>", id);
	}

	@Override
	public String toFTL() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		String result = FTLConvertor.convert(data, "${id}");
		return result;
	}
}
