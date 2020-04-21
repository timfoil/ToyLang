package net.travitz.lang.toy.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Symbol table implementation that uses an ArrayList as the backing data-structure
 */
public class ALSymbolTable implements SymbolTable {
    //TODO this the best way to do this? Do I care enough to rewrite this again?
    /** Top level scope, scope level 0 */
    Scope fileScope;

    /** ArrayList containing all scopes in current context */
    ArrayList<Scope> scopeList;

    /** Map indicating at what level a symbol was initially defined at */
    HashMap<String, Integer> levelMap;

    /** Default constructor */
    public ALSymbolTable() {
        fileScope = new Scope();
        scopeList = new ArrayList<>();
        levelMap = new HashMap<>();
        scopeList.add(fileScope);
    }

    private void addToScopeMap(String symbol) {
        if(!levelMap.containsKey(symbol)) {
            levelMap.put(symbol, scopeLevel());
        }
    }

    private void removeFromScopeMap(Scope remove) {
        for (String symbol : remove.getSymbolsMap()) {
            if (levelMap.get(symbol) == scopeLevel()) {
                levelMap.remove(symbol);
            } else if (levelMap.get(symbol) > scopeLevel()) {
                throw new IllegalStateException("Cannot have a symbol defined in a scope that doesn't exist");
            }
        }
    }

    @Override
    public void addToFileScope(String symbol, String type) {
        fileScope.addToScope(symbol, type);
        addToScopeMap(symbol);
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

        removeFromScopeMap(scopeList.get(scopeList.size() - 1));
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
    public boolean symbolExists(String symbol) {
        return levelMap.containsKey(symbol);
    }

    @Override
    public int scopeLevel() {
        return scopeList.size() - 1;
    }
}
