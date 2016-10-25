iHomework 07

This homework contains multiple folders and files.

In the "ScopeTestImages" folder, there are properly named scope images for each of the four tests required for this homework.  

The "ebb" folder contains the edited PRU template code to copy an input signal to another pin.  Simply run "source setup.sh", "make", and then "make install" to run on the PRU.

The "hw07" folder is the high level test written in Java to simply copy a signal input to an output pin.  Simply run "mvn compiler:compile package" to compile the project to a runnable JAR file (need JDK 8 at least) and run "java -jar output.jar" to run the JAR.

The "mmap" folder is the mmap C test code based on hw04.  It copies the input signal using memory mapped IO.

The file "hw07-Report-kuzalj.pdf" is VERY IMPORTANT and contains the entire analysis, comments, and conclusions for this homework.  It contains a neat table of the data acquired.  Please read that file for more information.  It does not contain the scope images, as they were left out to save Github space.  They can be found in "ScopeTestImages" as previously described.  
