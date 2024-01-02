package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.InvertInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;

class Thread7 extends Thread{           //Extending the Thread class
    private LoggingService ls;

    private String fileName;
    public Thread7(LoggingService l, String fn){
        ls = l;
        fileName = fn;
    }
    public void run(){
        ls.addLog(fileName, "Invert", "");
    }
}
public class InvertEffect implements PhotoEffect {
    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        Thread t1 = new Thread7(loggingService, fileName);
        t1.start();             //Starting the thread to perform logging and apply simultaneously
        return InvertInterface.applyInvert(image);

    }
}