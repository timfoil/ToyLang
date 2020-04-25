package net.travitz.lang.toy.ast.control;

import net.travitz.lang.toy.ast.Block;
import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.ToyType;

import java.util.ArrayList;

public class IfBlock implements Statement {

    Statement condition;

    Block ifBlock;
    ArrayList<ElseIf> ifElseBlocks;
    Block elseBlock;

    private static class ElseIf {
        Statement condition;
        Block ifBlock;
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
