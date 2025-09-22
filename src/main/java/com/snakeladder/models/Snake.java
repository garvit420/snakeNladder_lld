package com.snakeladder.models;

/**
 * Represents a Snake on the game board
 */
public class Snake {
    private int head;
    private int tail;
    
    public Snake(int head, int tail) {
        if (head <= tail) {
            throw new IllegalArgumentException("Snake head must be greater than tail");
        }
        this.head = head;
        this.tail = tail;
    }
    
    public int getHead() {
        return head;
    }
    
    public int getTail() {
        return tail;
    }
    
    @Override
    public String toString() {
        return String.format("Snake{head=%d, tail=%d}", head, tail);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Snake snake = (Snake) obj;
        return head == snake.head && tail == snake.tail;
    }
    
    @Override
    public int hashCode() {
        return head * 31 + tail;
    }
}
