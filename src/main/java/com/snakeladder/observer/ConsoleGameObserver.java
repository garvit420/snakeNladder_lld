package com.snakeladder.observer;

import com.snakeladder.models.Player;

/**
 * Console implementation of GameObserver for CLI output
 */
public class ConsoleGameObserver implements GameObserver {
    
    @Override
    public void onPlayerMoved(Player player, int oldPosition, int newPosition, int diceRoll) {
        System.out.printf("%s rolled %d and moved from position %d to %d%n", 
                         player.getName(), diceRoll, oldPosition, newPosition);
    }
    
    @Override
    public void onSnakeEncountered(Player player, int snakeHead, int snakeTail) {
        System.out.printf("🐍 Oh no! %s encountered a snake at %d and slid down to %d%n", 
                         player.getName(), snakeHead, snakeTail);
    }
    
    @Override
    public void onLadderEncountered(Player player, int ladderBottom, int ladderTop) {
        System.out.printf("🪜 Great! %s found a ladder at %d and climbed up to %d%n", 
                         player.getName(), ladderBottom, ladderTop);
    }
    
    @Override
    public void onGameWon(Player winner) {
        System.out.printf("🎉 Congratulations! %s has won the game!%n", winner.getName());
    }
    
    @Override
    public void onGameStarted() {
        System.out.println("🎮 Game Started! Let's play Snake & Ladder!");
    }
    
    @Override
    public void onTurnChanged(Player currentPlayer) {
        System.out.printf("%n--- %s's turn ---", currentPlayer.getName());
    }
}
