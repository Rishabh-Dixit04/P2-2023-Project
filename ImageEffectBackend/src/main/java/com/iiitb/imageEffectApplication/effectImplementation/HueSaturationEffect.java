package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.service.LoggingService;


public class HueSaturationEffect implements PhotoEffect {
    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        return image;
    }
}