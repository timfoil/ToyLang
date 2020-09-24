package net.timfoil.lang.toy.ast.expression;

import net.timfoil.lang.toy.ast.Statement;
import net.timfoil.lang.toy.ast.ToyType;

public abstract class ExpressionStmt implements Statement {

    ToyType returnType;

    @Override
    public ToyType getExpressionReturnVal() {
        return returnType;
    }
}
