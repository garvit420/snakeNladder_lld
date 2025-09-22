package com.snakeladder.command;

import com.snakeladder.models.Player;

/**
 * Command implementation for moving a player
 */
public class MovePlayerCommand implements Command {
    private Player player;
    private int steps;
    private int previousPosition;
    
    public MovePlayerCommand(Player player, int steps) {
        this.player = player;
        this.steps = steps;
        this.previousPosition = player.getPosition();
    }
    
    @Override
    public void execute() {
        int newPosition = player.getPosition() + steps;
        player.setPosition(newPosition);
    }
    
    @Override
    public void undo() {
        player.setPosition(previousPosition);
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public int getSteps() {
        return steps;
    }
    
    public int getPreviousPosition() {
        return previousPosition;
    }
}
