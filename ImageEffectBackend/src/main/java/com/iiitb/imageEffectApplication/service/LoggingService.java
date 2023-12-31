package com.iiitb.imageEffectApplication.service;
import com.iiitb.imageEffectApplication.model.LogModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@Service
public class LoggingService {

    private static List<LogModel> logs = new ArrayList<LogModel>();

    public void addLog(String fileName, String effectName, String optionValues) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String localDateTime =  LocalDateTime.now().format(formatter);
        LogModel new_LogModel = new LogModel(localDateTime, fileName, effectName, optionValues);
        logs.add(new_LogModel);
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
