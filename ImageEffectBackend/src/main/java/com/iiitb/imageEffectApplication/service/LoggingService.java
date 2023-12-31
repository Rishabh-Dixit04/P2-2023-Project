package com.iiitb.imageEffectApplication.service;
import com.iiitb.imageEffectApplication.model.LogModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 
import java.io.*;  

@Service
public class LoggingService {
    private static List<LogModel> logs = new ArrayList<LogModel>();
    public LoggingService(){
        try{
            File obj = new File("logs.txt");
            if (!obj.exists()){
                obj.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        try{
        FileInputStream fis=new FileInputStream("logs.txt");  
        Scanner sc=new Scanner(fis);
        while(sc.hasNextLine()){
                  String str = sc.nextLine();
                  List<String> models_list = Arrays.asList(str.split(","));
                 
                  if (models_list.size()>3){
                    LogModel new_LogModel = new LogModel(models_list.get(0), models_list.get(1), models_list.get(2),models_list.get(3));
                    logs.add(new_LogModel);
                  }
                  else{
                    LogModel new_LogModel = new LogModel(models_list.get(0), models_list.get(1), models_list.get(2),"");
                    logs.add(new_LogModel);
                  }
                  
            }  
            sc.close();     //closes the scanner  
            }  
            catch(IOException e)  
            {  
            e.printStackTrace();  
            }  
        }

    
    

    

    
    
    
    public void addLog(String fileName, String effectName, String optionValues) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String localDateTime =  LocalDateTime.now().format(formatter);
        LogModel new_LogModel = new LogModel(localDateTime, fileName, effectName, optionValues);
        logs.add(new_LogModel);
        try (FileWriter myWriter = new FileWriter("logs.txt", true)) {
            String inputString = localDateTime + "," + fileName + "," + effectName + "," + optionValues + '\n';
            myWriter.write(inputString);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();  // Handle or log the exception as needed
        }
        return;
    }

    public List<LogModel> getAllLogs() {
        List<LogModel> temp_logs = new ArrayList<LogModel>();
        temp_logs.addAll(logs);
        Collections.reverse(temp_logs);
        return temp_logs;
        
    }

    public List<LogModel> getLogsByEffect(String effectName) {
        List<LogModel> temp_logs = new ArrayList<LogModel>();
        for (LogModel x : getAllLogs()){
           
            if (x.getEffectName().equals(effectName)){
                temp_logs.add(x);
            }
        }
        return temp_logs;
    }

    public void clearLogs() {
        logs.clear();
        try{
            new FileWriter("logs.txt", false).close();
        }
        catch(IOException e) {
            e.printStackTrace();  // Handle or log the exception as needed
        }
        return;
    }

    public List<LogModel> getLogsBetweenTimestamps(LocalDateTime startTime, LocalDateTime endTime) {
        List<LogModel> temp_logs = new ArrayList<LogModel>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
       
        for (LogModel x : getAllLogs()){
            LocalDateTime current = LocalDateTime.parse(x.getTimestamp(), formatter);
            if ((current.isBefore(endTime) || current.isEqual(endTime)) && (current.isAfter(startTime) || current.isEqual(startTime))){
                temp_logs.add(x);
            }
        }
        return temp_logs;
    }
}
