package net.travitz.lang.toy;

import net.travitz.lang.toy.parsing.FileScopeCtxFiller;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class RunToy {
    public static void main(String[] args) {
        try {
            CharStream fileStream = CharStreams.fromFileName(args[0]);
            ToyLangLexer toyLex = new ToyLangLexer(fileStream);
            CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
            ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
            ParseTreeWalker b = new ParseTreeWalker();
            b.walk(new FileScopeCtxFiller(), toyParse.module());
            toyParse.addParseListener(new FileScopeCtxFiller());
            System.out.println(toyParse.module().toStringTree(toyParse));
            System.out.println("hi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
