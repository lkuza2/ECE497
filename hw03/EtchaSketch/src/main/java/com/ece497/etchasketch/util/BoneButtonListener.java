package com.ece497.etchasketch.util;

import com.googlecode.lanterna.input.KeyStroke;
import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.devices.switches.ButtonListener;

/**
 * Custom listener class that handles button presses from the BB
 */
public class BoneButtonListener implements ButtonListener {

    /**
     * What pin does this listener manage?
     */
    private String pin;

    /**
     * Constructor class for listener
     *
     * @param pin Pin this listener is assigned to
     */
    public BoneButtonListener(String pin) {
        this.pin = pin;
    }

    /**
     * Handles button press, and maps it to an input command to the bone
     */
    @Override
    public void buttonPressed() {
        EtchASketchTable etchASketchTable = MainUtil.getInstance().getTable();
        if (etchASketchTable != null) {
            int x = MainUtil.getInstance().getX();
            int y = MainUtil.getInstance().getY();
            BoardUtil.getInstance().getLedMatrixv2().setLED(x, y, true, false);

            switch (pin) {
                case BBBNames.P8_13:
                    //left arrow
                    etchASketchTable.handleKeyStroke(KeyStroke.fromString("<left>"));
                    x++;
                    if (x == 8)
                        x = 0;
                    break;
                case BBBNames.P9_14:
                    //right arrow
                    etchASketchTable.handleKeyStroke(KeyStroke.fromString("<right>"));
                    x--;
                    if (x == -1)
                        x = 7;

                    break;
                case BBBNames.P9_16:
                    //down arrow
                    etchASketchTable.handleKeyStroke(KeyStroke.fromString("<down>"));
                    y++;
                    if (y == 8)
                        y = 0;
                    break;
                case BBBNames.P9_42:
                    //up arrow
                    etchASketchTable.handleKeyStroke(KeyStroke.fromString("<up>"));
                    y--;
                    if (y == -1)
                        y = 7;
                    break;
                default:
                    break;
            }
            BoardUtil.getInstance().getLedMatrixv2().setLED(x, y, true, true);
            MainUtil.getInstance().setX(x);
            MainUtil.getInstance().setY(y);
        }

    }

    @Override
    public void buttonReleased() {

    }
}
