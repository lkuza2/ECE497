package com.ece497.hw03.util;

import com.ece497.hw03.i2c.TMP101v2;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.gpio.DigitalInput;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by kuzalj on 9/19/2016.
 */
public class MainUtil {

    public void init(){
        //TMP101 tmp101First = new TMP101((byte) 0b1001001, (byte) 2);
        //TMP101 tmp101Second = new TMP101((byte) 0b1001000, (byte) 2);

        TMP101v2 tmp101First = new TMP101v2(BoardUtil.getInstance().getBoard().getI2cBus(BBBNames.I2C_1), 73);
        TMP101v2 tmp101Second = new TMP101v2(BoardUtil.getInstance().getBoard().getI2cBus(BBBNames.I2C_1), 72);

        DigitalInput sensor1A = BoardUtil.getInstance().getBoard().getPin(BBBNames.P9_12).as(DigitalInput.class);
        DigitalInput sensor2A = BoardUtil.getInstance().getBoard().getPin(BBBNames.P9_30).as(DigitalInput.class);


        try {
            tmp101First.init();
            tmp101Second.init();

            //ledMatrix.init();
            //ledMatrix.setLED(1,1, true, true);

            // Setup terminal and screen layers
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();


            // Setup WindowBasedTextGUI for dialogs
            WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
            BasicWindow window = new BasicWindow();


            Panel panel = new Panel();
            panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

            Label details = new Label("Sensor will alarm at 25 degrees C, and will reset when below 24.").addTo(panel);
            Label sensor1 = new Label("Current Temperature from TMP101 Sensor 1: ").addTo(panel);
            Label sensor2 = new Label("Current Temperature from TMP101 Sensor 2: ").addTo(panel);

            // Create window to hold the panel
            window.setHints(Arrays.asList(Window.Hint.CENTERED));
            window.setComponent(panel);




            new Thread(() -> {
                //Update Temperature evrey so often
                while(true){
                    try {
                        Thread.sleep(400);

                        String sensor1Text = sensor1A.read().getBooleanValue() ? "ALERT HIGH" : "Alert Low";
                        String sensor2Text = sensor2A.read().getBooleanValue() ? "ALERT HIGH" : "Alert Low";

                        sensor1.setText("Alert from TMP101 Sensor 1: " + sensor1Text);
                        sensor2.setText("Alert from TMP101 Sensor 2: " + sensor2Text);

                        panel.invalidate();

                        // System.out.println("TEst");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            textGUI.addWindowAndWait(window);

            //System.out.println("Current Temperature from TMP101 Sensor 1: " + tmp101First.readTemp() + " °C");
            //System.out.println("Current Temperature from TMP101 Sensor 2: " + tmp101Second.readTemp() + " °C");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
