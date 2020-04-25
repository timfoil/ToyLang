package net.travitz.lang.toy.ast;

public interface Statement {
    String getIR();
    ToyType getExpressionReturnVal();
}
