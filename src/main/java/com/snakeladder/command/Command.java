package com.snakeladder.command;

/**
 * Command interface for implementing Command pattern
 */
public interface Command {
    void execute();
    void undo();
}
