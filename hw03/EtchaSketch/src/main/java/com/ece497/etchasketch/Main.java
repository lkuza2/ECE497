package com.ece497.etchasketch;

import com.ece497.etchasketch.i2c.LEDMatrixv2;
import com.ece497.etchasketch.util.BoardUtil;
import com.ece497.etchasketch.util.MainUtil;
import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.platform.Board;

import java.io.IOException;

import static com.ece497.etchasketch.util.MainUtil.BUTTON_PINS;

/**
 * Main class to start entry into the program
 *
 * @author Luke Kuza
 */
public class Main {

    /**
     * Main entry point for the program
     *
     * @param args Arguements, unsused
     */
    public static void main(String args[]) {
        try {
            BoardUtil.getInstance().getBoard().getI2cBus(BBBNames.I2C_1).writeByte(0x00);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LEDMatrixv2 ledMatrix = new LEDMatrixv2(BoardUtil.getInstance().getBoard().getI2cBus(BBBNames.I2C_1), 112);
        ledMatrix.init();
        BoardUtil.getInstance().setLedMatrixv2(ledMatrix);

        BoardUtil.getInstance().attachInterruptToPin(BUTTON_PINS);
        MainUtil.getInstance().init();
    }

}
