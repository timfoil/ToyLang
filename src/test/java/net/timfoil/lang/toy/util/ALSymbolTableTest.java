package net.timfoil.lang.toy.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ALSymbolTableTest {
    @Test
    public void testGetCurrentScope() {
        ALSymbolTable cut = new ALSymbolTable();
        assertEquals(0, cut.getScopeLevel());
        cut.enterScope();
        assertEquals(1, cut.getScopeLevel());
        cut.enterScope();
        assertEquals(2, cut.getScopeLevel());
        cut.exitScope();
        assertEquals(1, cut.getScopeLevel());
        cut.enterScope();
        assertEquals(2, cut.getScopeLevel());
        cut.exitScope();
        cut.exitScope();
        assertEquals(0, cut.getScopeLevel());
    }

    @Test(expected = IllegalStateException.class)
    public void testExitInitialScope() {
        ALSymbolTable cut = new ALSymbolTable();
        cut.exitScope();
    }

    @Test
    public void addToScopeIT() {
        ALSymbolTable cut = new ALSymbolTable();

        String type0 = "0";
        String symbol = "b";
        String symbolOther = "other";

        cut.addToScope(symbol, type0);
        cut.addToScope(symbolOther, type0);

        assertEquals(cut.lookupSymbol(symbol), type0);
        assertEquals(cut.lookupSymbol(symbolOther), type0);

        String type0b = "0b";
        cut.addToScope(symbol, type0b);

        assertEquals(cut.lookupSymbol(symbol), type0b);

        cut.enterScope(); // Enter tier 1

        assertFalse(cut.symbolExistsInCurrentScope(symbol));
        assertFalse(cut.symbolExistsInCurrentScope(symbolOther));

        String type1 = "1";
        cut.addToScope(symbol, type1);
        assertEquals(cut.lookupSymbol(symbol), type1);

        assertTrue(cut.symbolExistsInCurrentScope(symbol));
        assertFalse(cut.symbolExistsInCurrentScope(symbolOther));

        String type1b = "1b";
        cut.addToScope(symbol, type1b);
        assertEquals(cut.lookupSymbol(symbol), type1b);

        cut.enterScope(); // Enter tier 2

        assertFalse(cut.symbolExistsInCurrentScope(symbol));
        assertFalse(cut.symbolExistsInCurrentScope(symbolOther));

        String type2 = "2";
        cut.addToScope(symbol, type2);
        assertEquals(cut.lookupSymbol(symbol), type2);

        assertTrue(cut.symbolExistsInCurrentScope(symbol));
        assertFalse(cut.symbolExistsInCurrentScope(symbolOther));

        String type2b = "2b";
        cut.addToScope(symbol, type2b);
        assertEquals(cut.lookupSymbol(symbol), type2b);

        cut.exitScope();
        assertEquals(cut.lookupSymbol(symbol), type1b);
        cut.exitScope();
        assertEquals(cut.lookupSymbol(symbol), type0b);
    }
}
