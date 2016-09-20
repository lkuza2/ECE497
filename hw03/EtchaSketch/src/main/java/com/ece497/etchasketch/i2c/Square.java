package com.ece497.etchasketch.i2c;

/**
 * Created by kuzalj on 9/19/2016.
 */
public class Square {

    private int x;
    private int y;

    private boolean green;
    private boolean red;

    public Square(int x, int y){
        this.x = x;
        this.y = y;

        this.green = false;
        this.red = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isGreen() {
        return green;
    }

    public void setGreen(boolean green) {
        this.green = green;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }
}
