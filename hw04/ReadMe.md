Homework 04

In the mmap folder is the modified C code to light up LEDs based on two buttons.  This lights up to LEDs connected with pullup resistors and does not use the USR LEDs.

Before writing any code, write a paragraph describing how boneServer.js and the browser interact in the given example. For example, in matrixLED.js connect() is called to make a connection between the browser and the bone.  The message “matrix” is sent to the bone. What happens in response to the message? 

The boneserver.js starts the node server and socket.io and creates the web interface and then handles network connections, and then performs bonescript functions from user actions read from socket.io


What happens when an “LED” is clicked on in the browser?
The node.js server gets a callback from the click, containing the element clicked, and uses this element to lookup what physical LED that it represents, and sets the LED status accordingly.

What entry in matrix.css is used to color the LED?
.on


Write a high level paragraph about how you will control the two LEDs. What messages will be sent between the browser and the bone?
I will perform interrupts from callbacks and use bonescript

Write your code.  Do you need to change boneServer.js? (I don’t think so.)  Customize the html to have your name on it, etc.
No. 
