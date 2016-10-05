package com.ece497.hw05;

import com.ece497.hw05.util.BoardUtil;
import com.ece497.hw05.util.MainUtil;
import io.silverspoon.bulldog.beagleboneblack.BBBNames;

import java.io.IOException;

/**
 * Created by kuzalj on 9/15/2016.
 */
public class Main {

    public static void main(String args[]){
        //BoardUtil.getInstance().attachInterruptToPin(BBBNames.P9_12);
        try {
            BoardUtil.getInstance().getBoard().getI2cBus(BBBNames.I2C_1).writeByte(0x00);
            new MainUtil().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
