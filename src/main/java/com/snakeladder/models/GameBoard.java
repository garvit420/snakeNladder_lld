package com.snakeladder.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton GameBoard class representing the Snake & Ladder board
 */
public class GameBoard {
    private static GameBoard instance;
    private final int boardSize;
    private final Map<Integer, Snake> snakes;
    private final Map<Integer, Ladder> ladders;
    
    private GameBoard(int boardSize) {
        this.boardSize = boardSize;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        initializeBoard();
    }
    
    public static synchronized GameBoard getInstance() {
        if (instance == null) {
            instance = new GameBoard(100); // Default 10x10 board
        }
        return instance;
    }
    
    public static synchronized GameBoard getInstance(int boardSize) {
        if (instance == null) {
            instance = new GameBoard(boardSize);
        }
        return instance;
    }
    
    private void initializeBoard() {
        // Add default snakes
        addSnake(new Snake(99, 78));
        addSnake(new Snake(95, 75));
        addSnake(new Snake(92, 88));
        addSnake(new Snake(89, 68));
        addSnake(new Snake(74, 53));
        addSnake(new Snake(64, 60));
        addSnake(new Snake(62, 19));
        addSnake(new Snake(46, 25));
        addSnake(new Snake(37, 3));
        
        // Add default ladders
        addLadder(new Ladder(1, 38));
        addLadder(new Ladder(4, 14));
        addLadder(new Ladder(9, 31));
        addLadder(new Ladder(21, 42));
        addLadder(new Ladder(28, 84));
        addLadder(new Ladder(36, 44));
        addLadder(new Ladder(51, 67));
        addLadder(new Ladder(71, 91));
        addLadder(new Ladder(80, 99));
    }
    
    public void addSnake(Snake snake) {
        snakes.put(snake.getHead(), snake);
    }
    
    public void addLadder(Ladder ladder) {
        ladders.put(ladder.getBottom(), ladder);
    }
    
    public Snake getSnake(int position) {
        return snakes.get(position);
    }
    
    public Ladder getLadder(int position) {
        return ladders.get(position);
    }
    
    public boolean hasSnake(int position) {
        return snakes.containsKey(position);
    }
    
    public boolean hasLadder(int position) {
        return ladders.containsKey(position);
    }
    
    public int getBoardSize() {
        return boardSize;
    }
    
    public boolean isValidPosition(int position) {
        return position >= 0 && position <= boardSize;
    }
    
    public boolean isWinningPosition(int position) {
        return position == boardSize;
    }
    
    public Map<Integer, Snake> getSnakes() {
        return new HashMap<>(snakes);
    }
    
    public Map<Integer, Ladder> getLadders() {
        return new HashMap<>(ladders);
    }
    
    // Reset the singleton instance (useful for testing)
    public static synchronized void resetInstance() {
        instance = null;
    }
}
