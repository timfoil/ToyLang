package net.timfoil.lang.toy.ast.expression;

import net.timfoil.lang.toy.ast.Statement;
import net.timfoil.lang.toy.ast.ToyType;

public class ReturnStmt implements Statement {

    ExpressionStmt val;

    @Override
    public String getIR() {
        return null;
    }

    @Override
    public ToyType getExpressionReturnVal() {
        return (val == null) ? null : val.getExpressionReturnVal();
    }
}
