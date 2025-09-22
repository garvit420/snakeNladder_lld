package com.snakeladder.strategy;

import java.util.Random;

/**
 * Standard 6-sided dice implementation
 */
public class StandardDice implements DiceStrategy {
    private final Random random;
    private final int maxValue;
    
    public StandardDice() {
        this.random = new Random();
        this.maxValue = 6;
    }
    
    @Override
    public int roll() {
        return random.nextInt(maxValue) + 1;
    }
    
    @Override
    public int getMaxValue() {
        return maxValue;
    }
}
