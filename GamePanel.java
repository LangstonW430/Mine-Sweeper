
/**
 * Class Name: GamePanel
 *
 * Description:
 * The GamePanel class represents the main playing area of the Minesweeper game.
 * It manages the visual grid of tiles and interacts with the underlying game logic
 * through the Board class. It handles user input such as left and right mouse clicks
 * on tiles, updates tile icons based on game state, and manages the UI layout including
 * top and bottom control panels.
 *
 * Instance Variables:
 * - JLabel[][] tiles: A 2D array of JLabels representing the visible tiles on the board.
 * - Board gameBoard: The underlying model of the Minesweeper board containing game logic.
 * - int rows: Number of rows in the game grid.
 * - int cols: Number of columns in the game grid.
 * - JPanel gridPanel: The panel that holds the grid of tiles using GridLayout.
 * - int mines: Number of mines placed on the board.
 *
 * Constructor:
 * - GamePanel(int rows, int cols, int mines, GameWindow parent):
 *   Initializes the game panel with the specified grid size and mine count.
 *   Sets up the grid, creates and adds tile labels, and arranges UI components.
 *
 * Key Methods:
 * - createTopPanel(): Creates a top panel (e.g., for status/info display).
 * - createBottomPanel(GameWindow parent): Creates a bottom panel with a Menu button.
 * - createMenuButton(GameWindow parent): Creates a button that opens the menu panel.
 * - initializeTiles(): Creates all tile JLabels and adds them to the gridPanel.
 * - createTileLabel(int row, int col): Creates a JLabel for a single tile with
 *   mouse listeners for left and right clicks.
 * - rightClickTile(int row, int col): Handles right-click events to toggle flags and marks.
 * - handleTileClick(int row, int col): Handles left-click events to reveal tiles, handle mines,
 *   auto-clear nearby empty tiles, and check for win/loss conditions.
 * - loseGame(): Handles logic for when the player clicks on a mine (game over).
 * - autoClear(int row, int col): Recursively reveals adjacent tiles if they have no neighboring mines.
 * - revealMines(): Reveals all mines on the board (typically after losing).
 * - changeIcon(int row, int col, String name): Changes the icon of a tile to represent different states.
 * - getCols(), getRows(), getMines(): Accessor methods for grid dimensions and mine count.
 *
 * Event Handling:
 * - Left mouse click reveals a tile.
 * - Right mouse click toggles flag/question mark/uncover states on a tile.
 *
 * Layout:
 * - Uses GridBagLayout to arrange a top panel, the gridPanel in the center,
 *   and a bottom panel with a menu button.
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GamePanel extends JPanel {

    private final JLabel[][] tiles;
    private final Board gameBoard;
    private final int rows, cols;
    private final JPanel gridPanel;
    private final int mines;

    public GamePanel(int rows, int cols, int mines, GameWindow parent) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;

        // Create panels
        gridPanel = new JPanel(new GridLayout(rows, cols, 0, 0)); // Create a grid layout

        tiles = new JLabel[rows][cols];
        gameBoard = new Board(rows, cols, mines);

        initializeTiles();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        add(createTopPanel(), gbc);

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridy = 2;
        add(createBottomPanel(parent), gbc);

        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(gridPanel, gbc);
    }

    /*
     * Name: createTopPanel
     * Parameters: none
     * Description: Creates and returns a JPanel with a fixed size based on window
     * width and vertical padding constants.
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setSize(Constants.WINDOW_WIDTH, Constants.V_PADDING);
        return topPanel;
    }

    /*
     * Name: createBottomPanel
     * Parameters: GameWindow parent – the main game window used for creating menu
     * button actions
     * Description: Creates and returns a JPanel with a fixed size and adds a menu
     * button linked to the parent window.
     */
    private JPanel createBottomPanel(GameWindow parent) {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setSize(Constants.WINDOW_WIDTH, Constants.V_PADDING);
        bottomPanel.add(createMenuButton(parent));
        return bottomPanel;
    }

    /*
     * Name: createMenuButton
     * Parameters: GameWindow parent – the main game window used to open the menu
     * panel
     * Description: Creates and returns a JButton labeled "Menu" that triggers the
     * opening of the menu panel with current game settings when clicked.
     */
    private JButton createMenuButton(GameWindow parent) {
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(_ -> parent.openMenuPanel(rows, cols, mines));
        return menuButton;
    }

    /*
     * Name: initializeTiles
     * Parameters: none
     * Description: Initializes the grid of tile labels by creating each tile and
     * adding it to the grid panel.
     */
    private void initializeTiles() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                tiles[r][c] = createTileLabel(r, c);
                gridPanel.add(tiles[r][c]);
            }
        }
    }

    /*
     * Name: createTileLabel
     * Parameters:
     * - int row: the row index of the tile
     * - int col: the column index of the tile
     * Description: Creates and returns a JLabel representing a tile at the
     * specified row and column,
     * with an image icon scaled to fit the grid. Adds mouse listeners to handle
     * left and right clicks on the tile.
     */
    private JLabel createTileLabel(int row, int col) {
        ImageIcon tile = new ImageIcon("Assets/MS uncovered.png");
        int dimension = Math.min(Constants.USABLE_WIDTH / cols, Constants.USABLE_HEIGHT / rows);
        Image scaledTile = tile.getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        tile = new ImageIcon(scaledTile);

        JLabel label = new JLabel(tile);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    handleTileClick(row, col);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    rightClickTile(row, col);
                }
            }
        });
        return label;
    }

    /*
     * Name: rightClickTile
     * Parameters:
     * - int row: the row index of the tile
     * - int col: the column index of the tile
     * Description: Handles the right-click action on a tile at the specified
     * position.
     * Toggles the tile's state among flagged, marked, and uncovered icons, updating
     * the underlying cell's flags accordingly.
     * Does nothing if the game is not active or if the cell has already been
     * clicked.
     */
    private void rightClickTile(int row, int col) {

        if (!gameBoard.isActive()) {
            return;
        }

        Cell cell = gameBoard.cellAt(row, col);

        if (cell.isClicked()) {
            return;
        }

        if (cell.isFlaged()) {
            changeIcon(row, col, "question mark");
            cell.setFlaged(false);
            cell.setMarked(true);
        } else if (cell.isMarked()) {
            changeIcon(row, col, "uncovered");
            cell.setMarked(true);
        } else {
            changeIcon(row, col, "flag");
            cell.setFlaged(true);
        }
    }

    /*
     * Name: handleTileClick
     * Parameters:
     * - int row: the row index of the clicked tile
     * - int col: the column index of the clicked tile
     * Description: Processes a left-click on a tile at the given position.
     * If the game is inactive, or the cell is flagged or already clicked, it
     * returns immediately.
     * Otherwise, marks the cell as clicked and handles the game logic for mines,
     * auto-clearing, updating icons,
     * decrementing remaining tiles, and ending the game if all safe tiles are
     * revealed.
     */
    private void handleTileClick(int row, int col) {
        if (!gameBoard.isActive()) {
            return;
        }
        Cell cell = gameBoard.cellAt(row, col);

        if (cell.isFlaged()) {
            return;
        }

        if (cell.isClicked()) {
            return;
        }

        cell.setClicked(true);

        if (cell.isMine()) {
            revealMines();
            changeIcon(row, col, "explosion");
            loseGame();
            return;
        }

        if (gameBoard.nearMines(row, col) == 0) {
            autoClear(row, col);
        }

        changeIcon(row, col, gameBoard.nearMines(row, col) + "");
        gameBoard.setRemainingTiles(gameBoard.getRemainingTiles() - 1);
        if (gameBoard.getRemainingTiles() == 0) {
            gameBoard.setActive(false);
        }
    }

    /*
     * Name: loseGame
     * Parameters: none
     * Description: Sets the game state to inactive, indicating the player has lost
     * the game.
     */
    private void loseGame() {
        gameBoard.setActive(false);
    }

    /*
     * Name: autoClear
     * Parameters: int row, int col
     * Description: Recursively clears adjacent tiles that have not been clicked,
     * expanding from the given row and column.
     */
    private void autoClear(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && j >= 0 && i < rows && j < cols) {
                    if (!gameBoard.cellAt(i, j).isClicked()) {
                        handleTileClick(i, j);
                    }
                }
            }
        }
    }

    /*
     * Name: revealMines
     * Parameters: none
     * Description: Reveals all mine locations by changing their icons to indicate
     * mines.
     */
    private void revealMines() {
        for (Point p : gameBoard.getMineLocations()) {
            int x = p.x;
            int y = p.y;

            changeIcon(x, y, "mine");
        }

    }

    /*
     * Name: changeIcon
     * Parameters:
     * - int row: the row index of the tile
     * - int col: the column index of the tile
     * - String name: the name of the icon image to display
     * Description: Changes the icon of the specified tile to the image
     * corresponding to the given name, scaling it appropriately.
     */
    private void changeIcon(int row, int col, String name) {
        ImageIcon clickedIcon = new ImageIcon("Assets/MS " + name + ".png");
        int dimension = Math.min(Constants.USABLE_WIDTH / cols, Constants.USABLE_HEIGHT / rows);
        Image scaledIcon = clickedIcon.getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        clickedIcon = new ImageIcon(scaledIcon);

        tiles[row][col].setIcon(clickedIcon);
    }

    /*
     * Name: getCols
     * Returns: int - the number of columns in the board
     * Description: Retrieves the number of columns.
     */
    public int getCols() {
        return cols;
    }

    /*
     * Name: getRows
     * Returns: int - the number of rows in the board
     * Description: Retrieves the number of rows.
     */
    public int getRows() {
        return rows;
    }

    /*
     * Name: getMines
     * Returns: int - the number of mines on the board
     * Description: Retrieves the total count of mines.
     */
    public int getMines() {
        return mines;
    }

}
