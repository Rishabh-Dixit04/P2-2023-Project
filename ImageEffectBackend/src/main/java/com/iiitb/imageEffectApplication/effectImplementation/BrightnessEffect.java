package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.baseEffects.SingleValueParameterizableEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.BrightnessInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;


class Thread1 extends Thread{
    private LoggingService ls;
    private float amount;
    private String fileName;
    public Thread1(){}
    public Thread1( LoggingService l,float amt, String fn ){
        ls = l;
        amount = amt;
        fileName = fn;
    }

    public void run(){
        ls.addLog(fileName, "Brightness", "Brightness: " + amount );
    }
}
public class BrightnessEffect implements SingleValueParameterizableEffect {
    private float amount = 0;
    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        //System.out.println("hi");
        Thread t1 = new Thread1(loggingService, amount, fileName);
        t1.start();
        return BrightnessInterface.applyBrightness(image, amount);

    }
    public void setParameterValue(float parameterValue) throws IllegalParameterException{
        if (parameterValue > 200 || parameterValue < 0){
            throw new IllegalParameterException("Value out-of-bounds");
        }
        else{
            amount = parameterValue;
        }
    }
}