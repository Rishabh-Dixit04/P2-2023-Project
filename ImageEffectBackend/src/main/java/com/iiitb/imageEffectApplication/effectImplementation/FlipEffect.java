package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.baseEffects.DiscreteEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.FlipInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;


class Thread9 extends Thread{
    private LoggingService ls;          //Extending the Thread class

    private String fileName;
    public Thread9(LoggingService l, String fn){
        ls = l;
        fileName = fn;
    }
    public void run(){
        ls.addLog(fileName, "Flip", "");
    }
}

public class FlipEffect implements DiscreteEffect{
    private int hflip = 0, vflip = 0;

    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        Thread t1 = new Thread9(loggingService, fileName);
        t1.start();         //Starting the thread to perform logging and apply simultaneously
        return FlipInterface.applyFlip(image, hflip, vflip);
    }

    public void selectOptionValue(String optionName, int value) throws IllegalParameterException{      
        if(optionName.equals("Horizontal")) hflip = value;
        else if(optionName.equals("Vertical")) vflip = value;
        else{
            throw new IllegalParameterException();      //Ensuring robustness to keep values in expected range 
        }
    }
}


