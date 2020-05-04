package net.travitz.lang.toy.parsing;

import net.travitz.lang.toy.ToyLangBaseVisitor;
import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.control.FunctionDefStmt;
import net.travitz.lang.toy.util.SymbolTable;

import java.util.ArrayList;

import static net.travitz.lang.toy.ToyLangParser.*;

public class ToyParser<T extends Statement> extends ToyLangBaseVisitor<T> {
    ArrayList<FunctionDefStmt> ast = new ArrayList<>();
    SymbolTable context;

    public ToyParser(SymbolTable context) {
        this.context = context;
    }

    @Override
    public T visitFunction(FunctionContext ctx) {
        FunctionDefStmt function = new FunctionDefStmt();

        if (ctx.scope() != null) {
            context.enterScope();
            visitScope(ctx.scope());
            context.exitScope();
        }

        ast.add(function);
        return null;
    }
}
