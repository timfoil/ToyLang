package net.timfoil.lang.toy.ast.expression;

import net.timfoil.lang.toy.ast.Statement;
import net.timfoil.lang.toy.ast.ToyType;

public class Combinator extends ExpressionStmt {
    ExpressionStmt rhs;
    ExpressionStmt lhs;
    String combination; //TODO make an enum?

    private enum CombinatorType {
        OR, AND, NOT_EQ, EQ, LT, LT_OR_EQ, GT, GT_OR_EQ, PLUS, TIMES, DIV, MOD
    }

    @Override
    public String getIR() {
        return null;
    }
}
