#!/bin/bash

echo "Compiling Snake & Ladder Game..."
javac -d out src/main/java/com/snakeladder/*.java src/main/java/com/snakeladder/*/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running Snake & Ladder Game..."
    echo
    java -cp out com.snakeladder.SnakeLadderGame
else
    echo "Compilation failed! Please check for errors."
    exit 1
fi
