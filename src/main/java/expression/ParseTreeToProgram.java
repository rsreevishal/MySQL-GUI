package expression;

import java.util.HashMap;
import antlr.CrudqlBaseVisitor;
import antlr.CrudqlParser.ProgContext;
import model.User;

public class ParseTreeToProgram extends CrudqlBaseVisitor<Program> {
	public HashMap<String, String> vars;
	public User user;
	public ParseTreeToProgram(User _user, boolean _createNew) {
		vars = new HashMap<String, String>();
		user = _user;
	}
	
	@Override
	public Program visitProg(ProgContext ctx) {
		Program program = new Program();
		ParseTreeToExpression exprVisitor = new ParseTreeToExpression(vars, user);
		for(int i=0; i<ctx.getChildCount(); i++) {
			if(i == ctx.getChildCount() - 1) {
				// EOF
				continue;
			} else {
				program.addExpression(exprVisitor.visit(ctx.getChild(i)));
			}
		}
		return program;
	}

}
