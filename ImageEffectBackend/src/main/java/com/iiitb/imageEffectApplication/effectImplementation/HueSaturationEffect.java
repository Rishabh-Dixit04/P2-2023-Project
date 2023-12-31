package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.baseEffects.ParameterizableEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.HueSaturationInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;


class Thread4 extends Thread{
    private LoggingService ls;
    private float hue;
    private float sat;
    private String fileName;
    public Thread4( LoggingService l, float h, float s, String fn ){
        ls = l;
        sat = s;
        hue = h;
        fileName = fn;
    }
    public void run(){
        String optionValue = "Hue: " + hue + ", Saturation: " + sat;
        ls.addLog(fileName, "Hue-Saturation", optionValue );
    }
}
public class HueSaturationEffect implements ParameterizableEffect  {

    public static float hue, saturation = 0;
    public void setParameter(String paramName, float value) throws IllegalParameterException{

        if ((paramName.equals("hue") && (value>360 || value<0)) || (paramName.equals( "saturation") && (value>100 || value<0))){
            throw new IllegalParameterException("Value out-of-bounds");
        }

        else{
            if (paramName.equals("hue"))   hue = value;
            if (paramName.equals("saturation"))    saturation = value;
        }

    }

    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        Thread t1 = new Thread4(loggingService, hue, saturation, fileName);
        t1.start();
        return  HueSaturationInterface.applyHueSaturation(image, saturation, hue);
    }
}