// Generated from C:/Users/Samer/Documents/GitHub/Compiler-MapReduce/src\MapResult.g4 by ANTLR 4.7.2
package FileStream.MapResult;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MapResultParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		TEXT=10, WS=11;
	public static final int
		RULE_file = 0, RULE_header = 1, RULE_headerline = 2, RULE_line = 3, RULE_keys = 4, 
		RULE_key = 5, RULE_value = 6, RULE_entry = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "header", "headerline", "line", "keys", "key", "value", "entry"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "','", "']'", "'\r'", "'\n'", "'<'", "':'", "'>'", "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "TEXT", "WS"
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
	public String getGrammarFileName() { return "MapResult.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MapResultParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0 || _la==TEXT) {
				{
				setState(16);
				header();
				}
			}

			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(19);
				line();
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class HeaderContext extends ParserRuleContext {
		public HeaderlineContext headerline() {
			return getRuleContext(HeaderlineContext.class,0);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			headerline();
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

	public static class HeaderlineContext extends ParserRuleContext {
		public List<EntryContext> entry() {
			return getRuleContexts(EntryContext.class);
		}
		public EntryContext entry(int i) {
			return getRuleContext(EntryContext.class,i);
		}
		public HeaderlineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headerline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).enterHeaderline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).exitHeaderline(this);
		}
	}

	public final HeaderlineContext headerline() throws RecognitionException {
		HeaderlineContext _localctx = new HeaderlineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_headerline);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(27);
				match(T__0);
				}
			}

			setState(30);
			entry();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(31);
				match(T__1);
				setState(32);
				entry();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(38);
				match(T__2);
				}
			}

			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(41);
				match(T__3);
				}
			}

			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(44);
				match(T__4);
				}
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

	public static class LineContext extends ParserRuleContext {
		public KeysContext keys() {
			return getRuleContext(KeysContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(T__5);
			setState(48);
			keys();
			setState(49);
			match(T__6);
			setState(50);
			value();
			setState(51);
			match(T__7);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(52);
				match(T__3);
				}
			}

			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(55);
				match(T__4);
				}
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

	public static class KeysContext extends ParserRuleContext {
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public KeysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keys; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).enterKeys(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).exitKeys(this);
		}
	}

	public final KeysContext keys() throws RecognitionException {
		KeysContext _localctx = new KeysContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_keys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__0);
			setState(59);
			key();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(60);
				match(T__1);
				setState(61);
				key();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(T__2);
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

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(MapResultParser.TEXT, 0); }
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).exitKey(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_key);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
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

	public static class ValueContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(MapResultParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(MapResultParser.TEXT, i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(71);
				match(T__8);
				}
			}

			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(74);
				match(T__0);
				}
			}

			setState(77);
			match(TEXT);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(78);
				match(T__1);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(79);
					match(T__8);
					}
				}

				setState(82);
				match(TEXT);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(88);
				match(T__8);
				}
				break;
			}
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(91);
				match(T__2);
				}
			}

			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(94);
				match(T__8);
				}
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

	public static class EntryContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(MapResultParser.TEXT, 0); }
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).enterEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapResultListener ) ((MapResultListener)listener).exitEntry(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_entry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\rf\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\5\2\24\n\2\3\2"+
		"\7\2\27\n\2\f\2\16\2\32\13\2\3\3\3\3\3\4\5\4\37\n\4\3\4\3\4\3\4\7\4$\n"+
		"\4\f\4\16\4\'\13\4\3\4\5\4*\n\4\3\4\5\4-\n\4\3\4\5\4\60\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\58\n\5\3\5\5\5;\n\5\3\6\3\6\3\6\3\6\7\6A\n\6\f\6\16\6"+
		"D\13\6\3\6\3\6\3\7\3\7\3\b\5\bK\n\b\3\b\5\bN\n\b\3\b\3\b\3\b\5\bS\n\b"+
		"\3\b\7\bV\n\b\f\b\16\bY\13\b\3\b\5\b\\\n\b\3\b\5\b_\n\b\3\b\5\bb\n\b\3"+
		"\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2n\2\23\3\2\2\2\4\33\3\2\2\2\6"+
		"\36\3\2\2\2\b\61\3\2\2\2\n<\3\2\2\2\fG\3\2\2\2\16J\3\2\2\2\20c\3\2\2\2"+
		"\22\24\5\4\3\2\23\22\3\2\2\2\23\24\3\2\2\2\24\30\3\2\2\2\25\27\5\b\5\2"+
		"\26\25\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\3\3\2\2\2"+
		"\32\30\3\2\2\2\33\34\5\6\4\2\34\5\3\2\2\2\35\37\7\3\2\2\36\35\3\2\2\2"+
		"\36\37\3\2\2\2\37 \3\2\2\2 %\5\20\t\2!\"\7\4\2\2\"$\5\20\t\2#!\3\2\2\2"+
		"$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&)\3\2\2\2\'%\3\2\2\2(*\7\5\2\2)(\3\2\2"+
		"\2)*\3\2\2\2*,\3\2\2\2+-\7\6\2\2,+\3\2\2\2,-\3\2\2\2-/\3\2\2\2.\60\7\7"+
		"\2\2/.\3\2\2\2/\60\3\2\2\2\60\7\3\2\2\2\61\62\7\b\2\2\62\63\5\n\6\2\63"+
		"\64\7\t\2\2\64\65\5\16\b\2\65\67\7\n\2\2\668\7\6\2\2\67\66\3\2\2\2\67"+
		"8\3\2\2\28:\3\2\2\29;\7\7\2\2:9\3\2\2\2:;\3\2\2\2;\t\3\2\2\2<=\7\3\2\2"+
		"=B\5\f\7\2>?\7\4\2\2?A\5\f\7\2@>\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2"+
		"CE\3\2\2\2DB\3\2\2\2EF\7\5\2\2F\13\3\2\2\2GH\7\f\2\2H\r\3\2\2\2IK\7\13"+
		"\2\2JI\3\2\2\2JK\3\2\2\2KM\3\2\2\2LN\7\3\2\2ML\3\2\2\2MN\3\2\2\2NO\3\2"+
		"\2\2OW\7\f\2\2PR\7\4\2\2QS\7\13\2\2RQ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TV\7"+
		"\f\2\2UP\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X[\3\2\2\2YW\3\2\2\2Z\\"+
		"\7\13\2\2[Z\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2]_\7\5\2\2^]\3\2\2\2^_\3\2\2"+
		"\2_a\3\2\2\2`b\7\13\2\2a`\3\2\2\2ab\3\2\2\2b\17\3\2\2\2cd\7\f\2\2d\21"+
		"\3\2\2\2\23\23\30\36%),/\67:BJMRW[^a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}