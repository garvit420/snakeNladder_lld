package com.snakeladder.strategy;

/**
 * Strategy interface for different dice rolling strategies
 */
public interface DiceStrategy {
    int roll();
    int getMaxValue();
}
