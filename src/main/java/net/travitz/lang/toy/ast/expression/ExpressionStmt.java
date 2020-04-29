package net.travitz.lang.toy.ast.expression;

import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.ToyType;

public class ExpressionStmt implements Statement {

    ToyType returnType;

    @Override
    public String getIR() {
        return null;
    }

    @Override
    public ToyType getExpressionReturnVal() {
        return null;
    }
}
