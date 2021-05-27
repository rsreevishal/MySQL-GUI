package expression;

import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class CrudqlErrorListener extends BaseErrorListener {
	public static boolean hasError = false;
	public static String errorMsg = "Error occured";
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		hasError = true;
		List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		System.err.println("Syntax Error!");
		errorMsg = "Token "+ "\"" + ((Token) offendingSymbol).getText() + "\"" 
				+ " (line " + line + ", column "+ (charPositionInLine + 1) + ")" 
				+ ": " + msg;
		System.err.println(errorMsg);
		System.err.println("Rule stack: " + stack);
	}
}