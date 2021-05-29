// Generated from antlr/Crudql.g4 by ANTLR 4.9.2

	package antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CrudqlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		LIST=18, ULIST=19, ID=20, VAR=21, TEXT=22, UID=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"LIST", "ULIST", "ID", "VAR", "TEXT", "UID", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'^'", "'-'", "'='", "'.'", "'('", "')'", "'CREATE FORM'", 
			"'['", "']'", "'TEXT'", "'NUMBER'", "'TEXTAREA'", "'RADIO'", "'CHECKBOX'", 
			"'PASSWORD'", "'EMAIL'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "LIST", "ULIST", "ID", "VAR", "TEXT", 
			"UID", "WS"
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


	public CrudqlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Crudql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00bd\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u0089\n\23\f\23\16\23\u008c"+
		"\13\23\3\23\3\23\3\24\3\24\3\24\3\24\7\24\u0094\n\24\f\24\16\24\u0097"+
		"\13\24\3\24\3\24\3\25\3\25\7\25\u009d\n\25\f\25\16\25\u00a0\13\25\3\26"+
		"\3\26\3\26\7\26\u00a5\n\26\f\26\16\26\u00a8\13\26\3\27\3\27\6\27\u00ac"+
		"\n\27\r\27\16\27\u00ad\3\27\3\27\3\30\6\30\u00b3\n\30\r\30\16\30\u00b4"+
		"\3\31\6\31\u00b8\n\31\r\31\16\31\u00b9\3\31\3\31\2\2\32\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3\2c|\4"+
		"\2\f\f\17\17\3\2\62;\4\2\13\f\"\"\2\u00c3\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2"+
		"\5\65\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r=\3\2\2\2\17?\3\2\2"+
		"\2\21A\3\2\2\2\23M\3\2\2\2\25O\3\2\2\2\27Q\3\2\2\2\31V\3\2\2\2\33]\3\2"+
		"\2\2\35f\3\2\2\2\37l\3\2\2\2!u\3\2\2\2#~\3\2\2\2%\u0084\3\2\2\2\'\u008f"+
		"\3\2\2\2)\u009a\3\2\2\2+\u00a1\3\2\2\2-\u00a9\3\2\2\2/\u00b2\3\2\2\2\61"+
		"\u00b7\3\2\2\2\63\64\7-\2\2\64\4\3\2\2\2\65\66\7`\2\2\66\6\3\2\2\2\67"+
		"8\7/\2\28\b\3\2\2\29:\7?\2\2:\n\3\2\2\2;<\7\60\2\2<\f\3\2\2\2=>\7*\2\2"+
		">\16\3\2\2\2?@\7+\2\2@\20\3\2\2\2AB\7E\2\2BC\7T\2\2CD\7G\2\2DE\7C\2\2"+
		"EF\7V\2\2FG\7G\2\2GH\7\"\2\2HI\7H\2\2IJ\7Q\2\2JK\7T\2\2KL\7O\2\2L\22\3"+
		"\2\2\2MN\7]\2\2N\24\3\2\2\2OP\7_\2\2P\26\3\2\2\2QR\7V\2\2RS\7G\2\2ST\7"+
		"Z\2\2TU\7V\2\2U\30\3\2\2\2VW\7P\2\2WX\7W\2\2XY\7O\2\2YZ\7D\2\2Z[\7G\2"+
		"\2[\\\7T\2\2\\\32\3\2\2\2]^\7V\2\2^_\7G\2\2_`\7Z\2\2`a\7V\2\2ab\7C\2\2"+
		"bc\7T\2\2cd\7G\2\2de\7C\2\2e\34\3\2\2\2fg\7T\2\2gh\7C\2\2hi\7F\2\2ij\7"+
		"K\2\2jk\7Q\2\2k\36\3\2\2\2lm\7E\2\2mn\7J\2\2no\7G\2\2op\7E\2\2pq\7M\2"+
		"\2qr\7D\2\2rs\7Q\2\2st\7Z\2\2t \3\2\2\2uv\7R\2\2vw\7C\2\2wx\7U\2\2xy\7"+
		"U\2\2yz\7Y\2\2z{\7Q\2\2{|\7T\2\2|}\7F\2\2}\"\3\2\2\2~\177\7G\2\2\177\u0080"+
		"\7O\2\2\u0080\u0081\7C\2\2\u0081\u0082\7K\2\2\u0082\u0083\7N\2\2\u0083"+
		"$\3\2\2\2\u0084\u0085\7*\2\2\u0085\u008a\5-\27\2\u0086\u0087\7.\2\2\u0087"+
		"\u0089\5-\27\2\u0088\u0086\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d"+
		"\u008e\7+\2\2\u008e&\3\2\2\2\u008f\u0090\7*\2\2\u0090\u0095\5/\30\2\u0091"+
		"\u0092\7.\2\2\u0092\u0094\5-\27\2\u0093\u0091\3\2\2\2\u0094\u0097\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097"+
		"\u0095\3\2\2\2\u0098\u0099\7+\2\2\u0099(\3\2\2\2\u009a\u009e\t\2\2\2\u009b"+
		"\u009d\t\3\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2"+
		"\2\2\u009e\u009f\3\2\2\2\u009f*\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2"+
		"\7&\2\2\u00a2\u00a6\t\4\2\2\u00a3\u00a5\t\3\2\2\u00a4\u00a3\3\2\2\2\u00a5"+
		"\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7,\3\2\2\2"+
		"\u00a8\u00a6\3\2\2\2\u00a9\u00ab\7)\2\2\u00aa\u00ac\n\5\2\2\u00ab\u00aa"+
		"\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\7)\2\2\u00b0.\3\2\2\2\u00b1\u00b3\t\6\2\2\u00b2"+
		"\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\60\3\2\2\2\u00b6\u00b8\t\7\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00b9"+
		"\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\u00bc\b\31\2\2\u00bc\62\3\2\2\2\n\2\u008a\u0095\u009e\u00a6\u00ad\u00b4"+
		"\u00b9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}