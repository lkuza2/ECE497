This project was created in Java using a modified version of the Bulldog library by Luke Kuza to work on newer BeagleBone kernels.

The original library (with pull requests) is found here, https://github.com/SilverThings/bulldog

My version is found here: https://github.com/lkuza2/bulldog


The hw03 folder contains a project that uses two TMP101 sensors using I2C.  It uses the Lanterna framework for GUI handling, it is an ncurses like framework.  The hw03 projects sets up the sensors with the highest precision, and gives a 24 degree C low and 25 degree high limit for the sensors.  When the ALERT pin is triggered, the sensor it was triggered on will display on the GUI

This project was created in Java 8 using Maven to manage build dependencies. For terminal graphics, the project uses an ncurses like Java framework called Lanterna, found here: https://github.com/mabe02/lanterna/

For GPIO control on the BBB, the project uses a Java library called BullDog: https://github.com/SilverThings/bulldog (my version: https://github.com/lkuza2/bulldog)

The Etch-A-Sketch program was created in Intelli J Idea, and can be opened in the studio and a .jar file built. Once the .jar file is built, it can be ran using "java -jar file.jar". Note that the Bone must have Java installed from apt-get.  It also uses the Lanterna framework, and the modified Bulldog framework.  It runs the Etch-A-Sketch on an 8x8 LED and supports screen wrapping.

TO BUILD:
in either folder, run "mvn package" and in the "target" folder will contains the jars with dependencies

==========
Comments from Prof. Mark A. Yoder
Lots of nice extras, Java, Arch, etc.

Grade:  10/10