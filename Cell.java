
/**
 * Class Name: Cell
 *
 * Description:
 * The Cell class represents a single square on the Minesweeper game board.
 * Each cell can contain a mine and can be in one of several states:
 * clicked, flagged, or marked (e.g., with a question mark).
 * This class stores the cell's state and provides getters and setters for each
 * property.
 *
 * Instance Variables:
 * - boolean isMine: Indicates whether the cell contains a mine.
 * - boolean isClicked: Indicates whether the cell has been revealed by the
 * player.
 * - boolean isFlaged: Indicates whether the cell has been flagged as a
 * potential mine.
 * - boolean isMarked: Indicates whether the cell has been marked (typically
 * with a question mark).
 *
 * Constructor:
 * - Cell(boolean isMine):
 * Initializes the cell with the specified mine status.
 * All other states (clicked, flagged, marked) are initialized to false.
 *
 * Methods:
 * - boolean isMine(): Returns true if the cell contains a mine.
 * - void setMine(boolean isMine): Sets whether this cell contains a mine.
 * - boolean isClicked(): Returns true if the cell has been clicked/revealed.
 * - void setClicked(boolean isClicked): Sets the clicked state of the cell.
 * - boolean isFlaged(): Returns true if the cell has been flagged.
 * - void setFlaged(boolean isFlaged): Sets the flagged state of the cell.
 * - boolean isMarked(): Returns true if the cell is marked (e.g., with a
 * question mark).
 * - void setMarked(boolean isMarked): Sets the marked state of the cell.
 */

public class Cell {
    boolean isMine;
    boolean isClicked;
    boolean isFlaged;
    boolean isMarked;

    public Cell(boolean isMine) {
        isClicked = false;
        isFlaged = false;
        isMarked = false;
        this.isMine = isMine;
    }

    /*
     * Name: isMine
     * Returns: boolean - whether the cell contains a mine
     * Description: Checks if this cell is a mine.
     */
    public boolean isMine() {
        return isMine;
    }

    /*
     * Name: setMine
     * Parameters:
     * - boolean isMine: whether to set this cell as a mine
     * Description: Sets the mine status of this cell.
     */
    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    /*
     * Name: isClicked
     * Parameters: None
     * Description: Returns whether this cell has been clicked.
     */
    public boolean isClicked() {
        return isClicked;
    }

    /*
     * Name: setClicked
     * Parameters:
     * - boolean isClicked: the clicked state to set for this cell
     * Description: Sets whether this cell has been clicked.
     */
    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    /*
     * Name: isFlaged
     * Parameters: none
     * Description: Returns whether this cell is currently flagged.
     */
    public boolean isFlaged() {
        return isFlaged;
    }

    /*
     * Name: setFlaged
     * Parameters: boolean isFlaged - the flag status to set for this cell
     * Description: Sets whether this cell is flagged or not.
     */
    public void setFlaged(boolean isFlaged) {
        this.isFlaged = isFlaged;
    }

    /*
     * Name: isMarked
     * Parameters: None
     * Description: Returns whether this cell is marked with a question mark.
     */
    public boolean isMarked() {
        return isMarked;
    }

    /*
     * Name: setMarked
     * Parameters:
     * - boolean isMarked: the new marked state of the cell
     * Description: Sets whether this cell is marked with a question mark.
     */
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

}
