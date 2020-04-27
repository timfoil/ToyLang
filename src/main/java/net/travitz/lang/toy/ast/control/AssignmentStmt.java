package net.travitz.lang.toy.ast.control;


import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.ToyType;

public class AssignmentStmt implements Statement {
    String assignable;

    @Override
    public String getIR() {
        return null;
    }

    @Override
    public ToyType getExpressionReturnVal() {
        return null;
    }

    private enum AssignmentType {
        EQUALS, PLUS_EQUALS, MINUS_EQUALS, TIMES_EQUALS, DIV_EQUALS, MOD_EQUALS
    }
}
