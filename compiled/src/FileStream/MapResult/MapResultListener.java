// Generated from C:/Users/Samer/Documents/GitHub/Compiler-MapReduce/src\MapResult.g4 by ANTLR 4.7.2
package FileStream.MapResult;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MapResultParser}.
 */
public interface MapResultListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MapResultParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(MapResultParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapResultParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(MapResultParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapResultParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(MapResultParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapResultParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(MapResultParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapResultParser#headerline}.
	 * @param ctx the parse tree
	 */
	void enterHeaderline(MapResultParser.HeaderlineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapResultParser#headerline}.
	 * @param ctx the parse tree
	 */
	void exitHeaderline(MapResultParser.HeaderlineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapResultParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(MapResultParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapResultParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(MapResultParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapResultParser#keys}.
	 * @param ctx the parse tree
	 */
	void enterKeys(MapResultParser.KeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapResultParser#keys}.
	 * @param ctx the parse tree
	 */
	void exitKeys(MapResultParser.KeysContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapResultParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(MapResultParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapResultParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(MapResultParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapResultParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(MapResultParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapResultParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(MapResultParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapResultParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEntry(MapResultParser.EntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapResultParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEntry(MapResultParser.EntryContext ctx);
}