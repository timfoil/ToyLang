package net.travitz.lang.toy.ast.control;

import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.ToyType;

import java.util.ArrayList;


public class FunctionDefStmt implements Statement {

    ArrayList<Statement> functionContents;
    ArrayList<ToyType> parameters;
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