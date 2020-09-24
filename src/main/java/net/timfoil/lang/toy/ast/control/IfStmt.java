package net.timfoil.lang.toy.ast.control;

import net.timfoil.lang.toy.ast.Statement;
import net.timfoil.lang.toy.ast.ToyType;

import java.util.ArrayList;

public class IfStmt implements Statement {

    Statement condition;

    ArrayList<Statement> ifBlock;
    ArrayList<ElseIf> ifElseBlocks;
    ArrayList<Statement> elseBlock;

    private static class ElseIf {
        Statement condition;
        ArrayList<Statement> ifBlock;
    }

    @Override
    public String getIR() {
        return null; //TODO
    }

    @Override
    public ToyType getExpressionReturnVal() {
        return null;
    }


}
