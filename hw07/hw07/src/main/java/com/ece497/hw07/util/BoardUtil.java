package com.ece497.hw07.util;

import io.silverspoon.bulldog.core.Signal;
import io.silverspoon.bulldog.core.gpio.DigitalInput;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.devices.switches.Button;

/**
 * This class controls the beagleboard IO in Java
 *
 * @author Luke Kuza
 */
public class BoardUtil {

    /**
     * instance
     */
    private static BoardUtil instance;
    //Object representing board
    Board board;

    private BoardUtil() {
    }

    /**
     * Gets the current instance
     *
     * @return current instance
     */
    public static BoardUtil getInstance() {
        if (instance == null)
            instance = new BoardUtil();
        instance.setBoard(Platform.createBoard());
        return instance;
    }

    /**
     * Attaches an interrupt to an array of beagleboard pins
     *
     * @param pins Array of pins from BBBNames.
     */
    public void attachInterruptToPin(String[] pins) {
        for (String pin : pins) {
            attachInterruptToPin(pin);
        }
    }

    /**
     * Attaches an interrupt to a signle BB pin
     *
     * @param pin Pin from object BBBNames
     */
    public void attachInterruptToPin(String pin) {
        DigitalInput digitalInput = BoardUtil.getInstance().getBoard().getPin(pin).as(DigitalInput.class);
        Button button = new Button(digitalInput, Signal.Low);

        //button.addListener(new BoneButtonListener(pin));
    }

    /**
     * Get board object
     *
     * @return Board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * set the board object
     *
     * @param board board object
     */
    public void setBoard(Board board) {
        this.board = board;
    }


}
