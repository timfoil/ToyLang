package net.timfoil.lang.toy.ast.control;

import net.timfoil.lang.toy.ast.Statement;
import net.timfoil.lang.toy.ast.ToyType;

import java.util.ArrayList;

public class LoopStmt implements Statement {
    ArrayList<Statement> loopStmts;

    LoopType loopType;

    private enum LoopType { FOR, WHILE, LOOP }

    @Override
    public String getIR() {
        return null; //TODO
    }

    @Override
    public ToyType getExpressionReturnVal() {
        return null;
    }
}
