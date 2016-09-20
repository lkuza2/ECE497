package com.ece497.hw03.i2c;

import io.silverspoon.bulldog.core.io.bus.i2c.I2cBus;
import io.silverspoon.bulldog.core.io.bus.i2c.I2cDevice;

import java.io.IOException;

/**
 * Object representing the LED Matrix 8x8
 */
public class LEDMatrixv2 extends I2cDevice{

    private Square[][] board = new Square[8][8];


    /**
     * Setup 8x8 LED Matrix
     * @param bus The I2CBus to read from
     * @param address The address in DEC
     */
    public LEDMatrixv2(I2cBus bus, int address) {
        super(bus, address);
    }

    /**
     * Init board all blank, max brightness
     */
    public void init(){
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
            }
        }

        try {
            writeByteToRegister(0x21, 0x00);
            writeByteToRegister(0x81, 0x00);
            writeByteToRegister(0xe7, 0x00);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Set a specific LED
     * @param x The x cord
     * @param y The y cord
     * @param green True if green, false for no green
     * @param red True of red, false off
     */
    public void setLED(int x, int y, boolean green, boolean red){
        this.board[x][y].setX(x);
        this.board[x][y].setY(y);
        this.board[x][y].setGreen(green);
        this.board[x][y].setRed(red);

        pushBoard();
    }

    /**
     * Pushes the whole board to the LED Matrix
     */
    private void pushBoard(){
        try {
            int register = 0x00;
            for (int i = 0; i < 8; i++) {
                int greenByte = 0x00;
                int redByte = 0x00;
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].isGreen())
                        greenByte = (greenByte | (1 << j));
                    if (board[i][j].isRed())
                        redByte = (redByte | (1 << j));
                }
                writeByteToRegister(register, greenByte);
                register += 0x01;
                writeByteToRegister(register, redByte);
                register += 0x01;
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


}
