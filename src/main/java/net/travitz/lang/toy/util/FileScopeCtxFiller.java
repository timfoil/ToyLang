package net.travitz.lang.toy.util;

import net.travitz.lang.toy.ToyLangBaseListener;
import net.travitz.lang.toy.ToyLangLexer;
import java.util.Stack;

import static net.travitz.lang.toy.ToyLangParser.*;


import java.util.StringJoiner;

public class FileScopeCtxFiller extends ToyLangBaseListener {

    ALSymbolTable symbols;

    public FileScopeCtxFiller() {
        this.symbols = new ALSymbolTable();
    }

    public FileScopeCtxFiller(ALSymbolTable symbols) {
        this.symbols = symbols;
    }

    @Override
    public void enterFunction(FunctionContext ctx) {
        // TODO May want to use this code later if I ever decide to create a function type

        //System.out.println(ctx.getText());

        String returnType = ctx.type() == null ? ctx.VOID().getText() : ctx.type().getText();
        StringJoiner listOfParams = new StringJoiner(", ", "(", ")->" + returnType);


        Stack<String> types = new Stack<>();

        // while node still exists (there are arguments left), node = node.next
        for (Params_listContext node = ctx.params().params_list(); node != null; node = node.params_list()) {
            // add each parameter to our list
            types.push(node.param().type().getText());
        }

        // We added arguments in reverse, so reverse it again to get the right order
        while (!types.empty()) {
            listOfParams.add(types.pop());
        }

        // Looks like you have to decide for yourself what rule was taken based off of child size and if values are null
        symbols.addToFileScope(ctx.getToken(ToyLangLexer.ID,0).getText(), listOfParams.toString());
    }

    public ALSymbolTable getSymbolTable() {
        return symbols;
    }
}
