package com.snakeladder.observer;

import com.snakeladder.models.Player;

/**
 * Observer interface for game state changes
 */
public interface GameObserver {
    void onPlayerMoved(Player player, int oldPosition, int newPosition, int diceRoll);
    void onSnakeEncountered(Player player, int snakeHead, int snakeTail);
    void onLadderEncountered(Player player, int ladderBottom, int ladderTop);
    void onGameWon(Player winner);
    void onGameStarted();
    void onTurnChanged(Player currentPlayer);
}
