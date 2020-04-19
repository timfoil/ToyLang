package net.travitz.lang.toy.util;

import java.util.HashMap;
import java.util.Stack;

public class StackSymbolTable implements SymbolTable {

    /** Top level scope, scope level 0 */
    Scope fileScope;

    /** Stack containing all scopes in current context */
    Stack<Scope> scopeStack;

    /** The Current scope level we are in. Entering a nested scope increases the level, exiting a scope decreases it */
    int scopeLevel;

    /** Default constructor */
    public StackSymbolTable() {
        fileScope = new Scope();
        scopeStack = new Stack<>();
        scopeStack.push(fileScope);
        scopeLevel = 0;
    }

    /**
     * Scope implementation. Basically a wrapper around a HashMap to improve code readability. May change this from a
     * nested static class to its standalone file if I ever end up making more than one symbol-table class.
     */
    private static class Scope {
        HashMap<String, String> symbols = new HashMap<>();

        public void addToScope(String symbol, String type) {
            symbols.put(symbol, type);
        }

        public boolean symbolExistsInScope(String symbol) {
            return symbols.containsKey(symbol);
        }

        public String lookupSymbol(String symbol) {
            return symbols.get(symbol);
        }
    }

    @Override
    public void addToFileScope(String symbol, String type) {
        fileScope.addToScope(symbol, type);
    }

    @Override
    public void addToScope(String symbol, String type) {
            fileScope.addToScope(symbol, type);
    }

    @Override
    public void enterScope() {
        scopeLevel++;
        scopeStack.push(new Scope());
    }

    @Override
    public void exitScope() {
        if (scopeStack.empty()) {
            throw new RuntimeException("No scopes left to exit");
        }
        scopeLevel--;
        scopeStack.pop();
    }

    @Override
    public String lookupSymbol(String symbol) {
        return scopeStack.peek().lookupSymbol(symbol);
    }

    @Override
    public boolean symbolExistsInCurrentScope(String symbol) {
        return scopeStack.peek().symbolExistsInScope(symbol);
    }

    @Override
    public int scopeLevel() {
        return scopeLevel;
    }
}
