# Programming 2 Project 

## Description
- The Libraries folder contains the main logic behind each of the effects. The code is distributed across 2 cpp files and 1 header file. The Effect.cpp file consists of the cpp code that iterates over the 2-D Pixel vector and makes the corresponding changes, whereas the EffectInterface.cpp file converts the passed 2-D Java array to a 2-D cpp vector, calls the fuction declared in the header file and then converts the result back to a Java array.
- The effectImplementation package comprises of the Java code for each effect. Here we implement the baseEffect interface that is required for that particular effect, and define the functionality of the "apply()" & "setParameter()" methods. The logging and threading features have also been implementated in this directory.
- The service folder has 2 Java files - LoggingService.java, which contains the methods to handle the logs and PhotoeEffectService.java, which consists of methods to call the corresponding apply() and setParameter() methods defined in effectImplementation package.
- Logs: The logs are maintained with the help of a static array, defined in LoggingService.java & a file named "logs.txt". The static array ensures that we don't have to read the file each time the "filter" or "load between timestamps" options are used. Any change in the logs gets simulataneously updated in both. So whenever the application is loaded the previous logs are maintained in the txt file.
- MultiThreading - To ensure that the logging and image effect application are done parallely, an additional thread has been created to run the logging service.
- Exception Handling - Each effect implementation file checks if the parameter is within the correct range. If not, an IllegalParameterException is thrown. This exception is caught in the PhotoEffectService.java file which then diplays the stack trace of the exception.


## Installations
- Ensure that the code setup is done. Run the commands given in the setup which can be summarized as:

- Go to ImageEffectBackend and run to following commands
- make clean
- make
- mvnw clean package
- java -jar target/imageEffectApplication-0.0.1-SNAPSHOT.jar

- Go to ImageEffectFront end and run
- npm start

## Contributions
- Trupti Khodwe IMT2022007 - Flip Effect and Gaussian Blur
- Nikita Kiran IMT2022028 - Brightness Effect, Contrast Effect and Multithreading code
- Shiven Phogat IMT2022050 - Sepia Effect and Sharpen Effect
- Rishabh Dixit IMT2022051 - Grayscale Effect, Hue Saturation Effect and Logging Services
- Sasi Snigdha Yadavalli IMT2022571 - Invert Effect and Rotate Effect