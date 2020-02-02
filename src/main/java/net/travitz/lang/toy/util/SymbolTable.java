package net.travitz.lang.toy.util;

/**
 * Skeleton for a SymbolTable to use while parsing
 */
public interface SymbolTable {

    /**
     * Add symbol to the top-level scope
     * @param symbol name of the symbol
     * @param type symbol type
     */
    boolean addToFileScope(String symbol, String type);


    /**
     * Add symbol to the current scope
     * @param symbol name of the symbol
     * @param type symbol type
     */
    boolean addToScope(String symbol, String type);


    /**
     * Enter deeper into a new scope
     */
    void enterScope();


    /**
     * Leave the current scope
     *
     * May throw a runtime error if this is called while already at the top-level scope
     */
    void exitScope();


    /**
     * Lookup the type of a symbol given its name
     *
     * @param symbol name of a symbol
     * @return the symbol type
     */
    String lookupSymbol(String symbol);


    /**
     * Return whether or not the symbol exists in the current scope
     *
     * @return <code>true</code> if the symbol exists in the current scope, <code>false</code> otherwise
     */
    boolean symbolExistsInCurrentScope();


    /**
     * Return the current context level, with 0 being the top-level scope
     *
     * @return current scope level
     */
    int scopeLevel();
}
