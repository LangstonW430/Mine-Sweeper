
/**
 * Class Name: GameWindow
 *
 * Description:
 * The GameWindow class is the main application window for the Minesweeper game.
 * It manages navigation between different panels (StartPanel, GamePanel, MenuPanel)
 * using a CardLayout. This class is responsible for initializing the GUI,
 * switching between game states, and managing panel updates.
 *
 * Instance Variables:
 * - JPanel mainPanel: The container for all subpanels, controlled by a CardLayout.
 * - StartPanel startPanel: The panel where users select game settings before starting.
 * - GamePanel gamePanel: The main panel where the Minesweeper game is played.
 * - CardLayout cardLayout: Layout manager that switches between subpanels.
 * - MenuPanel menuPanel: The menu shown during gameplay, providing options to restart, resume, etc.
 *
 * Constructor:
 * - GameWindow():
 *   Sets up the initial window with default panels and layout.
 *   Adds StartPanel, GamePanel, and MenuPanel to the main panel and displays the window.
 *
 * Methods:
 * - void showPanel(String name):
 *   Switches the currently visible panel to the one identified by `name` (e.g., "start", "game", "menu").
 *
 * - void startNewGame(int rows, int cols, int mines):
 *   Creates and displays a new GamePanel with the specified configuration.
 *   Replaces the existing GamePanel if one already exists.
 *
 * - void openMenuPanel(int rows, int cols, int mines):
 *   Creates and displays a new MenuPanel with the specified game state information.
 *   Replaces the existing MenuPanel if one already exists.
 */

import java.awt.CardLayout;
import javax.swing.*;

public class GameWindow extends JFrame {
    private final JPanel mainPanel;
    private final StartPanel startPanel;
    private GamePanel gamePanel;
    private final CardLayout cardLayout;
    private MenuPanel menuPanel;

    public GameWindow() {
        // Initialize panels
        startPanel = new StartPanel(this);
        gamePanel = new GamePanel(10, 10, 0, this);
        menuPanel = new MenuPanel(10, 10, 0, this);

        // Set up the CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add the panels to mainPanel
        mainPanel.add(startPanel, "start");
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(gamePanel, "game");

        // Set up the main window
        setTitle(Constants.TITLE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setVisible(true);
    }

    /*
     * Name: showPanel
     * Parameters:
     * - String name: the identifier of the panel to display (e.g., "start", "game",
     * "menu")
     * Description: Displays the specified panel using the CardLayout and refreshes
     * the main panel to ensure
     * proper rendering.
     */
    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /*
     * Name: startNewGame
     * Parameters:
     * - int rows: number of rows for the new game board
     * - int cols: number of columns for the new game board
     * - int mines: number of mines to place on the game board
     * Description: Removes the existing GamePanel and creates a new one with the
     * specified dimensions and mine count.
     * Adds the new GamePanel to the main panel and switches the view to display the
     * game.
     */
    public void startNewGame(int rows, int cols, int mines) {
        mainPanel.remove(gamePanel);
        // Generates a game panel with the given dimensions
        gamePanel = new GamePanel(rows, cols, mines, this);

        mainPanel.add(gamePanel, "game");
        cardLayout.show(mainPanel, "game");

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /*
     * Name: openMenuPanel
     * Parameters:
     * - int rows: the number of rows in the current game board
     * - int cols: the number of columns in the current game board
     * - int mines: the number of mines in the current game board
     * Description: Replaces the current MenuPanel with a new one using the provided
     * game settings.
     * Updates the main panel to display the new menu.
     */
    public void openMenuPanel(int rows, int cols, int mines) {
        mainPanel.remove(menuPanel);
        menuPanel = new MenuPanel(rows, cols, mines, this);

        mainPanel.add(menuPanel, "menu");
        showPanel("menu");
    }
}
