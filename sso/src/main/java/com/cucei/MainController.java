package com.cucei;

import javafx.fxml.FXML;

import com.cucei.services.MonitorService;
import com.cucei.views.MonitorView;
import com.cucei.models.Process;

public class MainController {

    private MonitorService monitorService = new MonitorService();
    private MonitorView monitorView = new MonitorView();

    @FXML
    private void addProcess() {
        if (monitorService.queueEmpty()){
            monitorView.clear();
        }
        monitorService.resetProcessCount();

        monitorView.disableElements();
        String processName = monitorView.getNewProcessName();
        Process process = monitorService.addProcess(processName);
        monitorView.addProcess(process);
    }
    
    @FXML
    private void startBatch(){
        
    }

    @FXML
    private void startMultiProcess() {
        
    }

    @FXML
    private void processWithStates(){
        
    }

    @FXML
    private void clearUI(){
        
    }
}
