// Generated from antlr/Crudql.g4 by ANTLR 4.9.2

	package antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CrudqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, LIST=23, ULIST=24, ID=25, 
		VAR=26, TEXT=27, UID=28, WS=29;
	public static final int
		RULE_prog = 0, RULE_add = 1, RULE_update = 2, RULE_delete = 3, RULE_view = 4, 
		RULE_viewAll = 5, RULE_colView = 6, RULE_colViewAll = 7, RULE_storeColView = 8, 
		RULE_createForm = 9, RULE_createFormReport = 10, RULE_formInput = 11, 
		RULE_condition = 12, RULE_operator = 13, RULE_inputType = 14, RULE_expr = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "add", "update", "delete", "view", "viewAll", "colView", "colViewAll", 
			"storeColView", "createForm", "createFormReport", "formInput", "condition", 
			"operator", "inputType", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'^'", "'-'", "'='", "'.'", "'('", "')'", "'CREATE FORM'", 
			"'['", "']'", "'CREATE VIEW'", "'FOR'", "'>'", "'<'", "'HAS'", "'TEXT'", 
			"'NUMBER'", "'TEXTAREA'", "'RADIO'", "'CHECKBOX'", "'PASSWORD'", "'EMAIL'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "LIST", 
			"ULIST", "ID", "VAR", "TEXT", "UID", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Crudql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CrudqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CrudqlParser.EOF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				expr();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__10) | (1L << ID) | (1L << VAR))) != 0) );
			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CrudqlParser.ID, 0); }
		public TerminalNode LIST() { return getToken(CrudqlParser.LIST, 0); }
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitAdd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		AddContext _localctx = new AddContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_add);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(ID);
			setState(40);
			match(T__0);
			setState(41);
			match(LIST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CrudqlParser.ID, 0); }
		public TerminalNode ULIST() { return getToken(CrudqlParser.ULIST, 0); }
		public UpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitUpdate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateContext update() throws RecognitionException {
		UpdateContext _localctx = new UpdateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_update);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(ID);
			setState(44);
			match(T__1);
			setState(45);
			match(ULIST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeleteContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CrudqlParser.ID, 0); }
		public TerminalNode UID() { return getToken(CrudqlParser.UID, 0); }
		public DeleteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterDelete(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitDelete(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitDelete(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteContext delete() throws RecognitionException {
		DeleteContext _localctx = new DeleteContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_delete);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(ID);
			setState(48);
			match(T__2);
			setState(49);
			match(UID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CrudqlParser.ID, 0); }
		public TerminalNode UID() { return getToken(CrudqlParser.UID, 0); }
		public ViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_view; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterView(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitView(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewContext view() throws RecognitionException {
		ViewContext _localctx = new ViewContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_view);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(ID);
			setState(52);
			match(T__3);
			setState(53);
			match(UID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ViewAllContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CrudqlParser.ID, 0); }
		public ViewAllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewAll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterViewAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitViewAll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitViewAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewAllContext viewAll() throws RecognitionException {
		ViewAllContext _localctx = new ViewAllContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_viewAll);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(ID);
			setState(56);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColViewContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(CrudqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CrudqlParser.ID, i);
		}
		public TerminalNode UID() { return getToken(CrudqlParser.UID, 0); }
		public ColViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colView; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterColView(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitColView(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitColView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColViewContext colView() throws RecognitionException {
		ColViewContext _localctx = new ColViewContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_colView);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(ID);
			setState(59);
			match(T__4);
			setState(60);
			match(ID);
			setState(61);
			match(T__3);
			setState(62);
			match(UID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColViewAllContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(CrudqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CrudqlParser.ID, i);
		}
		public ColViewAllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colViewAll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterColViewAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitColViewAll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitColViewAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColViewAllContext colViewAll() throws RecognitionException {
		ColViewAllContext _localctx = new ColViewAllContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_colViewAll);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(ID);
			setState(65);
			match(T__4);
			setState(66);
			match(ID);
			setState(67);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StoreColViewContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(CrudqlParser.VAR, 0); }
		public ColViewContext colView() {
			return getRuleContext(ColViewContext.class,0);
		}
		public StoreColViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_storeColView; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterStoreColView(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitStoreColView(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitStoreColView(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StoreColViewContext storeColView() throws RecognitionException {
		StoreColViewContext _localctx = new StoreColViewContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_storeColView);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(VAR);
			setState(70);
			match(T__3);
			setState(71);
			match(T__5);
			setState(72);
			colView();
			setState(73);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreateFormContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CrudqlParser.ID, 0); }
		public List<FormInputContext> formInput() {
			return getRuleContexts(FormInputContext.class);
		}
		public FormInputContext formInput(int i) {
			return getRuleContext(FormInputContext.class,i);
		}
		public CreateFormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createForm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterCreateForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitCreateForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitCreateForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateFormContext createForm() throws RecognitionException {
		CreateFormContext _localctx = new CreateFormContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_createForm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__7);
			setState(76);
			match(ID);
			setState(77);
			match(T__8);
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				formInput();
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(83);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreateFormReportContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(CrudqlParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CrudqlParser.ID, i);
		}
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public CreateFormReportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createFormReport; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterCreateFormReport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitCreateFormReport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitCreateFormReport(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateFormReportContext createFormReport() throws RecognitionException {
		CreateFormReportContext _localctx = new CreateFormReportContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_createFormReport);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(T__10);
			setState(86);
			match(ID);
			setState(87);
			match(T__11);
			setState(88);
			match(ID);
			setState(89);
			match(T__8);
			setState(91); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(90);
				condition();
				}
				}
				setState(93); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(95);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormInputContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CrudqlParser.ID, 0); }
		public InputTypeContext inputType() {
			return getRuleContext(InputTypeContext.class,0);
		}
		public TerminalNode LIST() { return getToken(CrudqlParser.LIST, 0); }
		public FormInputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formInput; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterFormInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitFormInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitFormInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormInputContext formInput() throws RecognitionException {
		FormInputContext _localctx = new FormInputContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_formInput);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(ID);
			setState(98);
			inputType();
			setState(99);
			match(LIST);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CrudqlParser.ID, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(CrudqlParser.TEXT, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(ID);
			setState(102);
			operator();
			setState(103);
			match(TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InputTypeContext extends ParserRuleContext {
		public InputTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inputType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterInputType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitInputType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitInputType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputTypeContext inputType() throws RecognitionException {
		InputTypeContext _localctx = new InputTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_inputType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public UpdateContext update() {
			return getRuleContext(UpdateContext.class,0);
		}
		public DeleteContext delete() {
			return getRuleContext(DeleteContext.class,0);
		}
		public ViewContext view() {
			return getRuleContext(ViewContext.class,0);
		}
		public ViewAllContext viewAll() {
			return getRuleContext(ViewAllContext.class,0);
		}
		public ColViewContext colView() {
			return getRuleContext(ColViewContext.class,0);
		}
		public ColViewAllContext colViewAll() {
			return getRuleContext(ColViewAllContext.class,0);
		}
		public StoreColViewContext storeColView() {
			return getRuleContext(StoreColViewContext.class,0);
		}
		public CreateFormContext createForm() {
			return getRuleContext(CreateFormContext.class,0);
		}
		public CreateFormReportContext createFormReport() {
			return getRuleContext(CreateFormReportContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CrudqlListener ) ((CrudqlListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CrudqlVisitor ) return ((CrudqlVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr);
		try {
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				add();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				update();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				delete();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(112);
				view();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				viewAll();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(114);
				colView();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(115);
				colViewAll();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(116);
				storeColView();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(117);
				createForm();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(118);
				createFormReport();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37|\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\6\2$\n\2\r"+
		"\2\16\2%\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\6\13R\n\13\r\13\16\13S\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\6\f^\n\f\r\f\16\f_\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\5\21z\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \2\4\4\2\6\6\17\21\3\2\22\30\2w\2#\3\2\2\2\4)\3\2\2\2\6-"+
		"\3\2\2\2\b\61\3\2\2\2\n\65\3\2\2\2\f9\3\2\2\2\16<\3\2\2\2\20B\3\2\2\2"+
		"\22G\3\2\2\2\24M\3\2\2\2\26W\3\2\2\2\30c\3\2\2\2\32g\3\2\2\2\34k\3\2\2"+
		"\2\36m\3\2\2\2 y\3\2\2\2\"$\5 \21\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3"+
		"\2\2\2&\'\3\2\2\2\'(\7\2\2\3(\3\3\2\2\2)*\7\33\2\2*+\7\3\2\2+,\7\31\2"+
		"\2,\5\3\2\2\2-.\7\33\2\2./\7\4\2\2/\60\7\32\2\2\60\7\3\2\2\2\61\62\7\33"+
		"\2\2\62\63\7\5\2\2\63\64\7\36\2\2\64\t\3\2\2\2\65\66\7\33\2\2\66\67\7"+
		"\6\2\2\678\7\36\2\28\13\3\2\2\29:\7\33\2\2:;\7\6\2\2;\r\3\2\2\2<=\7\33"+
		"\2\2=>\7\7\2\2>?\7\33\2\2?@\7\6\2\2@A\7\36\2\2A\17\3\2\2\2BC\7\33\2\2"+
		"CD\7\7\2\2DE\7\33\2\2EF\7\6\2\2F\21\3\2\2\2GH\7\34\2\2HI\7\6\2\2IJ\7\b"+
		"\2\2JK\5\16\b\2KL\7\t\2\2L\23\3\2\2\2MN\7\n\2\2NO\7\33\2\2OQ\7\13\2\2"+
		"PR\5\30\r\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\f\2"+
		"\2V\25\3\2\2\2WX\7\r\2\2XY\7\33\2\2YZ\7\16\2\2Z[\7\33\2\2[]\7\13\2\2\\"+
		"^\5\32\16\2]\\\3\2\2\2^_\3\2\2\2_]\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\7\f\2"+
		"\2b\27\3\2\2\2cd\7\33\2\2de\5\36\20\2ef\7\31\2\2f\31\3\2\2\2gh\7\33\2"+
		"\2hi\5\34\17\2ij\7\35\2\2j\33\3\2\2\2kl\t\2\2\2l\35\3\2\2\2mn\t\3\2\2"+
		"n\37\3\2\2\2oz\5\4\3\2pz\5\6\4\2qz\5\b\5\2rz\5\n\6\2sz\5\f\7\2tz\5\16"+
		"\b\2uz\5\20\t\2vz\5\22\n\2wz\5\24\13\2xz\5\26\f\2yo\3\2\2\2yp\3\2\2\2"+
		"yq\3\2\2\2yr\3\2\2\2ys\3\2\2\2yt\3\2\2\2yu\3\2\2\2yv\3\2\2\2yw\3\2\2\2"+
		"yx\3\2\2\2z!\3\2\2\2\6%S_y";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}