package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.baseEffects.SingleValueDiscreteEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.RotationInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;



class Thread8 extends Thread{           //Extending the Thread class
    private LoggingService ls;
    private int val;
    private String fileName;
    public Thread8(LoggingService l, int param , String fn ){
        ls = l;
        val = param;
        fileName = fn;
    }
    public void run(){
        ls.addLog(fileName, "Rotation", "Rotation: " + val );
    }
}


public class RotationEffect implements SingleValueDiscreteEffect {
    private int degrees;
    public Pixel[][] apply(Pixel[][] image, String FileName, LoggingService loggingService){
        Thread t1 = new Thread8(loggingService, degrees, FileName);
        t1.start();         //Starting the thread to perform logging and apply simultaneously
        return RotationInterface.applyRotation(image, degrees);
    }
    public void setParameterValue(int value) throws IllegalParameterException{
        if (value == 0 || value == 1 || value == 2 || value == 3){
            degrees = value * 90;
        }
        else{
            throw new IllegalParameterException("Value out-of-bounds");     //Ensuring robustness to keep values in expected range 
        }

    }
}
