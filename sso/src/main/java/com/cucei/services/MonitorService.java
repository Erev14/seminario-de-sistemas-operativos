package com.cucei.services;

import java.util.LinkedList;
import com.cucei.models.Process;

public class MonitorService {
    private static final LinkedList<Process> processQueue = new LinkedList<>();

    private int processCount = 0;

    private int processEnded = 0;

    public MonitorService(){

    }

    public Process addProcess(String processName){
        Process process = new Process(processQueue.size(), processName);
        processQueue.add(process);
        return process;
    }

    public boolean queueEmpty() {
        return processQueue.isEmpty();
    }

    public void resetProcessCount(){
        processCount = 0;
    }
}