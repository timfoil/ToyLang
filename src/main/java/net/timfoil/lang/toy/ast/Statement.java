package net.timfoil.lang.toy.ast;

public interface Statement {
    String getIR();
    ToyType getExpressionReturnVal();
}
