
/**
 * Minesweeper Game
 * 
 * This program implements the classic Minesweeper game with a graphical user interface.
 * It allows users to start new games with customizable board sizes and mine counts,
 * interact with the game board by revealing cells or placing flags, and navigate
 * through a menu system. The game handles win/loss conditions and provides visual
 * feedback on the board.
 * 
 * The application is launched through this main class, which initializes the main game window.
 */

public class Minesweeper {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new GameWindow());
    }
}