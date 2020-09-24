package net.timfoil.lang.toy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

/**
 * Symbol table implementation that uses an ArrayList as the backing data-structure the top-level scope (fileScope) is
 * at a scope level of 0. Every enclosed scope increases the level by 1
 */
public class ALSymbolTable implements SymbolTable {

    /** ArrayList containing all scopes in current context */
    ArrayList<Scope> scopeList;

    /** Map that keeps track of what the current scope's symbol type definition should be*/
    HashMap<String, Stack<String>> levelMap;

    /**
     * Scope implementation. Basically a wrapper around a HashMap to improve code readability for symbol tables
     */
    public static class Scope {
        HashMap<String, String> symbolTypeMap = new HashMap<>();

        public void addToScope(String symbol, String type) {
            symbolTypeMap.put(symbol, type);
        }

        public boolean symbolExistsInScope(String symbol) {
            return symbolTypeMap.containsKey(symbol);
        }

        public String lookupSymbol(String symbol) {
            return symbolTypeMap.get(symbol);
        }

        public Set<String> getSymbolsSet() {
            return symbolTypeMap.keySet();
        }
    }

    /** Default constructor */
    public ALSymbolTable() {
        Scope fileScope = new Scope();
        scopeList = new ArrayList<>();
        levelMap = new HashMap<>();
        scopeList.add(fileScope);
    }

    private Scope getCurrentScope() {
        return scopeList.get(scopeList.size() - 1);
    }

    @Override
    public void addToScope(String symbol, String type) {
        Scope curScope = getCurrentScope();
        if (curScope.symbolExistsInScope(symbol)) {
            removeSymbolFromMap(symbol); //we want to replace, remove current symbol
        }
        curScope.addToScope(symbol, type); // add as new symbol
        addSymbolToMap(symbol, type);
    }

    private void addSymbolToMap(String symbol, String type) {
        if (levelMap.containsKey(symbol)) {
            levelMap.get(symbol).push(type);
        } else {
            Stack<String> typeVal = new Stack<>();
            typeVal.push(type);
            levelMap.put(symbol, typeVal);
        }
    }

    private void removeSymbolFromMap(String symbol) {
        levelMap.get(symbol).pop();
    }

    @Override
    public void enterScope() {
        scopeList.add(new Scope());
    }

    @Override
    public void exitScope() {
        // throw error if we try to exit file-level scope
        if (scopeList.size() <= 1) {
            throw new IllegalStateException("No scopes left to exit");
        }

        // scope exit logic
        Scope toExit = scopeList.remove(scopeList.size() - 1);

        for(String symbol : toExit.getSymbolsSet()) {
            removeSymbolFromMap(symbol);
        }
    }

    @Override
    public String lookupSymbol(String symbol) {
        if (symbolExists(symbol)) {
            return levelMap.get(symbol).peek();
        } else {
            throw new IllegalStateException("Given symbol does not exist in this symbol-table");
        }
    }

    @Override
    public boolean symbolExistsInCurrentScope(String symbol) {
        return getCurrentScope().symbolExistsInScope(symbol);
    }

    public String lookupSymbolInCurrentScope(String symbol) {
        if (symbolExistsInCurrentScope(symbol)) {
            return getCurrentScope().lookupSymbol(symbol);
        } else {
            throw new IllegalStateException("Given symbol does not exist in current scope");
        }
    }

    public String lookupSymbolInScopeAtLevel(String symbol, int level) {
        if (level >= 0 && level <= getScopeLevel()) {
            if (symbolExistsInScopeAtLevel(symbol, level)) {
                return scopeList.get(level).lookupSymbol(symbol);
            } else {
                throw new IllegalStateException("Given symbol does not exist at given scope level");
            }
        } else {
            throw new IndexOutOfBoundsException("Level must be a non-negative number less than or equal the current " +
                    "scope level");
        }
    }

    public boolean symbolExistsInScopeAtLevel(String symbol, int level) {
        if (level >= 0 && level <= getScopeLevel()) {
            return scopeList.get(level).symbolExistsInScope(symbol);
        } else {
            throw new IndexOutOfBoundsException("Level must be a non-negative number less than or equal the current " +
                    "scope level");
        }
    }

    @Override
    public boolean symbolExists(String symbol) {
        return levelMap.containsKey(symbol);
    }

    @Override
    public int getScopeLevel() {
        return scopeList.size() - 1;
    }
}
