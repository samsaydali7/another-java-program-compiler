// Generated from C:/Users/Samer/Documents/GitHub/Compiler-MapReduce/src\ShuffleResult.g4 by ANTLR 4.7.2
package FileStream.ShuffleResult;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShuffleResultParser}.
 */
public interface ShuffleResultListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(ShuffleResultParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(ShuffleResultParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(ShuffleResultParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(ShuffleResultParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#headerline}.
	 * @param ctx the parse tree
	 */
	void enterHeaderline(ShuffleResultParser.HeaderlineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#headerline}.
	 * @param ctx the parse tree
	 */
	void exitHeaderline(ShuffleResultParser.HeaderlineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(ShuffleResultParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(ShuffleResultParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#keys}.
	 * @param ctx the parse tree
	 */
	void enterKeys(ShuffleResultParser.KeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#keys}.
	 * @param ctx the parse tree
	 */
	void exitKeys(ShuffleResultParser.KeysContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#values}.
	 * @param ctx the parse tree
	 */
	void enterValues(ShuffleResultParser.ValuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#values}.
	 * @param ctx the parse tree
	 */
	void exitValues(ShuffleResultParser.ValuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(ShuffleResultParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(ShuffleResultParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ShuffleResultParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ShuffleResultParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#arrayValue}.
	 * @param ctx the parse tree
	 */
	void enterArrayValue(ShuffleResultParser.ArrayValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#arrayValue}.
	 * @param ctx the parse tree
	 */
	void exitArrayValue(ShuffleResultParser.ArrayValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#arrayEntry}.
	 * @param ctx the parse tree
	 */
	void enterArrayEntry(ShuffleResultParser.ArrayEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#arrayEntry}.
	 * @param ctx the parse tree
	 */
	void exitArrayEntry(ShuffleResultParser.ArrayEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShuffleResultParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEntry(ShuffleResultParser.EntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShuffleResultParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEntry(ShuffleResultParser.EntryContext ctx);
}