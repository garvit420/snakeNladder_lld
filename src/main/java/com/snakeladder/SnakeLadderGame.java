package com.snakeladder;

import com.snakeladder.cli.GameCLI;

/**
 * Main application class for Snake & Ladder game
 */
public class SnakeLadderGame {
    public static void main(String[] args) {
        GameCLI gameCLI = new GameCLI();
        
        // Add shutdown hook for cleanup
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down Snake & Ladder game...");
            gameCLI.close();
        }));
        
        try {
            gameCLI.startGame();
        } catch (Exception e) {
            System.err.println("An error occurred while running the game: " + e.getMessage());
            e.printStackTrace();
        } finally {
            gameCLI.close();
        }
    }
}
