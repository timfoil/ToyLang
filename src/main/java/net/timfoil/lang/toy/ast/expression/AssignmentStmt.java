package net.timfoil.lang.toy.ast.expression;


import net.timfoil.lang.toy.ast.Statement;
import net.timfoil.lang.toy.ast.ToyType;

public class AssignmentStmt implements Statement {
    Statement lhs;
    Statement rhs;
    boolean declaration; //true if we are assigning to an id with the LET keyword

    private enum AssignmentType {
        EQUALS, PLUS_EQUALS, MINUS_EQUALS, TIMES_EQUALS, DIV_EQUALS, MOD_EQUALS
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
