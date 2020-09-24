package net.timfoil.lang.toy.ast;

public class ToyType {
    boolean mutable;
    boolean nullable;
    //If we are an array we need these filled
    boolean isArray;
    ToyType arrayType;

    //If we are a function we need these filled


    /**
     * Return a type from a given string
     * @param typeString
     * @return
     */
    public static ToyType stringToType(String typeString) {
        return null;
    }

}
