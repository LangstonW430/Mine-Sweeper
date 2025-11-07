
/**
 * Class Name: MenuPanel
 *
 * Description:
 * The MenuPanel class represents a vertical menu interface used in the
 * Minesweeper game.
 * It displays game information and provides user interaction through buttons
 * such as
 * Restart, Resume, New Game, and Quit. This panel is typically shown when the
 * game is paused
 * or when navigating the game's menu system.
 *
 * Instance Variables:
 * None explicitly declared at the class level (all components are created and
 * added within the constructor).
 *
 * Constructor:
 * - MenuPanel(int rows, int cols, int mines, GameWindow parent):
 * Initializes the panel with layout and spacing, displays current game
 * configuration
 * (rows, columns, and number of mines), and adds functional buttons for user
 * control.
 *
 * Methods:
 * - private JButton createMenuButton(String label, Runnable action):
 * Creates a standardized button with a given label and an associated action
 * when clicked.
 * Buttons are aligned and spaced vertically in the layout.
 */

import java.awt.*;
import javax.swing.*;

public class MenuPanel extends JPanel {

    public MenuPanel(int rows, int cols, int mines, GameWindow parent) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Info label
        JLabel info = new JLabel(rows + " x " + cols + " | " + mines + " mines");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(info);
        add(Box.createVerticalStrut(20));

        // Buttons
        add(createMenuButton("Restart", () -> parent.startNewGame(rows, cols, mines)));
        add(createMenuButton("Resume", () -> parent.showPanel("game")));
        add(createMenuButton("New Game", () -> parent.showPanel("start")));
        add(createMenuButton("Quit", () -> System.exit(0)));
    }

    /*
     * Name: createMenuButton
     * Parameters:
     * - String label: the text label to display on the button
     * - Runnable action: the action to perform when the button is clicked
     * Description: Creates a button with the given label, sets its alignment to
     * center,
     * and assigns an action to be executed when clicked. The button is given a
     * fixed size
     * and includes vertical spacing above it.
     */
    private JButton createMenuButton(String label, Runnable action) {
        JButton button = new JButton(label);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(_ -> action.run());
        button.setMaximumSize(new Dimension(200, 30));
        add(Box.createVerticalStrut(10));
        return button;
    }
}
