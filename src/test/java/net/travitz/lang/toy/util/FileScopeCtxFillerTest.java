package net.travitz.lang.toy.util;
import net.travitz.lang.toy.ToyLangLexer;
import net.travitz.lang.toy.ToyLangParser;
import net.travitz.lang.toy.parsing.FileScopeCtxFiller;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Tests for the FileScopeCtxFiller class
 */
public class FileScopeCtxFillerTest {

    FileScopeCtxFiller listener;

    String arraysToyFile = "ToyTestCode/Arrays.toy";
    String expressionTestsToyFile = "ToyTestCode/ExpressionTests.toy";
    String oneFunctionToyFile = "ToyTestCode/OneFunction.toy";
    String moreFunctionsToyFile = "ToyTestCode/MoreFunctions.toy";
    String stringsToyFile = "ToyTestCode/Strings.toy";

    @Before
    public void setup() {
        listener = new FileScopeCtxFiller();
    }

    @Test
    public void arraysFileTest() throws Exception {
        testFile(arraysToyFile, listener);
        SymbolTable st = listener.getSymbolTable();
        assertEquals("()->void", st.lookupSymbol("arrays"));
        assertEquals("()->int", st.lookupSymbol("returnSomething"));
        assertEquals("()->void", st.lookupSymbol("returnNothing"));
        assertFalse(st.symbolExistsInCurrentScope("notARealFunction"));
    }

    @Test
    public void expressionFileTest() throws Exception {
        testFile(expressionTestsToyFile, listener);
        SymbolTable st = listener.getSymbolTable();
        assertEquals("()->int", st.lookupSymbol("returnSomething"));
        assertEquals("()->void", st.lookupSymbol("main"));
        assertFalse(st.symbolExistsInCurrentScope("notARealFunction"));
    }

    @Test
    public void functionFileTest() throws Exception {
        testFile(oneFunctionToyFile, listener);
        SymbolTable st = listener.getSymbolTable();
        assertEquals("()->void", st.lookupSymbol("main"));
        assertFalse(st.symbolExistsInCurrentScope("notARealFunction"));
    }

    @Test
    public void moreFunctionsFileTest() throws Exception {
        testFile(moreFunctionsToyFile, listener);
        SymbolTable st = listener.getSymbolTable();
        assertEquals("()->void", st.lookupSymbol("main"));
        assertEquals("(int, bool, string)->bool", st.lookupSymbol("fun2"));
        assertFalse(st.symbolExistsInCurrentScope("notARealFunction"));
    }

    @Test
    public void stringsFileTest() throws Exception {
        testFile(stringsToyFile, listener);
        SymbolTable st = listener.getSymbolTable();
        assertEquals("()->void", st.lookupSymbol("strings"));
        assertFalse(st.symbolExistsInCurrentScope("notARealFunction"));
    }

    /**
     * Attempt to identify functions and their return types in a ToyLang file
     *
     * Throws {@link ParseCancellationException} instead of silently printing to std-error if there is a problem during
     * parsing
     *
     * @param toyFile path to the toyFile to parse
     * @throws IOException thrown if there was an problem accessing the path given by arraysToyFile
     * @throws ParseCancellationException if an error was encountered during parsing
     */
    private static void testFile(String toyFile, FileScopeCtxFiller listener)
            throws IOException, ParseCancellationException {
        CharStream fileStream = CharStreams.fromFileName(toyFile);
        ToyLangLexer toyLex = new ToyLangLexer(fileStream);
        CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
        ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
        toyParse.setErrorHandler(new BailErrorStrategy());
        ParseTreeWalker b = new ParseTreeWalker();
        b.walk(listener, toyParse.module());
        toyParse.addParseListener(new FileScopeCtxFiller());
        //toyParse.module();
    }
}
