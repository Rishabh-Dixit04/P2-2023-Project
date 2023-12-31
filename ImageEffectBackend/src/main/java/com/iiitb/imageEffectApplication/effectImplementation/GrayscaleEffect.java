package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.GrayscaleInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;

public class GrayscaleEffect implements PhotoEffect {
    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){     
        

        Pixel[][] imageVector = GrayscaleInterface.applyGrayscale(image);  
        
        loggingService.addLog(fileName, "Grayscale", "" );
        
        return imageVector;
    }
}