// Generated from Crudql.g4 by ANTLR 4.9.2

	package antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CrudqlParser}.
 */
public interface CrudqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CrudqlParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CrudqlParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(CrudqlParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(CrudqlParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#update}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(CrudqlParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#update}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(CrudqlParser.UpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(CrudqlParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(CrudqlParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#view}.
	 * @param ctx the parse tree
	 */
	void enterView(CrudqlParser.ViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#view}.
	 * @param ctx the parse tree
	 */
	void exitView(CrudqlParser.ViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#viewAll}.
	 * @param ctx the parse tree
	 */
	void enterViewAll(CrudqlParser.ViewAllContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#viewAll}.
	 * @param ctx the parse tree
	 */
	void exitViewAll(CrudqlParser.ViewAllContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CrudqlParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CrudqlParser.ExprContext ctx);
}