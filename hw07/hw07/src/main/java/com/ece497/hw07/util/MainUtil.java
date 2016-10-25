package com.ece497.hw07.util;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.gpio.DigitalInput;
import io.silverspoon.bulldog.core.gpio.DigitalOutput;

/**
 * Created by kuzalj on 9/19/2016.
 */
public class MainUtil {

    //public boolean running = true;

    public void init() {

        DigitalOutput output = BoardUtil.getInstance().getBoard().getPin(BBBNames.P9_29).as(DigitalOutput.class);
        DigitalInput input = BoardUtil.getInstance().getBoard().getPin(BBBNames.P9_28).as(DigitalInput.class);

        while (true) {
            // go forever nigga
            output.write(input.read());
        }

    }
}
