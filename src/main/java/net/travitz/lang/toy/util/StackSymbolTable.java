package net.travitz.lang.toy.util;

public class StackSymbolTable implements SymbolTable {
    @Override
    public boolean addToFileScope(String symbol, String type) {
        return false;
    }

    @Override
    public boolean addToScope(String symbol, String type) {
        return false;
    }

    @Override
    public void enterScope() {

    }

    @Override
    public void exitScope() {

    }

    @Override
    public String lookupSymbol(String symbol) {
        return null;
    }

    @Override
    public boolean symbolExistsInCurrentScope() {
        return false;
    }

    @Override
    public int scopeLevel() {
        return 0;
    }
}
