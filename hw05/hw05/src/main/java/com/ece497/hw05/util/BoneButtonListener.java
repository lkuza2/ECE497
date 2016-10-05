package com.ece497.hw05.util;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
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
        System.out.println("TEST");

        switch(pin) {
            case BBBNames.P9_12:
                break;
            default:
                break;
        }

//        EtchASketchTable etchASketchTable = MainUtil.getInstance().getTable();
//        if (etchASketchTable != null) {
//            switch (pin) {
//                case BBBNames.P8_13:
//                    //left arrow
//                    etchASketchTable.handleKeyStroke(KeyStroke.fromString("<left>"));
//                    break;
//                case BBBNames.P9_14:
//                    //right arrow
//                    etchASketchTable.handleKeyStroke(KeyStroke.fromString("<right>"));
//                    break;
//                case BBBNames.P9_16:
//                    //down arrow
//                    etchASketchTable.handleKeyStroke(KeyStroke.fromString("<down>"));
//                    break;
//                case BBBNames.P9_42:
//                    //up arrow
//                    etchASketchTable.handleKeyStroke(KeyStroke.fromString("<up>"));
//                    break;
//                default:
//                    break;
//            }
//        }

    }

    @Override
    public void buttonReleased() {
    }
}
