
/**
 * Class Name: StartPanel
 *
 * Description:
 * The StartPanel class is a graphical user interface panel used as the main
 * menu screen
 * in a Minesweeper game. It provides users with options to start a game using
 * preset
 * difficulty levels or to configure a custom game by specifying the number of
 * rows,
 * columns, and mines. The panel includes a styled title, a logo image, and
 * interactive
 * buttons for game configuration. It is designed using Java Swing and relies on
 * layout
 * managers such as BoxLayout and GridBagLayout for component arrangement.
 *
 * Variables:
 * - JTextField rowsField: Input field for the number of rows in a custom game.
 * - JTextField colsField: Input field for the number of columns in a custom
 * game.
 * - JTextField minesField: Input field for the number of mines in a custom
 * game.
 * - int TITLE_HEIGHT: Fixed height for the title row, based on window
 * dimensions.
 * - int LOGO_SIZE: Fixed size for the logo image, based on window height.
 * - int DIFFICULTY_ROW_HEIGHT: Height for the difficulty selection row.
 * - int CUSTOM_ROW_HEIGHT: Height for custom game configuration rows.
 *
 * Methods:
 * - StartPanel(GameWindow parent): Constructor that sets up the panel layout
 * and adds
 * UI components for title, logo, and difficulty options.
 * - JPanel createTitleRow(): Creates a centered title label in a fixed-height
 * row.
 * - JPanel createLogoRow(): Loads and scales a logo image for display.
 * - JPanel createDifficultyRow(GameWindow parent): Combines preset and custom
 * game
 * configuration UI elements.
 * - JPanel createPresetsRow(GameWindow parent): Adds buttons for Easy, Medium,
 * and Hard
 * difficulty levels.
 * - JPanel createCustomRow(GameWindow parent): Adds a custom button that reads
 * input
 * from text fields and attempts to start a new game with custom settings.
 * - JPanel createCustomSettingsRow(): Sets up text fields for custom game
 * parameters.
 * - JButton createPreset(String name, int rows, int cols, int mines, GameWindow
 * parent):
 * Creates a difficulty button that starts a new game when clicked.
 * - void entryPrompt(JTextField textField, String message): Adds placeholder
 * behavior
 * to text fields for guidance.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartPanel extends JPanel {

    private JTextField rowsField;
    private JTextField colsField;
    private JTextField minesField;
    private final int TITLE_HEIGHT = Constants.WINDOW_HEIGHT / 6;
    private final int LOGO_SIZE = Constants.WINDOW_HEIGHT / 2;
    private final int DIFFICULTY_ROW_HEIGHT = Constants.WINDOW_HEIGHT * 3 / 10;
    private final int CUSTOM_ROW_HEIGHT = Constants.WINDOW_HEIGHT / 10;

    public StartPanel(GameWindow parent) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createTitleRow());
        add(createLogoRow());
        add(createDifficultyRow(parent));

    }

    /*
     * Name: createTitleRow
     * Parameters: none
     * Description: Creates and returns a JPanel containing a centered "Minesweeper"
     * title label,
     * styled with a large font and laid out using GridBagLayout to ensure it
     * remains
     * centered within a fixed height panel.
     */
    private JPanel createTitleRow() {
        JPanel titleRow = new JPanel();
        titleRow.setMaximumSize(new Dimension(Constants.WINDOW_WIDTH, TITLE_HEIGHT));
        titleRow.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Minesweeper");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        titleRow.add(title, gbc);

        return titleRow;
    }

    /*
     * Name: createLogoRow
     * Parameters: none
     * Description: Creates and returns a JPanel containing a scaled image logo
     * (from the "Assets/MS flag.png" path) with a fixed height. The image
     * is resized smoothly to fit within the designated logo size.
     */
    private JPanel createLogoRow() {
        JPanel logoRow = new JPanel();
        logoRow.setMaximumSize(new Dimension(Constants.WINDOW_WIDTH, LOGO_SIZE));

        ImageIcon icon = new ImageIcon("Assets/MS flag.png");
        Image scaledImage = icon.getImage().getScaledInstance(LOGO_SIZE, LOGO_SIZE, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(icon);
        logoRow.add(imageLabel);

        return logoRow;
    }

    /*
     * Name: createDifficultyRow
     * Parameters: GameWindow parent – the parent window used to start new games
     * Description: Creates and returns a JPanel containing difficulty selection
     * options.
     * This includes preset difficulty buttons, a custom game toggle, and
     * input fields for custom game settings, all stacked vertically.
     */
    private JPanel createDifficultyRow(GameWindow parent) {
        JPanel difficultyRow = new JPanel();
        difficultyRow.setMaximumSize(new Dimension(Constants.WINDOW_WIDTH, DIFFICULTY_ROW_HEIGHT));
        difficultyRow.setLayout(new BoxLayout(difficultyRow, BoxLayout.Y_AXIS));

        difficultyRow.add(createPresetsRow(parent));
        difficultyRow.add(createCustomRow(parent));
        difficultyRow.add(createCustomSettingsRow());

        return difficultyRow;
    }

    /*
     * Name: createCustomSettingsRow
     * Parameters: None
     * Description: Creates and returns a JPanel containing text fields for users to
     * enter custom game settings (rows, columns, and number of mines).
     * Each field includes a placeholder prompt for user guidance.
     */
    private JPanel createCustomSettingsRow() {
        JPanel customSettingsRow = new JPanel();
        customSettingsRow.setMaximumSize(new Dimension(Constants.WINDOW_WIDTH, CUSTOM_ROW_HEIGHT));

        rowsField = new JTextField("Rows");
        customSettingsRow.add(rowsField);
        entryPrompt(rowsField, "Rows");

        colsField = new JTextField("Cols");
        customSettingsRow.add(colsField);
        entryPrompt(colsField, "Cols");

        minesField = new JTextField("Mines");
        customSettingsRow.add(minesField);
        entryPrompt(minesField, "Mines");

        return customSettingsRow;
    }

    /*
     * Name: createCustomRow
     * Parameters: GameWindow parent – the main game window used to start a new game
     * Description: Creates and returns a JPanel containing a "Custom" button. When
     * clicked,
     * the button reads values from the custom settings text fields (rows, cols,
     * mines),
     * validates them, and starts a new game with the specified settings. Displays
     * an error message if inputs are invalid or improperly formatted.
     */
    private JPanel createCustomRow(GameWindow parent) {
        JPanel customRow = new JPanel();
        customRow.setMaximumSize(new Dimension(Constants.WINDOW_WIDTH, CUSTOM_ROW_HEIGHT));

        JButton customButton = new JButton("Custom");
        customButton.addActionListener(_ -> {
            try {

                int customRowsEntry = Integer.parseInt(rowsField.getText());
                int customColsEntry = Integer.parseInt(colsField.getText());
                int customMinesEntry = Integer.parseInt(minesField.getText());

                if (customRowsEntry > 0 && customColsEntry > 0 && customMinesEntry > 0
                        && customRowsEntry * customColsEntry > customMinesEntry) {
                    parent.startNewGame(customRowsEntry, customColsEntry, customMinesEntry);
                } else {
                    JOptionPane.showMessageDialog(parent, "Invalid input");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Please enter valid numbers for size and mines.");
            }
        });
        customRow.add(customButton);

        return (customRow);
    }

    /*
     * Name: createPresetsRow
     * Parameters: GameWindow parent – the main game window used to start a new game
     * Description: Creates and returns a JPanel containing three preset difficulty
     * buttons:
     * Easy, Medium, and Hard. Each button, when clicked, starts a new game
     * with predefined board sizes and mine counts.
     */
    private JPanel createPresetsRow(GameWindow parent) {
        JPanel presetsRow = new JPanel();
        presetsRow.setMaximumSize(new Dimension(Constants.WINDOW_WIDTH, CUSTOM_ROW_HEIGHT));

        presetsRow.add(createPreset("Easy", 9, 9, 10, parent));
        presetsRow.add(createPreset("Medium", 16, 16, 40, parent));
        presetsRow.add(createPreset("Hard", 16, 30, 99, parent));

        return presetsRow;
    }

    /*
     * Name: createPreset
     * Parameters:
     * - String name: the label for the preset button
     * - int rows: number of rows for the game board
     * - int cols: number of columns for the game board
     * - int mines: number of mines for the game
     * - GameWindow parent: reference to the main game window to start a new game
     * Description: Creates and returns a JButton labeled with the given preset name
     * and dimensions.
     * When clicked, the button starts a new game with the specified settings.
     */
    private JButton createPreset(String name, int rows, int cols, int mines, GameWindow parent) {
        JButton preset = new JButton(name + " (" + rows + "x" + cols + ")");
        preset.addActionListener(_ -> parent.startNewGame(rows, cols, mines));
        return preset;
    }

    /*
     * Name: entryPrompt
     * Parameters:
     * - JTextField textField: the text field to apply the prompt behavior to
     * - String message: the default message to display in the text field
     * Description: Sets up a focus listener for the given JTextField that shows a
     * placeholder message
     * when the field is empty and restores the message when the field loses focus
     * if it is empty.
     */
    private void entryPrompt(JTextField textField, String message) {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(message)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(message);
                }
            }
        });
    }
}
