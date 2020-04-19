package net.travitz.lang.toy.util;

import net.travitz.lang.toy.ToyLangBaseListener;
import net.travitz.lang.toy.ToyLangLexer;

import static net.travitz.lang.toy.ToyLangParser.*;


import java.util.StringJoiner;

public class FileScopeCtxFiller extends ToyLangBaseListener {

    StackSymbolTable symbols;

    public FileScopeCtxFiller() {
        this.symbols = new StackSymbolTable();
    }

    public FileScopeCtxFiller(StackSymbolTable symbols) {
        this.symbols = symbols;
    }

    @Override
    public void enterFunction(FunctionContext ctx) {
        // TODO May want to use this code later if I ever decide to create a function type

        //System.out.println(ctx.getText());

        String returnType = ctx.type() == null ? ctx.VOID().getText() : ctx.type().getText();
        StringJoiner listOfParams = new StringJoiner(", ", "(", ")->(" + returnType + ")");

        // while node still exists (there are arguments left), node = node.next
        for (Params_listContext node = ctx.params().params_list(); node != null; node = node.params_list()) {
            // add each parameter to our list
            listOfParams.add(node.param().type().getText());
        }

        // Looks like you have to decide for yourself what rule was taken based off of child size and if values are null
        symbols.addToFileScope(ctx.getToken(ToyLangLexer.ID,0).getText(), listOfParams.toString());
    }

    public StackSymbolTable getSymbols() {
        return symbols;
    }
}
