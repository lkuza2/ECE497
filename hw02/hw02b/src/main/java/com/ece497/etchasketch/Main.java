package com.ece497.etchasketch;

import com.ece497.etchasketch.util.BoardUtil;
import com.ece497.etchasketch.util.MainUtil;

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
        BoardUtil.getInstance().attachInterruptToPin(BUTTON_PINS);
        MainUtil.getInstance().init();
    }

}
