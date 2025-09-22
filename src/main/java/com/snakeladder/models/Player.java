package com.snakeladder.models;

/**
 * Represents a player in the Snake & Ladder game
 */
public class Player {
    private String name;
    private int position;
    private int id;
    
    public Player(String name, int id) {
        this.name = name;
        this.id = id;
        this.position = 0; // Starting position
    }
    
    public String getName() {
        return name;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return String.format("Player{name='%s', position=%d}", name, position);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return id == player.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
