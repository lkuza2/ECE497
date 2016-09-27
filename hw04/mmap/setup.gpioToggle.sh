#!/bin/bash
# Set up gpio 7 to read and gpio 3 to write
cd /sys/class/gpio
echo 20 > export
#echo 7 > export
#echo in  > gpio7/direction
echo out > gpio20/direction
