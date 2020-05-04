package net.travitz.lang.toy.ast.expression;

import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.ToyType;

public abstract class ExpressionStmt implements Statement {

    ToyType returnType;

    @Override
    public ToyType getExpressionReturnVal() {
        return returnType;
    }
}
