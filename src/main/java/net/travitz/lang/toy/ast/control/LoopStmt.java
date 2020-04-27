package net.travitz.lang.toy.ast.control;

import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.ToyType;

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
