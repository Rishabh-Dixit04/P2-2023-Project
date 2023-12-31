package main.java.com.iiitb.imageEffectApplication.effectImplementation;
import com.iiitb.imageEffectApplication.baseEffects.PhotoEffect;
import com.iiitb.imageEffectApplication.libraryInterfaces.Pixel;
import com.iiitb.imageEffectApplication.libraryInterfaces.GrayscaleInterface;
import com.iiitb.imageEffectApplication.service.LoggingService;

class Thread3 extends Thread{
    private LoggingService ls;

    private String fileName;
    public Thread3(LoggingService l, String fn){
        ls = l;
        fileName = fn;
    }
    public void run(){
        ls.addLog(fileName, "Grayscale", "");
    }
}
public class GrayscaleEffect implements PhotoEffect {
    public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService){
        Thread t1 = new Thread3(loggingService, fileName);
        t1.start();
        return GrayscaleInterface.applyGrayscale(image);

    }
}