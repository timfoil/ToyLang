package net.travitz.lang.toy.util;

import java.util.ArrayList;

/**
 * Symbol table implementation that uses an ArrayList as the backing data-structure
 */
public class ALSymbolTable implements SymbolTable {

    /**
     * Top level scope, scope level 0
     */
    Scope fileScope;

    /**
     * ArrayList containing all scopes in current context
     */
    ArrayList<Scope> scopeList;

    /**
     * Default constructor
     */
    public ALSymbolTable() {
        fileScope = new Scope();
        scopeList = new ArrayList<>();
        scopeList.add(fileScope);
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
        scopeList.add(new Scope());
    }

    @Override
    public void exitScope() {
        // throw error if we try to exit file-level scope
        if (scopeList.size() <= 1) {
            throw new RuntimeException("No scopes left to exit");
        }

        scopeList.remove(scopeList.size() - 1);
    }

    @Override
    public String lookupSymbol(String symbol) {
        return scopeList.get(scopeList.size() - 1).lookupSymbol(symbol);
    }

    @Override
    public boolean symbolExistsInCurrentScope(String symbol) {
        return scopeList.get(scopeList.size() - 1).symbolExistsInScope(symbol);
    }

    @Override
    public int scopeLevel() {
        return scopeList.size() - 1;
    }
}
