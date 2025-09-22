# 🐍 Snake & Ladder Game 🪜

A comprehensive implementation of the classic Snake & Ladder board game in Java, featuring modern software design patterns, clean architecture, and a command-line interface.

## 🎯 Features

- **Complete Snake & Ladder gameplay** with traditional rules
- **Command Line Interface (CLI)** for interactive gameplay
- **Multiple players support** (2-6 players)
- **Modern design patterns** implementation
- **Extensible architecture** for easy modifications
- **Visual board representation** in CLI
- **Game state management** with undo functionality
- **Observer pattern** for real-time game notifications

## 🏗️ Architecture & Design Patterns

This implementation showcases several famous design patterns:

### 1. **Singleton Pattern**
- **Class**: `GameBoard`
- **Purpose**: Ensures only one game board instance exists throughout the game
- **Benefits**: Centralized board state management, memory efficiency

### 2. **Strategy Pattern**
- **Classes**: `DiceStrategy`, `StandardDice`
- **Purpose**: Allows different dice rolling strategies
- **Benefits**: Easy to add custom dice types (weighted dice, multiple dice, etc.)

### 3. **Observer Pattern**
- **Classes**: `GameObserver`, `ConsoleGameObserver`
- **Purpose**: Notifies interested parties about game events
- **Benefits**: Loose coupling, easy to add new UI components or logging

### 4. **Command Pattern**
- **Classes**: `Command`, `MovePlayerCommand`
- **Purpose**: Encapsulates player moves as objects
- **Benefits**: Enables undo functionality, move history tracking

### 5. **Factory Pattern**
- **Class**: `GameComponentFactory`
- **Purpose**: Creates game components (Snakes, Ladders)
- **Benefits**: Centralized object creation, easy configuration management

## 📁 Project Structure

```
src/main/java/com/snakeladder/
├── models/                     # Core game entities
│   ├── Player.java            # Player representation
│   ├── GameBoard.java         # Singleton game board
│   ├── Snake.java             # Snake entity
│   ├── Ladder.java            # Ladder entity
│   └── Dice.java              # Dice with strategy pattern
├── strategy/                   # Strategy pattern implementation
│   ├── DiceStrategy.java      # Strategy interface
│   └── StandardDice.java      # Standard dice implementation
├── observer/                   # Observer pattern implementation
│   ├── GameObserver.java      # Observer interface
│   └── ConsoleGameObserver.java # CLI observer implementation
├── command/                    # Command pattern implementation
│   ├── Command.java           # Command interface
│   └── MovePlayerCommand.java # Move command implementation
├── factory/                    # Factory pattern implementation
│   └── GameComponentFactory.java # Game component factory
├── engine/                     # Game logic
│   └── GameEngine.java        # Main game engine
├── cli/                        # Command line interface
│   └── GameCLI.java           # CLI implementation
└── SnakeLadderGame.java       # Main application class
```

## 🎮 How to Play

### Prerequisites
- Java 8 or higher
- Any terminal/command prompt

### Running the Game

1. **Compile the Java files:**
   ```bash
   javac -d out src/main/java/com/snakeladder/**/*.java
   ```

2. **Run the game:**
   ```bash
   java -cp out com.snakeladder.SnakeLadderGame
   ```

### Game Rules

1. **Objective**: Be the first player to reach position 100
2. **Setup**: 2-6 players start at position 0
3. **Gameplay**:
   - Players take turns rolling a dice (1-6)
   - Move forward by the number shown on dice
   - **Ladders**: Climb up to a higher position
   - **Snakes**: Slide down to a lower position
   - **Winning**: First player to reach exactly position 100 wins
   - **Bounce Rule**: If dice roll takes you beyond 100, you stay at current position

### Game Controls

- **Enter**: Roll dice during your turn
- **Follow prompts**: Enter player names and number of players
- **Y/N**: Choose to play again after game ends

## 🎨 Game Board Layout

The game features a traditional 10x10 board (positions 1-100) with:

- **🐍 Snakes**: 
  - 99→78, 95→75, 92→88, 89→68, 74→53
  - 64→60, 62→19, 46→25, 37→3

- **🪜 Ladders**: 
  - 1→38, 4→14, 9→31, 21→42, 28→84
  - 36→44, 51→67, 71→91, 80→99

- **🏁 Goal**: Position 100

## 🔧 Design Principles Applied

### SOLID Principles

1. **Single Responsibility Principle (SRP)**
   - Each class has a single, well-defined responsibility
   - `Player` manages player state, `GameBoard` manages board state, etc.

2. **Open-Closed Principle (OCP)**
   - Classes are open for extension but closed for modification
   - New dice strategies can be added without modifying existing code

3. **Liskov Substitution Principle (LSP)**
   - Derived classes can replace base classes without breaking functionality
   - Any `DiceStrategy` implementation can replace `StandardDice`

4. **Interface Segregation Principle (ISP)**
   - Interfaces are small and focused on specific functionality
   - `GameObserver` only contains game-related notification methods

5. **Dependency Inversion Principle (DIP)**
   - High-level modules don't depend on low-level modules
   - `GameEngine` depends on `GameObserver` abstraction, not concrete implementations

### Additional Design Principles

- **Encapsulation**: Private fields with controlled access via public methods
- **Composition over Inheritance**: Using composition for dice strategies
- **Immutable Objects**: Snake and Ladder positions are immutable after creation
- **Fail-Fast**: Early validation of input parameters

## 🚀 Extension Points

The architecture supports easy extensions:

### Adding New Dice Types
```java
public class WeightedDice implements DiceStrategy {
    // Custom implementation for weighted dice
}
```

### Adding New Observers
```java
public class GraphicalGameObserver implements GameObserver {
    // GUI-based observer implementation
}
```

### Adding New Game Rules
```java
public class CustomGameEngine extends GameEngine {
    // Override methods to implement custom rules
}
```

## 🧪 Key Classes Explained

### GameEngine
The heart of the application that:
- Manages game flow and turn-based gameplay
- Handles player movements and dice rolls
- Processes snake and ladder encounters
- Manages win conditions
- Maintains command history for undo functionality

### GameBoard (Singleton)
- Maintains the single instance of the game board
- Stores snake and ladder positions
- Validates player positions
- Provides board configuration

### Player
- Represents individual players with name and position
- Immutable ID for player identification
- Position tracking throughout the game

### Command Pattern Implementation
- `MovePlayerCommand`: Encapsulates player movement
- Supports undo functionality
- Command history tracking

## 🎯 Benefits of This Implementation

1. **Maintainability**: Clean separation of concerns
2. **Extensibility**: Easy to add new features
3. **Testability**: Modular design supports unit testing
4. **Reusability**: Components can be reused in different contexts
5. **Scalability**: Observer pattern supports multiple UI types
6. **Performance**: Singleton pattern ensures efficient memory usage

## 🔍 UML Class Diagram

```mermaid
classDiagram
    class Player {
        -String name
        -int position
        -int id
        +Player(String name, int id)
        +getName() String
        +getPosition() int
        +setPosition(int position)
        +getId() int
    }

    class GameBoard {
        -static GameBoard instance
        -int boardSize
        -Map<Integer, Snake> snakes
        -Map<Integer, Ladder> ladders
        -GameBoard(int boardSize)
        +getInstance() GameBoard
        +addSnake(Snake snake)
        +addLadder(Ladder ladder)
        +getSnake(int position) Snake
        +getLadder(int position) Ladder
        +hasSnake(int position) boolean
        +hasLadder(int position) boolean
    }

    class Snake {
        -int head
        -int tail
        +Snake(int head, int tail)
        +getHead() int
        +getTail() int
    }

    class Ladder {
        -int bottom
        -int top
        +Ladder(int bottom, int top)
        +getBottom() int
        +getTop() int
    }

    class Dice {
        -DiceStrategy strategy
        +Dice()
        +Dice(DiceStrategy strategy)
        +roll() int
        +setStrategy(DiceStrategy strategy)
    }

    class DiceStrategy {
        <<interface>>
        +roll() int
        +getMaxValue() int
    }

    class StandardDice {
        -Random random
        -int maxValue
        +roll() int
        +getMaxValue() int
    }

    class GameObserver {
        <<interface>>
        +onPlayerMoved(Player player, int oldPos, int newPos, int diceRoll)
        +onSnakeEncountered(Player player, int head, int tail)
        +onLadderEncountered(Player player, int bottom, int top)
        +onGameWon(Player winner)
        +onGameStarted()
        +onTurnChanged(Player currentPlayer)
    }

    class ConsoleGameObserver {
        +onPlayerMoved(Player player, int oldPos, int newPos, int diceRoll)
        +onSnakeEncountered(Player player, int head, int tail)
        +onLadderEncountered(Player player, int bottom, int top)
        +onGameWon(Player winner)
        +onGameStarted()
        +onTurnChanged(Player currentPlayer)
    }

    class Command {
        <<interface>>
        +execute()
        +undo()
    }

    class MovePlayerCommand {
        -Player player
        -int steps
        -int previousPosition
        +MovePlayerCommand(Player player, int steps)
        +execute()
        +undo()
    }

    class GameEngine {
        -List<Player> players
        -GameBoard board
        -Dice dice
        -int currentPlayerIndex
        -boolean gameWon
        -List<GameObserver> observers
        -Stack<Command> commandHistory
        +addPlayer(Player player)
        +addObserver(GameObserver observer)
        +startGame()
        +playTurn() boolean
        +undoLastMove()
        +getCurrentPlayer() Player
    }

    class GameComponentFactory {
        +createDefaultSnakes() List<Snake>
        +createDefaultLadders() List<Ladder>
        +createRandomSnakes(int count, int boardSize) List<Snake>
        +createRandomLadders(int count, int boardSize) List<Ladder>
        +createSnake(int head, int tail) Snake
        +createLadder(int bottom, int top) Ladder
    }

    class GameCLI {
        -GameEngine gameEngine
        -Scanner scanner
        +GameCLI()
        +startGame()
        -setupPlayers()
        -displayBoard()
        -playGame()
        -displayCurrentPositions()
    }

    class SnakeLadderGame {
        +main(String[] args)
    }

    %% Relationships
    GameBoard ||--o{ Snake : contains
    GameBoard ||--o{ Ladder : contains
    GameEngine ||--o{ Player : manages
    GameEngine ||--|| GameBoard : uses
    GameEngine ||--|| Dice : uses
    GameEngine ||--o{ GameObserver : notifies
    GameEngine ||--o{ Command : executes
    
    Dice ||--|| DiceStrategy : uses
    DiceStrategy <|.. StandardDice : implements
    
    GameObserver <|.. ConsoleGameObserver : implements
    
    Command <|.. MovePlayerCommand : implements
    MovePlayerCommand ||--|| Player : moves
    
    GameCLI ||--|| GameEngine : uses
    SnakeLadderGame ||--|| GameCLI : creates
    
    GameComponentFactory ..> Snake : creates
    GameComponentFactory ..> Ladder : creates
```

## 🤝 Contributing

Feel free to contribute to this project by:
1. Adding new dice strategies
2. Implementing GUI interface
3. Adding game variations
4. Improving CLI experience
5. Adding unit tests

## 📄 License

This project is open source and available under the MIT License.

---

**Enjoy playing Snake & Ladder!** 🎮🐍🪜
