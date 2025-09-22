package com.snakeladder.models;

import com.snakeladder.strategy.DiceStrategy;
import com.snakeladder.strategy.StandardDice;

/**
 * Dice class that uses Strategy pattern for different dice implementations
 */
public class Dice {
    private DiceStrategy strategy;
    
    public Dice() {
        this.strategy = new StandardDice(); // Default strategy
    }
    
    public Dice(DiceStrategy strategy) {
        this.strategy = strategy;
    }
    
    public int roll() {
        return strategy.roll();
    }
    
    public int getMaxValue() {
        return strategy.getMaxValue();
    }
    
    public void setStrategy(DiceStrategy strategy) {
        this.strategy = strategy;
    }
}
