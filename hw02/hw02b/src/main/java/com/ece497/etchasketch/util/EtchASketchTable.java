package com.ece497.etchasketch.util;

import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

/**
 * This is an extension of the Table object in the Lanterna framework for Terminal graphics, which allows the table to function as an etch-a-sketch
 *
 * @author Luke Kuza
 */
class EtchASketchTable extends Table<String> {

    /**
     * Constructor for etch a sketch, predefined as a string
     *
     * @param labels Labels for columns
     */
    EtchASketchTable(String... labels) {
        super(labels);
    }

    /**
     * This is modified from the parent class.  This method first checks if the key is an arrow key, and if it is, changes the current selection to an X,
     * then it runs the parents class super handleKeyStroke, which moves the cell to where the arrow key was pressed
     *
     * @param keyStroke The keystroke the user pressed
     * @return The result code
     */
    @Override
    public Result handleKeyStroke(KeyStroke keyStroke) {
        if ((keyStroke.getKeyType() == KeyType.ArrowUp) || (keyStroke.getKeyType() == KeyType.ArrowDown)
                || (keyStroke.getKeyType() == KeyType.ArrowRight) || (keyStroke.getKeyType() == KeyType.ArrowLeft))
            getTableModel().setCell(getSelectedColumn(), getSelectedRow(), "X");

        return super.handleKeyStroke(keyStroke);
    }
}
