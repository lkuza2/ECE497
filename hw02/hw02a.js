#!/usr/bin/env node

var  b = require('bonescript');
var  button1 = 'P9_11';
var  button2 = 'P9_13';
var  button3 = 'P9_42';
var  button4 = 'P9_17';
var  led1    = 'P9_21';
var  led2    = 'P9_23';
var  led3    = 'P9_27';
var  led4    = 'P9_41';



b.pinMode(button1, b.INPUT, 7, 'pulldown');
b.pinMode(button2, b.INPUT, 7, 'pulldown');
b.pinMode(button3, b.INPUT, 7, 'pulldown');
b.pinMode(button4, b.INPUT, 7, 'pulldown');

b.pinMode(led1, b.OUTPUT);
b.pinMode(led2, b.OUTPUT);
b.pinMode(led3, b.OUTPUT);
b.pinMode(led4, b.OUTPUT);

b.attachInterrupt(button1, true, b.CHANGE, changeLED);
b.attachInterrupt(button2, true, b.CHANGE, changeLED);
b.attachInterrupt(button3, true, b.CHANGE, changeLED);
b.attachInterrupt(button4, true, b.CHANGE, changeLED);

console.log('Program Start complete');

function changeLED(x){
    switch(x.pin.key){
        case button1:
            if(x.value == 1){
                b.digitalWrite(led1, 1);
            }else{
                b.digitalWrite(led1, 0);
            }
            break;
        case button2:
            if(x.value == 1){
                b.digitalWrite(led2, 1);
            }else{
                b.digitalWrite(led2, 0);
            }
            break;
        case button3:
            if(x.value == 1){
                b.digitalWrite(led3, 1);
            }else{
                b.digitalWrite(led3, 0);
            }
            break;
        case button4:
            if(x.value == 1){
                b.digitalWrite(led4, 1);
            }else{
                b.digitalWrite(led4, 0);
            }
            break;
        default:
            console.log(x.pin.key);
    }
}