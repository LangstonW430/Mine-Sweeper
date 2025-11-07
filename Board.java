
/**
 * Class Name: Board
 * 
 * Description:
 * Represents the Minesweeper game board. It manages the state of the board including the 
 * placement of mines, tracking which cells are revealed or flagged, and maintaining the 
 * game's active status and remaining non-mine tiles. The board also provides utility methods 
 * to calculate the number of mines or flags adjacent to a given cell.
 * 
 * Fields:
 * - rows (int): Number of rows in the board.
 * - cols (int): Number of columns in the board.
 * - mines (int): Number of mines placed on the board.
 * - board (Cell[][]): 2D array of Cell objects representing the board's grid.
 * - isActive (boolean): Indicates if the game is currently active.
 * - remainingTiles (int): Number of non-mine cells yet to be revealed.
 * - mineLocations (List<Point>): List of coordinates where mines are placed.
 * 
 * Key Methods:
 * - Board(int rows, int cols, int mines): Constructor that initializes the board with 
 *   specified dimensions and randomly places mines.
 * - nearMines(int x, int y): Returns the count of mines adjacent to the cell at (x, y).
 * - nearFlags(int x, int y): Returns the count of flagged cells adjacent to the cell at (x, y).
 * - cellAt(int row, int col): Returns the Cell object at the specified location.
 * - isActive(), setActive(boolean): Get or set the active state of the game.
 * - getRemainingTiles(), setRemainingTiles(int): Accessors for the count of unrevealed non-mine cells.
 * - getMineLocations(): Returns a list of all mine locations on the board.
 */

import java.awt.Point;
import java.util.*;

public class Board {
    private final int rows, cols;
    private final int mines;
    private final Cell[][] board;
    private boolean isActive;
    private int remainingTiles;
    private final List<Point> mineLocations;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Board(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        isActive = true;
        remainingTiles = rows * cols - mines;

        board = new Cell[rows][cols];
        mineLocations = new ArrayList<>();
        Random gen = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell(false);
            }
        }

        int counter = 0;
        while (counter < this.mines) {
            int randomRow = gen.nextInt(rows);
            int randomCol = gen.nextInt(cols);

            
            if (!cellAt(randomRow, randomCol).isMine()) {
                cellAt(randomRow, randomCol).setMine(true);
                counter++;
                mineLocations.add(new Point(randomRow, randomCol));
            }
        }
    }

    /*
     * Name: nearMines
     * Parameters:
     * - int x: the row index of the cell to check around
     * - int y: the column index of the cell to check around
     * Returns:
     * - int: the count of mines in the neighboring cells surrounding (x, y)
     * Description:
     * Calculates and returns the number of mines adjacent to the cell at position
     * (x, y),
     * including diagonally adjacent cells. The method checks boundaries to avoid
     * indexing outside the board.
     */
    public int nearMines(int x, int y) {
        int near = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && j >= 0 && i < rows && j < cols && cellAt(i, j).isMine()) {
                    near++;
                }
            }
        }

        return near;
    }

    /*
     * Name: nearFlags
     * Parameters:
     * - int x: the x-coordinate of the target cell
     * - int y: the y-coordinate of the target cell
     * Description: Counts and returns the number of flagged cells surrounding the
     * specified cell at (x, y), including diagonals. Checks boundaries to avoid
     * out-of-bounds errors.
     */
    public int nearFlags(int x, int y) {
        int near = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && j >= 0 && i < rows && j < cols && cellAt(i, j).isFlaged()) {
                    near++;
                }
            }
        }

        return near;
    }

    /*
     * Name: cellAt
     * Parameters:
     * - int row: the row index of the cell
     * - int col: the column index of the cell
     * Description: Returns the Cell object located at the specified row and column
     * on the game board.
     */
    public Cell cellAt(int row, int col) {
        return board[row][col];
    }

    /*
     * Name: isActive
     * Parameters: none
     * Description: Returns whether the current cell is active (revealed or
     * clickable).
     */
    public boolean isActive() {
        return isActive;
    }

    /*
     * Name: setActive
     * Parameters: boolean isActive – the new active state to set for the cell
     * Description: Sets the active state of the cell to the specified value.
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /*
     * Name: getRemainingTiles
     * Parameters: none
     * Description: Returns the number of remaining unrevealed tiles on the board.
     */
    public int getRemainingTiles() {
        return remainingTiles;
    }

    /*
     * Name: setRemainingTiles
     * Parameters: int remainingTiles - the number of remaining unrevealed tiles to
     * set
     * Description: Sets the number of remaining unrevealed tiles on the board.
     */
    public void setRemainingTiles(int remainingTiles) {
        this.remainingTiles = remainingTiles;
    }

    /*
     * Name: getMineLocations
     * Parameters: none
     * Description: Returns a list of Points representing the locations of all mines
     * on the board.
     */
    public List<Point> getMineLocations() {
        return mineLocations;
    }

}
