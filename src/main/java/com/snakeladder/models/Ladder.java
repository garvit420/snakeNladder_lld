package com.snakeladder.models;

/**
 * Represents a Ladder on the game board
 */
public class Ladder {
    private int bottom;
    private int top;
    
    public Ladder(int bottom, int top) {
        if (bottom >= top) {
            throw new IllegalArgumentException("Ladder bottom must be less than top");
        }
        this.bottom = bottom;
        this.top = top;
    }
    
    public int getBottom() {
        return bottom;
    }
    
    public int getTop() {
        return top;
    }
    
    @Override
    public String toString() {
        return String.format("Ladder{bottom=%d, top=%d}", bottom, top);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ladder ladder = (Ladder) obj;
        return bottom == ladder.bottom && top == ladder.top;
    }
    
    @Override
    public int hashCode() {
        return bottom * 31 + top;
    }
}
