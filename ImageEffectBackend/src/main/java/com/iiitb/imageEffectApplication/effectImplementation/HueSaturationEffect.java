package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.baseEffects.ParameterizableEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.HueSaturationInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;


public class HueSaturationEffect implements ParameterizableEffect  {

    public static float hue, saturation = 0;
    public void setParameter(String paramName, float value) throws IllegalParameterException{
           
            if ((paramName.equals("hue") && (value>360 || value<0)) || (paramName.equals("saturation") && (value>100 || value<0))){
                IllegalParameterException exception = new IllegalParameterException("Value out-of-bounds");
                throw exception;
            }
                
            else{
                if (paramName.equals("hue"))     hue = value;
                if (paramName.equals("saturation"))      saturation = value;
            }

    }

    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        
        Pixel[][] imageVector = HueSaturationInterface.applyHueSaturation(image, saturation, hue);       
        String optionValue = "Hue: " + Float.toString(hue) + " Saturation: " + Float.toString(saturation);
        loggingService.addLog(fileName, "Hue-Saturation", optionValue );  
        return imageVector;

    }
}