# Minesweeper

A classic Minesweeper game implemented in Java using Swing for the graphical user interface. This project allows users to play the classic puzzle game with a simple, interactive UI.

## Features

- Interactive GUI using Java Swing
- Customizable board size and mine count (can be set in the code)
- Click to reveal cells
- Automatically reveals adjacent empty cells
- Basic win/loss detection

## Getting Started

These instructions will help you run the project on your local machine.

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- IDE or terminal to compile and run Java programs

### Running the Game

1. Clone the repository:

```bash
git clone https://github.com/LangstonW430/Mine-Sweeper.git
cd Mine-Sweeper
```

2. Compile the Java files:

```bash
javac *.java
```

3. Run the game:

```bash
java Main
```

## Project Structure

- `Board.java` – Manages the game board, mines, and cell states
- `Cell.java` – Represents each individual cell
- `GamePanel.java` – The main panel that displays the game board
- `MenuPanel.java` – Menu for starting/restarting the game
- `Main.java` – Entry point for the application

## Future Improvements

- Add a timer and mine counter
- Improve GUI visuals and animations
