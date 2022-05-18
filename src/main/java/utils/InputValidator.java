package utils;

import java.util.regex.Pattern;

public class InputValidator {
	private static final String HTML_TAG_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
	public static boolean validateHtml(final String text){
		Pattern htmlValidator = HTML_TAG_PATTERN.length() == 0 ? null : Pattern.compile(HTML_TAG_PATTERN);
	    if(htmlValidator !=null)
	      return htmlValidator.matcher(text).find();
	    return false;
	}
}
