package net.travitz.lang.toy;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Test;

import java.io.IOException;

/**
 * Test Parsing simple toy files
 *
 * Perform a sanity check to ensure the parser created by the Toylang.g4 grammar specification can parse simple toy
 * files without throwing unexpected runtime errors
 */
public class ParsingTest {

    String syntaxErrorToyFile = "ToyTestCode\\SyntaxError.toy";
    String arraysToyFile = "ToyTestCode\\arrays.toy";
    String expressionTestsToyFile = "ToyTestCode\\ExpressionTests.toy";
    String moreFunctionsToyFile = "ToyTestCode\\MoreFunctions.toy";
    String oneFunctionToyFile = "ToyTestCode\\OneFunction.toy";
    String stringsToyFile = "ToyTestCode\\strings.toy";

    @Test(expected = ParseCancellationException.class)
    public void testFailure() throws IOException {
        testFile(syntaxErrorToyFile);
    }

    @Test
    public void testParseArrays() throws IOException {
        testFile(arraysToyFile);
    }

    @Test
    public void testParseMoreFunctions() throws IOException {
        testFile(moreFunctionsToyFile);
    }

    @Test
    public void testParseExpression() throws IOException {
        testFile(expressionTestsToyFile);
    }

    @Test
    public void testParseOneFunction() throws IOException {
        testFile(oneFunctionToyFile);
    }

    @Test
    public void testParseStrings() throws IOException {
        testFile(stringsToyFile);
    }

    /**
     * Parse a ToyLang file
     *
     * Throws {@link ParseCancellationException} instead of silently printing to std-error if there is a problem during
     * parsing
     *
     * @param arraysToyFile path to the toyFile to parse
     * @throws IOException thrown if there was an problem accessing the path given by arraysToyFile
     * @throws ParseCancellationException if an error was encountered during parsing
     */
    private static void testFile(String arraysToyFile) throws IOException, ParseCancellationException {
        CharStream fileStream = CharStreams.fromFileName(arraysToyFile);
        ToyLangLexer toyLex = new ToyLangLexer(fileStream);
        CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
        ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
        toyParse.setErrorHandler(new BailErrorStrategy());
        toyParse.module();
    }
}
