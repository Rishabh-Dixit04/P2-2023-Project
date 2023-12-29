package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import libraryInterfaces.Pixel;

public class GrayscaleEffect implements PhotoEffect {
    Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){       
        Pixel[][] imageVector = libraryInterfaces.GrayscaleInterface.applyGrayscale(image);          //calling applyGrayscale method from libraryInterfaces package
    }
}
