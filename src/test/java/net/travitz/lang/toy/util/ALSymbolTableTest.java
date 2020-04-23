package net.travitz.lang.toy.util;

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
}
