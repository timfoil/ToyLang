package net.travitz.lang.toy;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class RunToy {
    public static void main(String[] args) {
        try {
            CharStream fileStream = CharStreams.fromFileName(args[0]);
            ToyLangLexer toyLex = new ToyLangLexer(fileStream);
            CommonTokenStream toyTokenStream = new CommonTokenStream(toyLex);
            ToyLangParser toyParse = new ToyLangParser(toyTokenStream);
            System.out.println(toyParse.module().toStringTree(toyParse));
            System.out.println("hi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
