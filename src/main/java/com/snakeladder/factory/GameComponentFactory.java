package com.snakeladder.factory;

import com.snakeladder.models.Ladder;
import com.snakeladder.models.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Factory class for creating game components like Snakes and Ladders
 */
public class GameComponentFactory {
    private static final Random random = new Random();
    
    public static List<Snake> createDefaultSnakes() {
        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(99, 78));
        snakes.add(new Snake(95, 75));
        snakes.add(new Snake(92, 88));
        snakes.add(new Snake(89, 68));
        snakes.add(new Snake(74, 53));
        snakes.add(new Snake(64, 60));
        snakes.add(new Snake(62, 19));
        snakes.add(new Snake(46, 25));
        snakes.add(new Snake(37, 3));
        return snakes;
    }
    
    public static List<Ladder> createDefaultLadders() {
        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(1, 38));
        ladders.add(new Ladder(4, 14));
        ladders.add(new Ladder(9, 31));
        ladders.add(new Ladder(16, 6));
        ladders.add(new Ladder(21, 42));
        ladders.add(new Ladder(28, 84));
        ladders.add(new Ladder(36, 44));
        ladders.add(new Ladder(51, 67));
        ladders.add(new Ladder(71, 91));
        ladders.add(new Ladder(80, 100));
        return ladders;
    }
    
    public static List<Snake> createRandomSnakes(int count, int boardSize) {
        List<Snake> snakes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int head = random.nextInt(boardSize - 10) + 10; // Ensure head is not too low
            int tail = random.nextInt(head - 1) + 1; // Ensure tail is less than head
            snakes.add(new Snake(head, tail));
        }
        return snakes;
    }
    
    public static List<Ladder> createRandomLadders(int count, int boardSize) {
        List<Ladder> ladders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int bottom = random.nextInt(boardSize - 10) + 1; // Ensure bottom is not too high
            int top = random.nextInt(boardSize - bottom) + bottom + 1; // Ensure top is greater than bottom
            ladders.add(new Ladder(bottom, top));
        }
        return ladders;
    }
    
    public static Snake createSnake(int head, int tail) {
        return new Snake(head, tail);
    }
    
    public static Ladder createLadder(int bottom, int top) {
        return new Ladder(bottom, top);
    }
}
