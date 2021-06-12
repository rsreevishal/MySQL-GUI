package expression;

import java.util.HashMap;

import ftl_templates.FTLConvertor;

public class TextToken extends Expression {
	public String text;
	
	public TextToken(String text) {
		this.text = text;
	}

	@Override
	public String toHTML() {
		return String.format("<span style='color:green;'>%s</span>", text);
	}

	@Override
	public String toFTL() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("text", text);
		return FTLConvertor.convert(data, "${text}");
	}
}
