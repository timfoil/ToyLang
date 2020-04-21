package net.travitz.lang.toy.util;

import java.util.HashMap;
import java.util.Set;

/**
 * Scope implementation. Basically a wrapper around a HashMap to improve code readability for symbol tables
 */
public class Scope {
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

    public Set<String> getSymbolsMap() {
        return symbolTypeMap.keySet();
    }
}