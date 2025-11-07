
/**
 * Class Name: Constants
 * 
 * Description:
 * This class defines fixed constant values used throughout the Minesweeper application.
 * It centralizes window dimensions, padding values, usable drawing area, and the window title,
 * making it easy to maintain and adjust UI layout parameters in one place.
 * 
 * Constants:
 * - WINDOW_WIDTH (int): The overall width of the application window in pixels.
 * - WINDOW_HEIGHT (int): The overall height of the application window in pixels.
 * - V_PADDING (int): Vertical padding reserved for UI elements outside the game grid.
 * - H_PADDING (int): Horizontal padding reserved for UI elements outside the game grid.
 * - USABLE_WIDTH (int): Effective width available for the game grid (WINDOW_WIDTH - H_PADDING).
 * - USABLE_HEIGHT (int): Effective height available for the game grid (WINDOW_HEIGHT - V_PADDING).
 * - TITLE (String): The title of the application window.
 */

public class Constants {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;
    
    public static final int V_PADDING = 100;
    public static final int H_PADDING = 0;

    public static final int USABLE_WIDTH = WINDOW_WIDTH - H_PADDING;
    public static final int USABLE_HEIGHT = WINDOW_HEIGHT - V_PADDING;

    public static final String TITLE = "Minesweeper";
}
