package net.travitz.lang.toy.ast.expression;

import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.ToyType;

public class Combinator implements Statement {
    Statement rhs;
    Statement lhs;
    String combination; //TODO make an enum?

    private enum CombinatorType {
        OR, AND, NOT_EQ, EQ, LT, LT_OR_EQ, GT, GT_OR_EQ, PLUS, TIMES, DIV, MOD
    }

    @Override
    public String getIR() {
        return null;
    }

    @Override
    public ToyType getExpressionReturnVal() {
        return null;
    }
}
