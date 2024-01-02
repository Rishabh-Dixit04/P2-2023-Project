package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.baseEffects.SingleValueParameterizableEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.GaussianBlurInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;


class Thread10 extends Thread{          //Extending the Thread class
    private LoggingService ls;
    private float amount;
    private String fileName;
    public Thread10(){}
    public Thread10( LoggingService l, float amt, String fn ){
        ls = l;
        amount = amt;
        fileName = fn;
    }
    public void run(){
        ls.addLog(fileName, "Gaussian Blur", "Blur: " + amount );
    }
}

public class GaussianBlurEffect implements SingleValueParameterizableEffect{
    private float amount = 0;
    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        Thread t1 = new Thread10(loggingService, amount, fileName);
        t1.start();         //Starting the thread to perform logging and apply simultaneously
        return GaussianBlurInterface.applyGaussianBlur(image, amount);
    }
    public void setParameterValue(float parameterValue) throws IllegalParameterException{       //Ensuring robustness to keep values in expected range 
        if (parameterValue > 200 || parameterValue < 0){
            throw new IllegalParameterException("Value out-of-bounds");
        }
        else{
            amount = parameterValue;
        }
    }
    
}