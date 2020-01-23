package net.travitz.lang.toy;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

/**
 * Test Parsing simple toy files
 *
 * Perform a sanity check to ensure the parser created by the Toylang.g4 grammar specification can parse simple toy
 * files without throwing unexpected runtime errors
 */
public class ParsingTest {

    String arraysToyFile = "ToyTestCode\\arrays.toy";
    String expressionTestsToyFile = "ToyTestCode\\ExpressionTests.toy";
    String moreFunctionsToyFile = "ToyTestCode\\MoreFunctions.toy";
    String oneFunctionToyFile = "ToyTestCode\\OneFunction.toy";
    String stringsToyFile = "ToyTestCode\\strings.toy";


    @Test
    public void testParseArrays() throws IOException {
        CharStream fileStream = CharStreams.fromFileName(arraysToyFile);
        ToyLangLexer toyLex = new ToyLangLexer(fileStream);
        CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
        ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
        toyParse.module();
    }

    @Test
    public void testParseExpression() throws IOException {
        CharStream fileStream = CharStreams.fromFileName(expressionTestsToyFile);
        ToyLangLexer toyLex = new ToyLangLexer(fileStream);
        CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
        ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
        toyParse.module();
    }

    @Test //Currently not failing TODO
    public void testParseMoreFunctions() throws IOException {
        CharStream fileStream = CharStreams.fromFileName(moreFunctionsToyFile);
        ToyLangLexer toyLex = new ToyLangLexer(fileStream);
        CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
        ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
        toyParse.module();
    }

    @Test
    public void testParseOneFunction() throws IOException {
        CharStream fileStream = CharStreams.fromFileName(oneFunctionToyFile);
        ToyLangLexer toyLex = new ToyLangLexer(fileStream);
        CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
        ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
        toyParse.module();
    }

    @Test
    public void testParseStrings() throws IOException {
        CharStream fileStream = CharStreams.fromFileName(stringsToyFile);
        ToyLangLexer toyLex = new ToyLangLexer(fileStream);
        CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
        ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
        toyParse.module();
    }
}
