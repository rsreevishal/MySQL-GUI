// Generated from Crudql.g4 by ANTLR 4.9.2

	package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CrudqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CrudqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CrudqlParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(CrudqlParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrudqlParser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(CrudqlParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrudqlParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(CrudqlParser.UpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrudqlParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(CrudqlParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrudqlParser#view}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitView(CrudqlParser.ViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrudqlParser#viewAll}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewAll(CrudqlParser.ViewAllContext ctx);
	/**
	 * Visit a parse tree produced by {@link CrudqlParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CrudqlParser.ExprContext ctx);
}