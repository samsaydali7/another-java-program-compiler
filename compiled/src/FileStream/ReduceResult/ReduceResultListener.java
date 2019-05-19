// Generated from C:/Users/Samer/Documents/GitHub/Compiler-MapReduce/src\ReduceResult.g4 by ANTLR 4.7.2
package FileStream.ReduceResult;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ReduceResultParser}.
 */
public interface ReduceResultListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(ReduceResultParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(ReduceResultParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(ReduceResultParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(ReduceResultParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#headerline}.
	 * @param ctx the parse tree
	 */
	void enterHeaderline(ReduceResultParser.HeaderlineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#headerline}.
	 * @param ctx the parse tree
	 */
	void exitHeaderline(ReduceResultParser.HeaderlineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(ReduceResultParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(ReduceResultParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#keys}.
	 * @param ctx the parse tree
	 */
	void enterKeys(ReduceResultParser.KeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#keys}.
	 * @param ctx the parse tree
	 */
	void exitKeys(ReduceResultParser.KeysContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(ReduceResultParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(ReduceResultParser.KeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ReduceResultParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ReduceResultParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#simple}.
	 * @param ctx the parse tree
	 */
	void enterSimple(ReduceResultParser.SimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#simple}.
	 * @param ctx the parse tree
	 */
	void exitSimple(ReduceResultParser.SimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(ReduceResultParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(ReduceResultParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#objectEntry}.
	 * @param ctx the parse tree
	 */
	void enterObjectEntry(ReduceResultParser.ObjectEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#objectEntry}.
	 * @param ctx the parse tree
	 */
	void exitObjectEntry(ReduceResultParser.ObjectEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#objectKey}.
	 * @param ctx the parse tree
	 */
	void enterObjectKey(ReduceResultParser.ObjectKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#objectKey}.
	 * @param ctx the parse tree
	 */
	void exitObjectKey(ReduceResultParser.ObjectKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#objectValue}.
	 * @param ctx the parse tree
	 */
	void enterObjectValue(ReduceResultParser.ObjectValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#objectValue}.
	 * @param ctx the parse tree
	 */
	void exitObjectValue(ReduceResultParser.ObjectValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(ReduceResultParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(ReduceResultParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#arrayElemnt}.
	 * @param ctx the parse tree
	 */
	void enterArrayElemnt(ReduceResultParser.ArrayElemntContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#arrayElemnt}.
	 * @param ctx the parse tree
	 */
	void exitArrayElemnt(ReduceResultParser.ArrayElemntContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(ReduceResultParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(ReduceResultParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ReduceResultParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEntry(ReduceResultParser.EntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ReduceResultParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEntry(ReduceResultParser.EntryContext ctx);
}