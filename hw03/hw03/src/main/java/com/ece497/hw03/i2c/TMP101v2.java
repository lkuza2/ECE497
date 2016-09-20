package com.ece497.hw03.i2c;

import io.silverspoon.bulldog.core.io.bus.i2c.I2cBus;
import io.silverspoon.bulldog.core.io.bus.i2c.I2cDevice;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Improved TMP101 device object that uses improved Bulldog Java I2C library
 */
public class TMP101v2 extends I2cDevice{

    /**
     * Setup TMP101 sensor
     * @param bus The I2CBus to read from
     * @param address The address in DEC
     */
    public TMP101v2(I2cBus bus, int address) {
        super(bus, address);
    }

    /**
     * Starts up the temperature controller to use Active high ALERT pin, .0625 precision, and Tlow=24, Thigh=25
     */
    public void init(){
        try {
            readByteFromRegister(0x00);

            writeByteToRegister(0b00000001, 0b01100100);

            byte thigh[] = new byte[2];
            byte tlow[] = new byte[2];
            //11000
            thigh[0] = (byte) 0b00011001;
            thigh[1] = (byte) 0b00000000;

            tlow[0] = (byte)  0b00011000;
            tlow[1] = (byte)  0b00000000;

            //writeBytesToRegister(0x02, tlow);

            //Set temp to 24
            writeByteToRegister(0x02, 0b00011000);

            //writeBytesToRegister(0b00000011, thigh);

            //Set high temp to 25
            writeByteToRegister(0x03, 0b00011001);

            readByteFromRegister(0x00);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the current temperature and returns the value in degrees celsius
     * @return Temperature object, contains temp in degrees celsius, and raw byte data
     * @throws IOException
     */
    public Temp readTemp() throws IOException {
        byte[] buffer = new byte[2];
        readBytesFromRegister(0x00, buffer);

        ByteBuffer wrapped = ByteBuffer.wrap(buffer); // big-endian by default
        wrapped.order(ByteOrder.BIG_ENDIAN);

        int num = wrapped.getShort(); // 1

        return  new Temp(num*.0625d*.0625d, String.format("%8s", Integer.toBinaryString(buffer[0] & 0xFF)).replace(' ', '0'), String.format("%8s", Integer.toBinaryString(buffer[1] & 0xFF)).replace(' ', '0'));
    }

    /**
     * Temp class for returning temperature data
     */
    public class Temp{
        public double temp;
        public String byte1;
        public String byte2;

        public Temp(double temp, String byte1, String byte2){
            this.temp = temp;
            this.byte1 = byte1;
            this.byte2 = byte2;
        }

        /**
         * Current temperature in degrees C, .0625 precision
         * @return
         */
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        /**
         * Gets first binary byte string of data
         * @return A string of 8 bits
         */
        public String getByte1() {
            return byte1;
        }

        public void setByte1(String byte1) {
            this.byte1 = byte1;
        }

        /**
         * Gets the second binary byte String of data
         * @return The binary byte string of data, 8 bits
         */
        public String getByte2() {
            return byte2;
        }

        public void setByte2(String byte2) {
            this.byte2 = byte2;
        }
    }
}
