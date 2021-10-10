package com.company.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void givenAdd_whenGetCommand_returnsAddCommand() {
        assertEquals(
                new CommandAdd(1L, "a1", "Robert"),
                CommandFactory.getCommand("Add (1, \"a1\", \"Robert\")")
        );
    }

    @Test
    void givenPrintAll_whenGetCommand_returnsPrintAllCommand() {
        assertTrue(CommandFactory.getCommand("PrintAll") instanceof CommandPrintAll);
    }

    @Test
    void givenDeleteAll_whenGetCommand_returnsDeleteAllCommand() {
        assertTrue(CommandFactory.getCommand("DeleteAll") instanceof CommandDeleteAll);
    }

    @Test
    void givenNull_whenGetCommand_returnsNull() {
        assertNull(CommandFactory.getCommand(null));
    }

    @Test
    void givenInvalidCommand_whenGetCommand_returnsNull() {
        assertNull(CommandFactory.getCommand("blabla"));
    }

    @Test
    void givenInvalidAddParams_whenGetCommand_returnsNull() {
        assertNull(CommandFactory.getCommand("Add(\"ad\", 1)"));
    }
}