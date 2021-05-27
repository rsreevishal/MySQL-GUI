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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, LIST=8, ULIST=9, 
		ID=10, VAR=11, TEXT=12, UID=13, WS=14;
	public static final int
		RULE_prog = 0, RULE_add = 1, RULE_update = 2, RULE_delete = 3, RULE_view = 4, 
		RULE_viewAll = 5, RULE_colView = 6, RULE_colViewAll = 7, RULE_storeColView = 8, 
		RULE_expr = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "add", "update", "delete", "view", "viewAll", "colView", "colViewAll", 
			"storeColView", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'^'", "'-'", "'='", "'.'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "LIST", "ULIST", "ID", 
			"VAR", "TEXT", "UID", "WS"
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
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				expr();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID || _la==VAR );
			setState(25);
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
			setState(27);
			match(ID);
			setState(28);
			match(T__0);
			setState(29);
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
			setState(31);
			match(ID);
			setState(32);
			match(T__1);
			setState(33);
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
			setState(35);
			match(ID);
			setState(36);
			match(T__2);
			setState(37);
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
			setState(39);
			match(ID);
			setState(40);
			match(T__3);
			setState(41);
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
			setState(43);
			match(ID);
			setState(44);
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
			setState(46);
			match(ID);
			setState(47);
			match(T__4);
			setState(48);
			match(ID);
			setState(49);
			match(T__3);
			setState(50);
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
			setState(52);
			match(ID);
			setState(53);
			match(T__4);
			setState(54);
			match(ID);
			setState(55);
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
			setState(57);
			match(VAR);
			setState(58);
			match(T__3);
			setState(59);
			match(T__5);
			setState(60);
			colView();
			setState(61);
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
		enterRule(_localctx, 18, RULE_expr);
		try {
			setState(71);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				add();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				update();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				delete();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				view();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(67);
				viewAll();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(68);
				colView();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(69);
				colViewAll();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(70);
				storeColView();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\20L\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\6\2\30\n\2\r\2\16\2\31\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\5\13J\n\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2\2I\2\27\3\2"+
		"\2\2\4\35\3\2\2\2\6!\3\2\2\2\b%\3\2\2\2\n)\3\2\2\2\f-\3\2\2\2\16\60\3"+
		"\2\2\2\20\66\3\2\2\2\22;\3\2\2\2\24I\3\2\2\2\26\30\5\24\13\2\27\26\3\2"+
		"\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\33\3\2\2\2\33\34\7\2"+
		"\2\3\34\3\3\2\2\2\35\36\7\f\2\2\36\37\7\3\2\2\37 \7\n\2\2 \5\3\2\2\2!"+
		"\"\7\f\2\2\"#\7\4\2\2#$\7\13\2\2$\7\3\2\2\2%&\7\f\2\2&\'\7\5\2\2\'(\7"+
		"\17\2\2(\t\3\2\2\2)*\7\f\2\2*+\7\6\2\2+,\7\17\2\2,\13\3\2\2\2-.\7\f\2"+
		"\2./\7\6\2\2/\r\3\2\2\2\60\61\7\f\2\2\61\62\7\7\2\2\62\63\7\f\2\2\63\64"+
		"\7\6\2\2\64\65\7\17\2\2\65\17\3\2\2\2\66\67\7\f\2\2\678\7\7\2\289\7\f"+
		"\2\29:\7\6\2\2:\21\3\2\2\2;<\7\r\2\2<=\7\6\2\2=>\7\b\2\2>?\5\16\b\2?@"+
		"\7\t\2\2@\23\3\2\2\2AJ\5\4\3\2BJ\5\6\4\2CJ\5\b\5\2DJ\5\n\6\2EJ\5\f\7\2"+
		"FJ\5\16\b\2GJ\5\20\t\2HJ\5\22\n\2IA\3\2\2\2IB\3\2\2\2IC\3\2\2\2ID\3\2"+
		"\2\2IE\3\2\2\2IF\3\2\2\2IG\3\2\2\2IH\3\2\2\2J\25\3\2\2\2\4\31I";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}