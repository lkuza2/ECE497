package com.ece497.hw05.util;

import com.ece497.hw05.i2c.TMP101v2;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.firebase.tasks.Tasks;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.sun.corba.se.spi.activation.Server;
import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.gpio.DigitalInput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by kuzalj on 9/19/2016.
 */
public class MainUtil {

    public boolean running = true;

    public void init(){
        //TMP101 tmp101First = new TMP101((byte) 0b1001001, (byte) 2);
        //TMP101 tmp101Second = new TMP101((byte) 0b1001000, (byte) 2);

        TMP101v2 tmp101First = new TMP101v2(BoardUtil.getInstance().getBoard().getI2cBus(BBBNames.I2C_1), 73);
        TMP101v2 tmp101Second = new TMP101v2(BoardUtil.getInstance().getBoard().getI2cBus(BBBNames.I2C_1), 72);

        tmp101First.init();
        tmp101Second.init();

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setServiceAccount(new FileInputStream("/home/alarm/auth.json"))
                    .setDatabaseUrl("https://beaglebone-black.firebaseio.com/")
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FirebaseApp.initializeApp(options);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //database.setLogLevel(Logger.Level.DEBUG);
        DatabaseReference ref = database.getReference("temperature");
        DatabaseReference usersRef = ref.child("data");

        Map<Map<String,String>, TemperatureReading > temps = new HashMap<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(running) {
                    //temps.put(ServerValue.TIMESTAMP, new TemperatureReading(6.6));
                    try {
                        Tasks.await(usersRef.push().setValue(new TemperatureReading(tmp101First.readTemp().getTemp())));
                        Thread.sleep(5000);

                    } catch (IOException | InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        System.out.println("Tried to write data");

    }

    public static class TemperatureReading {

        public Map<String, String> timestamp = ServerValue.TIMESTAMP;
        public double temperature;

        public TemperatureReading(double temperature){
            this.temperature = temperature;
        }

    }
}
