Homework 05

This homework contains multiple folders.

In the "linux" folder, there is a completed Makefile for hw05 that builds app.c.  Just run make from the command line.

The kernel source was installed on a Google Cloud machine, and I build kernel version 4.8 and installed it to the Bone.  It booted.

The folder "crosscompile" contains command line output from cross compiling helloWorld.c on a Google Cloud machine and then running it on the Bone.

"hw05" contains a Java project that uses Firebase to push temperature data to the Firebase Cloud every 5 seconds. Simply run "maven compiler:compile package" to produce a JAR file and run it with "java -jar file.jar" to run, and the program will continously log temperature data.  

"temperature.html" is a file I wrote that is node.js independent.  It utilizes the Javascript Firebase API and a graphing API to pull data from Firebase and plot it on a graph.  This is running on my server at "https://lukekuza.me/temperature.html"