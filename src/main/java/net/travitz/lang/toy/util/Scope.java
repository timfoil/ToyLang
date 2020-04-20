package net.travitz.lang.toy.util;

import java.util.HashMap;

/**
 * Scope implementation. Basically a wrapper around a HashMap to improve code readability for symbol tables
 */
public class Scope {
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