package net.travitz.lang.toy.ast.control;

import net.travitz.lang.toy.ast.Statement;
import net.travitz.lang.toy.ast.ToyType;

import java.util.ArrayList;


public class FunctionDefStmt implements Statement {

    ArrayList<Statement> functionContents = new ArrayList<>();
    ArrayList<ToyType> parameters = new ArrayList<>();
    ToyType returnType;

    @Override
    public String getIR() {
        return null; //TODO
    }

    @Override
    public ToyType getExpressionReturnVal() {
        return null;
    }

    public ToyType getReturnType() {
        return returnType;
    }

    public int functionContentsSize() {
        return functionContents.size();
    }

    public boolean functionContentsIsEmpty() {
        return functionContents.isEmpty();
    }

    public Statement functionContentsGet(int index) {
        return functionContents.get(index);
    }

    public Statement functionContentsSet(int index, Statement element) {
        return functionContents.set(index, element);
    }

    public boolean functionContentsAdd(Statement statement) {
        return functionContents.add(statement);
    }

    public void functionContentsAdd(int index, Statement element) {
        functionContents.add(index, element);
    }

    public void setReturnType(ToyType returnType) {
        this.returnType = returnType;
    }

    public int parametersSize() {
        return parameters.size();
    }

    public boolean parametersIsEmpty() {
        return parameters.isEmpty();
    }

    public ToyType parametersGet(int index) {
        return parameters.get(index);
    }

    public ToyType parametersSet(int index, ToyType element) {
        return parameters.set(index, element);
    }

    public boolean parametersAdd(ToyType toyType) {
        return parameters.add(toyType);
    }

    public void parametersAdd(int index, ToyType element) {
        parameters.add(index, element);
    }
}
