// Generated from antlr/Crudql.g4 by ANTLR 4.9.2

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
	 * Enter a parse tree produced by {@link CrudqlParser#colView}.
	 * @param ctx the parse tree
	 */
	void enterColView(CrudqlParser.ColViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#colView}.
	 * @param ctx the parse tree
	 */
	void exitColView(CrudqlParser.ColViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#colViewAll}.
	 * @param ctx the parse tree
	 */
	void enterColViewAll(CrudqlParser.ColViewAllContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#colViewAll}.
	 * @param ctx the parse tree
	 */
	void exitColViewAll(CrudqlParser.ColViewAllContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#storeColView}.
	 * @param ctx the parse tree
	 */
	void enterStoreColView(CrudqlParser.StoreColViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#storeColView}.
	 * @param ctx the parse tree
	 */
	void exitStoreColView(CrudqlParser.StoreColViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#createForm}.
	 * @param ctx the parse tree
	 */
	void enterCreateForm(CrudqlParser.CreateFormContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#createForm}.
	 * @param ctx the parse tree
	 */
	void exitCreateForm(CrudqlParser.CreateFormContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#createFormReport}.
	 * @param ctx the parse tree
	 */
	void enterCreateFormReport(CrudqlParser.CreateFormReportContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#createFormReport}.
	 * @param ctx the parse tree
	 */
	void exitCreateFormReport(CrudqlParser.CreateFormReportContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#formInput}.
	 * @param ctx the parse tree
	 */
	void enterFormInput(CrudqlParser.FormInputContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#formInput}.
	 * @param ctx the parse tree
	 */
	void exitFormInput(CrudqlParser.FormInputContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(CrudqlParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(CrudqlParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(CrudqlParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(CrudqlParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CrudqlParser#inputType}.
	 * @param ctx the parse tree
	 */
	void enterInputType(CrudqlParser.InputTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CrudqlParser#inputType}.
	 * @param ctx the parse tree
	 */
	void exitInputType(CrudqlParser.InputTypeContext ctx);
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