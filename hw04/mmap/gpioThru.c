// From : http://stackoverflow.com/questions/13124271/driving-beaglebone-gpio-through-dev-mem
//
// Read one gpio pin and write it out to another using mmap.
// Be sure to set -O3 when compiling.
// Modified by Mark A. Yoder  26-Sept-2013
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h> 
#include <signal.h>    // Defines signal-handling functions (i.e. trap Ctrl-C)
#include "beaglebone_gpio.h"

/****************************************************************
 * Global variables
 ****************************************************************/
int keepgoing = 1;    // Set to 0 when ctrl-c is pressed

/****************************************************************
 * signal_handler
 ****************************************************************/
void signal_handler(int sig);
// Callback called when SIGINT is sent to the process (Ctrl-C)
void signal_handler(int sig)
{
    printf( "\nCtrl-C pressed, cleaning up and exiting...\n" );
	keepgoing = 0;
    exit(0);
}

int main(int argc, char *argv[]) {
    volatile void *gpio_addr;
    volatile unsigned int *gpio_oe_addr;
    volatile unsigned int *gpio_datain;
    volatile unsigned int *gpio_setdataout_addr;
    volatile unsigned int *gpio_cleardataout_addr;

    volatile void *gpio_addr3;
    volatile unsigned int *gpio_oe_addr3;
    volatile unsigned int *gpio_datain3;
    volatile unsigned int *gpio_setdataout_addr3;
    volatile unsigned int *gpio_cleardataout_addr3;

    volatile void *gpio_addr1;
    volatile unsigned int *gpio_oe_addr1;
    volatile unsigned int *gpio_datain1;
    volatile unsigned int *gpio_setdataout_addr1;
    volatile unsigned int *gpio_cleardataout_addr1;

    unsigned int reg;

    // Set the signal callback for Ctrl-C
    signal(SIGINT, signal_handler);

    int fd = open("/dev/mem", O_RDWR);

    printf("Mapping %X - %X (size: %X)\n", GPIO0_START_ADDR, GPIO0_END_ADDR, 
                                           GPIO0_SIZE);

    gpio_addr = mmap(0, GPIO0_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 
                        GPIO0_START_ADDR);

    gpio_oe_addr           = gpio_addr + GPIO_OE;
    gpio_datain            = gpio_addr + GPIO_DATAIN;
    gpio_setdataout_addr   = gpio_addr + GPIO_SETDATAOUT;
    gpio_cleardataout_addr = gpio_addr + GPIO_CLEARDATAOUT;

    gpio_addr3 = mmap(0, GPIO3_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 
                        GPIO3_START_ADDR);

    gpio_oe_addr3           = gpio_addr3 + GPIO_OE;
    gpio_datain3            = gpio_addr3 + GPIO_DATAIN;
    gpio_setdataout_addr3   = gpio_addr3 + GPIO_SETDATAOUT;
    gpio_cleardataout_addr3 = gpio_addr3 + GPIO_CLEARDATAOUT;

    gpio_addr1 = mmap(0, GPIO1_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 
                        GPIO1_START_ADDR);

    gpio_oe_addr1           = gpio_addr1 + GPIO_OE;
    gpio_datain1            = gpio_addr1 + GPIO_DATAIN;
    gpio_setdataout_addr1   = gpio_addr1 + GPIO_SETDATAOUT;
    gpio_cleardataout_addr1 = gpio_addr1 + GPIO_CLEARDATAOUT;

    system("echo 20 > /sys/class/gpio/export");
    //system("echo 65 > /sys/class/gpio/export");

    if(gpio_addr == MAP_FAILED) {
        printf("Unable to map GPIO\n");
        exit(1);
    }

    if(gpio_addr1 == MAP_FAILED) {
        printf("Unable to map GPIO\n");
        exit(1);
    }

    if(gpio_addr3 == MAP_FAILED) {
        printf("Unable to map GPIO\n");
        exit(1);
    }
    printf("GPIO mapped to %p\n", gpio_addr);
    printf("GPIO OE mapped to %p\n", gpio_oe_addr);
    printf("GPIO SETDATAOUTADDR mapped to %p\n", gpio_setdataout_addr);
    printf("GPIO CLEARDATAOUT mapped to %p\n", gpio_cleardataout_addr);

    printf("Start copying GPIO_23 to GPIO_49\n");
    while(keepgoing) {
    	if(*gpio_datain & GPIO_23) {
            *gpio_setdataout_addr3 = GPIO_117;
    	} else {
            *gpio_cleardataout_addr3 = GPIO_117;
    	}

        if(*gpio_datain1 & P9_16) {
            *gpio_setdataout_addr = P9_41;
        } else {
            *gpio_cleardataout_addr = P9_41;
        }
        //usleep(1);
    }

    munmap((void *)gpio_addr, GPIO0_SIZE);
    munmap((void *)gpio_addr3, GPIO3_SIZE);
    munmap((void *)gpio_addr1, GPIO1_SIZE);
    close(fd);
    return 0;
}
