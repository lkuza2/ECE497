package com.ece497.etchasketch.util;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import io.silverspoon.bulldog.beagleboneblack.BBBNames;

import java.io.IOException;
import java.util.Arrays;

/**
 * Main utility that will set up the and manage the game
 *
 * @author Luke Kuza
 */
public class MainUtil {

    /**
     * Instance variable
     */
    private static MainUtil instance;

    /**
     * Gets current application Instance
     *
     * @return instance
     */
    public static MainUtil getInstance() {
        if (instance == null)
            instance = new MainUtil();
        return instance;
    }

    /**
     * Constructor made private
     */
    private MainUtil() {

    }

    /**
     * Contains the value for rows for the game
     */
    private int rows;
    /**
     * Contains the value for columns for the game
     */
    private int cols;


    /**
     * Initialize the entire game
     */
    public void init() {
        createInterface();
    }

    /**
     * Represents the pins the BB uses for buttons
     */
    public static final String[] BUTTON_PINS = {BBBNames.P9_14, BBBNames.P9_16, BBBNames.P9_42, BBBNames.P8_13};

    /**
     * Object for the etchasketch table
     */
    private EtchASketchTable etable;

    /**
     * Creates and manages the interface
     */
    private void createInterface() {
        // Setup terminal and screen layers
        try {
            // Setup terminal and screen layers
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.Indexed.fromRGB(100, 149, 237)));
            BasicWindow window = new BasicWindow();


            // Create panel to hold components
            Panel panel = new Panel();
            panel.setPreferredSize(new TerminalSize(0, 0));

            panel.setLayoutManager(new BorderLayout());

            // Sets the textbox to be in the center of the screen
            Label label = new Label("Welcome to Etch-a-Sketch! It was written by Luke Kuza " +
                    "using the Lanterna framework for the terminal graphics and is written in java. Created for ECE497.  Enjoy!");
            label.setLayoutData(BorderLayout.Location.CENTER);
            panel.addComponent(label);

            //Create start button and handle events
            Button start = new Button("Start", new Runnable() {
                @Override
                public void run() {
                    //Start Application

                    //Remove all panel components to add etchasketch
                    panel.removeAllComponents();
                    window.setHints(Arrays.asList(Window.Hint.CENTERED));

                    //Create dialog boxes to acquire the rows and columns the user wants
                    setRows(Integer.parseInt(TextInputDialog.showDialog(gui, "Enter Rows", "Enter the amount of rows for your Etch-a-Skech", "8")));
                    setCols(Integer.parseInt(TextInputDialog.showDialog(gui, "Enter Columns", "Enter the amount of columns for your Etch-a-Skech", "8")));

                    //Create a new panel for the game
                    Panel panelGame = new Panel();

                    //Create empty arrays to pre allocate the space needed for the etchasketch table
                    String[] colArray = new String[getCols()];
                    for (int i = 0; i < getCols(); i++)
                        colArray[i] = "-";

                    //Use extended EtchASketchTable object
                    EtchASketchTable table = new EtchASketchTable(colArray);
                    panelGame.setLayoutManager(new BorderLayout());


                    //Pre allocation of rows and columns as blank
                    for (int j = 0; j < getRows(); j++)
                        table.getTableModel().addRow(colArray);

                    for (int i = 0; i < getRows(); i++) {
                        for (int j = 0; j < getCols(); j++) {
                            table.getTableModel().setCell(j, i, "");
                        }
                    }
                    //Allow individual cell selection
                    table.setCellSelection(true);
                    table.setLayoutData(BorderLayout.Location.CENTER);

                    //Show brief instructions
                    MessageDialog.showMessageDialog(gui, "Instructions", "Press Enter to escape the Etch-A-Sketch, use arrow keys to traverse.", MessageDialogButton.OK);
                    panelGame.addComponent(table);

                    //Create panel for options such as shaking and quitting
                    Panel panelOptions = new Panel();
                    panelOptions.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

                    //Add shake button
                    panelOptions.addComponent(new Button("Shake", new Runnable() {
                        @Override
                        public void run() {
                            //Clear table
                            for (int i = 0; i < getRows(); i++) {
                                for (int j = 0; j < getCols(); j++) {
                                    table.getTableModel().setCell(j, i, "");
                                }
                            }
                        }
                    }));
                    //Add exit button
                    panelOptions.addComponent(new Button("Exit", new Runnable() {
                        @Override
                        public void run() {
                            //System Exit
                            try {
                                terminal.clearScreen();
                                terminal.resetColorAndSGR();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.exit(0);
                        }
                    }));
                    //Set layout for options panel and add to game board
                    panelOptions.setLayoutData(BorderLayout.Location.BOTTOM);
                    panelGame.addComponent(panelOptions);
                    window.setComponent(panelGame);
                    window.setHints(Arrays.asList(Window.Hint.CENTERED));
                    setTable(table);
                }
            });

            //Set layout for start panel and set full screen
            start.setLayoutData(BorderLayout.Location.BOTTOM);
            panel.addComponent(start);

            window.setComponent(panel);
            window.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));

            // Create gui and start gui
            gui.addWindowAndWait(window);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the rows for this etch a sketch
     *
     * @return Int rows
     */
    private int getRows() {
        return rows;
    }

    /**
     * Set rows for this etch-a-sketch
     *
     * @param rows The number of rows
     */
    private void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Get columns for this etch a sketch
     *
     * @return Int cols
     */
    private int getCols() {
        return cols;
    }

    /**
     * Set columns for this etch a sket
     *
     * @param cols The number of columns
     */
    private void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Get etch a sketch table object
     *
     * @return EtchASketchTable object
     */
    public EtchASketchTable getTable() {
        return etable;
    }

    /**
     * Sets the table object
     *
     * @param table The table object
     */
    public void setTable(EtchASketchTable table) {
        this.etable = table;
    }
}