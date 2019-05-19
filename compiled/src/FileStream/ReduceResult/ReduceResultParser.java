// Generated from C:/Users/Samer/Documents/GitHub/Compiler-MapReduce/src\ReduceResult.g4 by ANTLR 4.7.2
package FileStream.ReduceResult;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ReduceResultParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, IDENT=12, WS=13;
	public static final int
		RULE_file = 0, RULE_header = 1, RULE_headerline = 2, RULE_line = 3, RULE_keys = 4, 
		RULE_key = 5, RULE_value = 6, RULE_simple = 7, RULE_object = 8, RULE_objectEntry = 9, 
		RULE_objectKey = 10, RULE_objectValue = 11, RULE_array = 12, RULE_arrayElemnt = 13, 
		RULE_element = 14, RULE_entry = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "header", "headerline", "line", "keys", "key", "value", "simple", 
			"object", "objectEntry", "objectKey", "objectValue", "array", "arrayElemnt", 
			"element", "entry"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "','", "' '", "']'", "'\r'", "'\n'", "'<'", "':'", "'>'", 
			"'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"IDENT", "WS"
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
	public String getGrammarFileName() { return "ReduceResult.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ReduceResultParser(TokenStream input) {
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
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0 || _la==IDENT) {
				{
				setState(32);
				header();
				}
			}

			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				line();
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__6 );
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
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitHeader(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
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
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterHeaderline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitHeaderline(this);
		}
	}

	public final HeaderlineContext headerline() throws RecognitionException {
		HeaderlineContext _localctx = new HeaderlineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_headerline);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(42);
				match(T__0);
				}
			}

			setState(45);
			entry();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(46);
				match(T__1);
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(47);
					match(T__2);
					}
				}

				setState(50);
				entry();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(56);
				match(T__3);
				}
			}

			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(59);
				match(T__4);
				}
			}

			setState(62);
			match(T__5);
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
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__6);
			setState(65);
			keys();
			setState(66);
			match(T__7);
			setState(67);
			value();
			setState(68);
			match(T__8);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(69);
				match(T__4);
				}
			}

			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(72);
				match(T__5);
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
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterKeys(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitKeys(this);
		}
	}

	public final KeysContext keys() throws RecognitionException {
		KeysContext _localctx = new KeysContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_keys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__0);
			setState(76);
			key();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(77);
				match(T__1);
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(78);
					match(T__2);
					}
				}

				setState(81);
				key();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
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

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ReduceResultParser.IDENT, 0); }
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitKey(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_key);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(IDENT);
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
		public SimpleContext simple() {
			return getRuleContext(SimpleContext.class,0);
		}
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		try {
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				simple();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				object();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				array();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class SimpleContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ReduceResultParser.IDENT, 0); }
		public SimpleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitSimple(this);
		}
	}

	public final SimpleContext simple() throws RecognitionException {
		SimpleContext _localctx = new SimpleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_simple);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(IDENT);
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

	public static class ObjectContext extends ParserRuleContext {
		public List<ObjectEntryContext> objectEntry() {
			return getRuleContexts(ObjectEntryContext.class);
		}
		public ObjectEntryContext objectEntry(int i) {
			return getRuleContext(ObjectEntryContext.class,i);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__9);
			setState(99);
			objectEntry();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(100);
				match(T__1);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(101);
					match(T__2);
					}
				}

				setState(104);
				objectEntry();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
			match(T__10);
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

	public static class ObjectEntryContext extends ParserRuleContext {
		public ObjectKeyContext objectKey() {
			return getRuleContext(ObjectKeyContext.class,0);
		}
		public ObjectValueContext objectValue() {
			return getRuleContext(ObjectValueContext.class,0);
		}
		public ObjectEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterObjectEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitObjectEntry(this);
		}
	}

	public final ObjectEntryContext objectEntry() throws RecognitionException {
		ObjectEntryContext _localctx = new ObjectEntryContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_objectEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			objectKey();
			setState(113);
			match(T__7);
			setState(114);
			objectValue();
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

	public static class ObjectKeyContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ReduceResultParser.IDENT, 0); }
		public ObjectKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterObjectKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitObjectKey(this);
		}
	}

	public final ObjectKeyContext objectKey() throws RecognitionException {
		ObjectKeyContext _localctx = new ObjectKeyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_objectKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(IDENT);
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

	public static class ObjectValueContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ReduceResultParser.IDENT, 0); }
		public ObjectValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterObjectValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitObjectValue(this);
		}
	}

	public final ObjectValueContext objectValue() throws RecognitionException {
		ObjectValueContext _localctx = new ObjectValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_objectValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(IDENT);
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

	public static class ArrayContext extends ParserRuleContext {
		public List<ArrayElemntContext> arrayElemnt() {
			return getRuleContexts(ArrayElemntContext.class);
		}
		public ArrayElemntContext arrayElemnt(int i) {
			return getRuleContext(ArrayElemntContext.class,i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__0);
			setState(121);
			arrayElemnt();
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(122);
				match(T__1);
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(123);
					match(T__2);
					}
				}

				setState(126);
				arrayElemnt();
				}
				}
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(132);
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

	public static class ArrayElemntContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public ArrayElemntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElemnt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterArrayElemnt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitArrayElemnt(this);
		}
	}

	public final ArrayElemntContext arrayElemnt() throws RecognitionException {
		ArrayElemntContext _localctx = new ArrayElemntContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arrayElemnt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__0);
			setState(135);
			element();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(136);
				match(T__1);
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(137);
					match(T__2);
					}
				}

				setState(140);
				element();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
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

	public static class ElementContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ReduceResultParser.IDENT, 0); }
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitElement(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(IDENT);
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
		public TerminalNode IDENT() { return getToken(ReduceResultParser.IDENT, 0); }
		public EntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).enterEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReduceResultListener ) ((ReduceResultListener)listener).exitEntry(this);
		}
	}

	public final EntryContext entry() throws RecognitionException {
		EntryContext _localctx = new EntryContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_entry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(IDENT);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17\u009b\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\5\2"+
		"$\n\2\3\2\6\2\'\n\2\r\2\16\2(\3\3\3\3\3\4\5\4.\n\4\3\4\3\4\3\4\5\4\63"+
		"\n\4\3\4\7\4\66\n\4\f\4\16\49\13\4\3\4\5\4<\n\4\3\4\5\4?\n\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5I\n\5\3\5\5\5L\n\5\3\6\3\6\3\6\3\6\5\6R\n\6"+
		"\3\6\7\6U\n\6\f\6\16\6X\13\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\5\ba\n\b\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\5\ni\n\n\3\n\7\nl\n\n\f\n\16\no\13\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\5\16\177\n\16\3\16"+
		"\7\16\u0082\n\16\f\16\16\16\u0085\13\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\5\17\u008d\n\17\3\17\7\17\u0090\n\17\f\17\16\17\u0093\13\17\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \2\2\2\u009d\2#\3\2\2\2\4*\3\2\2\2\6-\3\2\2\2\bB\3\2\2\2\nM\3\2\2\2\f"+
		"[\3\2\2\2\16`\3\2\2\2\20b\3\2\2\2\22d\3\2\2\2\24r\3\2\2\2\26v\3\2\2\2"+
		"\30x\3\2\2\2\32z\3\2\2\2\34\u0088\3\2\2\2\36\u0096\3\2\2\2 \u0098\3\2"+
		"\2\2\"$\5\4\3\2#\"\3\2\2\2#$\3\2\2\2$&\3\2\2\2%\'\5\b\5\2&%\3\2\2\2\'"+
		"(\3\2\2\2(&\3\2\2\2()\3\2\2\2)\3\3\2\2\2*+\5\6\4\2+\5\3\2\2\2,.\7\3\2"+
		"\2-,\3\2\2\2-.\3\2\2\2./\3\2\2\2/\67\5 \21\2\60\62\7\4\2\2\61\63\7\5\2"+
		"\2\62\61\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\66\5 \21\2\65\60\3\2\2"+
		"\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28;\3\2\2\29\67\3\2\2\2:<\7\6\2"+
		"\2;:\3\2\2\2;<\3\2\2\2<>\3\2\2\2=?\7\7\2\2>=\3\2\2\2>?\3\2\2\2?@\3\2\2"+
		"\2@A\7\b\2\2A\7\3\2\2\2BC\7\t\2\2CD\5\n\6\2DE\7\n\2\2EF\5\16\b\2FH\7\13"+
		"\2\2GI\7\7\2\2HG\3\2\2\2HI\3\2\2\2IK\3\2\2\2JL\7\b\2\2KJ\3\2\2\2KL\3\2"+
		"\2\2L\t\3\2\2\2MN\7\3\2\2NV\5\f\7\2OQ\7\4\2\2PR\7\5\2\2QP\3\2\2\2QR\3"+
		"\2\2\2RS\3\2\2\2SU\5\f\7\2TO\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3"+
		"\2\2\2XV\3\2\2\2YZ\7\6\2\2Z\13\3\2\2\2[\\\7\16\2\2\\\r\3\2\2\2]a\5\20"+
		"\t\2^a\5\22\n\2_a\5\32\16\2`]\3\2\2\2`^\3\2\2\2`_\3\2\2\2a\17\3\2\2\2"+
		"bc\7\16\2\2c\21\3\2\2\2de\7\f\2\2em\5\24\13\2fh\7\4\2\2gi\7\5\2\2hg\3"+
		"\2\2\2hi\3\2\2\2ij\3\2\2\2jl\5\24\13\2kf\3\2\2\2lo\3\2\2\2mk\3\2\2\2m"+
		"n\3\2\2\2np\3\2\2\2om\3\2\2\2pq\7\r\2\2q\23\3\2\2\2rs\5\26\f\2st\7\n\2"+
		"\2tu\5\30\r\2u\25\3\2\2\2vw\7\16\2\2w\27\3\2\2\2xy\7\16\2\2y\31\3\2\2"+
		"\2z{\7\3\2\2{\u0083\5\34\17\2|~\7\4\2\2}\177\7\5\2\2~}\3\2\2\2~\177\3"+
		"\2\2\2\177\u0080\3\2\2\2\u0080\u0082\5\34\17\2\u0081|\3\2\2\2\u0082\u0085"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0086\u0087\7\6\2\2\u0087\33\3\2\2\2\u0088\u0089\7\3\2"+
		"\2\u0089\u0091\5\36\20\2\u008a\u008c\7\4\2\2\u008b\u008d\7\5\2\2\u008c"+
		"\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\5\36"+
		"\20\2\u008f\u008a\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\7\6"+
		"\2\2\u0095\35\3\2\2\2\u0096\u0097\7\16\2\2\u0097\37\3\2\2\2\u0098\u0099"+
		"\7\16\2\2\u0099!\3\2\2\2\24#(-\62\67;>HKQV`hm~\u0083\u008c\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}