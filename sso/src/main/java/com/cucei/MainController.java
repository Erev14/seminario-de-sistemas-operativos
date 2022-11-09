package com.cucei;

import javafx.fxml.FXML;

import com.cucei.services.MonitorService;
import com.cucei.views.MonitorView;
import com.cucei.models.Process;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainController {

    private MonitorService monitorService = new MonitorService();
    private MonitorView monitorView = new MonitorView();

    @FXML
    private TextField newProcessTextInput;

    @FXML
    private ListView<String> processListView = new ListView<>();

    @FXML
    private VBox processProcessingVBox;

    @FXML
    private Button addProcessButton;

    @FXML
    private Button batchButton;
    
    @FXML
    private Button multiprogramingButton;
    
    @FXML
    private Button processWithStatesButton;
    
    @FXML
    private Button clearButton;

    @FXML
    private void initialize(){
        monitorView.setNewProcessTextInput(newProcessTextInput);
        monitorView.setProcessProcessingVBox(processProcessingVBox);
        monitorView.setAddProcessButton(addProcessButton);
        monitorView.setBatchButton(batchButton);
        monitorView.setMultiprogramingButton(multiprogramingButton);
        monitorView.setProcessWithStatesButton(processWithStatesButton);
        monitorView.setClearButton(clearButton);
    }

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
