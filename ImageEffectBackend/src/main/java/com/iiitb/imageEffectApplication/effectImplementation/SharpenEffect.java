package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.baseEffects.SingleValueParameterizableEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.SharpenInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;


class Thread6 extends Thread{           //Extending the Thread class
    private LoggingService ls;
    private float amount;

    private String fileName;
    public Thread6(){}
    public Thread6( LoggingService l, float amt, String fn ){
        ls = l;
        amount=amt;
        fileName = fn;
    }
    public void run(){
        ls.addLog(fileName, "Sharpen", "Sharpen: " + amount );
    }
}
public class SharpenEffect implements SingleValueParameterizableEffect  {

    private float amount = 0;
    @Override
    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        //System.out.println("hi");
        Thread t1 = new Thread6(loggingService, amount, fileName);
        t1.start();             //Starting the thread to perform logging and apply simultaneously
        return SharpenInterface.applySharpen(image, amount);

    }
    public void setParameterValue(float parameterValue) throws IllegalParameterException{
        if (parameterValue > 200 || parameterValue < 0){            //Ensuring robustness to keep values in expected range 
            throw new IllegalParameterException("Value out-of-bounds");
        }
        else{
            amount = parameterValue;
        }
    }
}