package com.snakeladder.engine;

import com.snakeladder.command.Command;
import com.snakeladder.command.MovePlayerCommand;
import com.snakeladder.models.*;
import com.snakeladder.observer.GameObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Main game engine that manages the game flow
 */
public class GameEngine {
    private List<Player> players;
    private GameBoard board;
    private Dice dice;
    private int currentPlayerIndex;
    private boolean gameWon;
    private List<GameObserver> observers;
    private Stack<Command> commandHistory;
    
    public GameEngine() {
        this.players = new ArrayList<>();
        this.board = GameBoard.getInstance();
        this.dice = new Dice();
        this.currentPlayerIndex = 0;
        this.gameWon = false;
        this.observers = new ArrayList<>();
        this.commandHistory = new Stack<>();
    }
    
    public void addPlayer(Player player) {
        if (gameWon) {
            throw new IllegalStateException("Cannot add players after game has ended");
        }
        players.add(player);
    }
    
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    
    public void startGame() {
        if (players.size() < 2) {
            throw new IllegalStateException("At least 2 players required to start the game");
        }
        notifyGameStarted();
    }
    
    public boolean playTurn() {
        if (gameWon) {
            return false;
        }
        
        Player currentPlayer = getCurrentPlayer();
        notifyTurnChanged(currentPlayer);
        
        int diceRoll = dice.roll();
        int oldPosition = currentPlayer.getPosition();
        
        // Create and execute move command
        MovePlayerCommand moveCommand = new MovePlayerCommand(currentPlayer, diceRoll);
        executeCommand(moveCommand);
        
        int newPosition = currentPlayer.getPosition();
        
        // Check if player goes beyond board
        if (newPosition > board.getBoardSize()) {
            // Bounce back or stay at current position based on rules
            currentPlayer.setPosition(oldPosition);
            newPosition = oldPosition;
        }
        
        notifyPlayerMoved(currentPlayer, oldPosition, newPosition, diceRoll);
        
        // Check for snakes and ladders
        newPosition = handleSnakesAndLadders(currentPlayer, newPosition);
        
        // Check for win condition
        if (board.isWinningPosition(newPosition)) {
            gameWon = true;
            notifyGameWon(currentPlayer);
            return false;
        }
        
        // Move to next player
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return true;
    }
    
    private int handleSnakesAndLadders(Player player, int position) {
        // Check for snake
        if (board.hasSnake(position)) {
            Snake snake = board.getSnake(position);
            player.setPosition(snake.getTail());
            notifySnakeEncountered(player, snake.getHead(), snake.getTail());
            return snake.getTail();
        }
        
        // Check for ladder
        if (board.hasLadder(position)) {
            Ladder ladder = board.getLadder(position);
            player.setPosition(ladder.getTop());
            notifyLadderEncountered(player, ladder.getBottom(), ladder.getTop());
            return ladder.getTop();
        }
        
        return position;
    }
    
    private void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }
    
    public void undoLastMove() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        }
    }
    
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
    
    public boolean isGameWon() {
        return gameWon;
    }
    
    public GameBoard getBoard() {
        return board;
    }
    
    // Observer notification methods
    private void notifyPlayerMoved(Player player, int oldPosition, int newPosition, int diceRoll) {
        for (GameObserver observer : observers) {
            observer.onPlayerMoved(player, oldPosition, newPosition, diceRoll);
        }
    }
    
    private void notifySnakeEncountered(Player player, int snakeHead, int snakeTail) {
        for (GameObserver observer : observers) {
            observer.onSnakeEncountered(player, snakeHead, snakeTail);
        }
    }
    
    private void notifyLadderEncountered(Player player, int ladderBottom, int ladderTop) {
        for (GameObserver observer : observers) {
            observer.onLadderEncountered(player, ladderBottom, ladderTop);
        }
    }
    
    private void notifyGameWon(Player winner) {
        for (GameObserver observer : observers) {
            observer.onGameWon(winner);
        }
    }
    
    private void notifyGameStarted() {
        for (GameObserver observer : observers) {
            observer.onGameStarted();
        }
    }
    
    private void notifyTurnChanged(Player currentPlayer) {
        for (GameObserver observer : observers) {
            observer.onTurnChanged(currentPlayer);
        }
    }
}
