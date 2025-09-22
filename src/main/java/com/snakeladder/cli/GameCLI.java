package com.snakeladder.cli;

import com.snakeladder.engine.GameEngine;
import com.snakeladder.models.Player;
import com.snakeladder.observer.ConsoleGameObserver;

import java.util.Scanner;

/**
 * Command Line Interface for the Snake & Ladder game
 */
public class GameCLI {
    private GameEngine gameEngine;
    private Scanner scanner;
    
    public GameCLI() {
        this.gameEngine = new GameEngine();
        this.scanner = new Scanner(System.in);
        this.gameEngine.addObserver(new ConsoleGameObserver());
    }
    
    public void startGame() {
        displayWelcomeMessage();
        setupPlayers();
        displayBoard();
        gameEngine.startGame();
        playGame();
    }
    
    private void displayWelcomeMessage() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    🐍 SNAKE & LADDER 🪜                    ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Welcome to the classic Snake & Ladder game!            ║");
        System.out.println("║  Race to reach position 100 first to win!               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    private void setupPlayers() {
        System.out.print("Enter number of players (2-6): ");
        int numPlayers = getValidPlayerCount();
        
        for (int i = 0; i < numPlayers; i++) {
            System.out.printf("Enter name for Player %d: ", i + 1);
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                name = "Player " + (i + 1);
            }
            gameEngine.addPlayer(new Player(name, i + 1));
        }
        
        System.out.println("\nPlayers added successfully!");
        for (Player player : gameEngine.getPlayers()) {
            System.out.println("- " + player.getName());
        }
        System.out.println();
    }
    
    private int getValidPlayerCount() {
        while (true) {
            try {
                int count = Integer.parseInt(scanner.nextLine().trim());
                if (count >= 2 && count <= 6) {
                    return count;
                } else {
                    System.out.print("Please enter a number between 2 and 6: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    private void displayBoard() {
        System.out.println("🎯 Game Board Information:");
        System.out.println("Board Size: 100 positions (10x10)");
        System.out.println("🐍 Snakes will take you down!");
        System.out.println("🪜 Ladders will help you climb up!");
        System.out.println("🏁 Reach position 100 to win!");
        System.out.println();
        
        displayBoardLayout();
    }
    
    private void displayBoardLayout() {
        System.out.println("📋 Board Layout:");
        System.out.println("┌" + "─".repeat(39) + "┐");
        
        for (int row = 9; row >= 0; row--) {
            System.out.print("│");
            for (int col = 0; col < 10; col++) {
                int position;
                if (row % 2 == 1) {
                    position = row * 10 + col + 1;
                } else {
                    position = row * 10 + (9 - col) + 1;
                }
                
                String symbol = getPositionSymbol(position);
                System.out.printf("%3s ", symbol);
            }
            System.out.println("│");
        }
        
        System.out.println("└" + "─".repeat(39) + "┘");
        System.out.println();
    }
    
    private String getPositionSymbol(int position) {
        if (gameEngine.getBoard().hasSnake(position)) {
            return "🐍";
        } else if (gameEngine.getBoard().hasLadder(position)) {
            return "🪜";
        } else if (position == 100) {
            return "🏁";
        } else {
            return String.valueOf(position);
        }
    }
    
    private void playGame() {
        while (!gameEngine.isGameWon()) {
            displayCurrentPositions();
            System.out.print("\nPress Enter to roll the dice...");
            scanner.nextLine();
            
            boolean continueGame = gameEngine.playTurn();
            
            if (!continueGame) {
                break;
            }
            
            // Add a small delay for better user experience
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        displayGameEnd();
    }
    
    private void displayCurrentPositions() {
        System.out.println("\n📊 Current Positions:");
        for (Player player : gameEngine.getPlayers()) {
            System.out.printf("  %s: Position %d%n", player.getName(), player.getPosition());
        }
    }
    
    private void displayGameEnd() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                GAME OVER");
        System.out.println("=".repeat(50));
        
        displayFinalPositions();
        
        System.out.print("\nWould you like to play again? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("y") || response.equals("yes")) {
            resetGame();
            startGame();
        } else {
            System.out.println("\nThank you for playing Snake & Ladder! 🎮");
        }
    }
    
    private void displayFinalPositions() {
        System.out.println("\n📊 Final Positions:");
        gameEngine.getPlayers().stream()
            .sorted((p1, p2) -> Integer.compare(p2.getPosition(), p1.getPosition()))
            .forEach(player -> {
                String status = player.getPosition() == 100 ? " 🏆 WINNER!" : "";
                System.out.printf("  %s: Position %d%s%n", 
                    player.getName(), player.getPosition(), status);
            });
    }
    
    private void resetGame() {
        this.gameEngine = new GameEngine();
        this.gameEngine.addObserver(new ConsoleGameObserver());
    }
    
    public void close() {
        scanner.close();
    }
}
