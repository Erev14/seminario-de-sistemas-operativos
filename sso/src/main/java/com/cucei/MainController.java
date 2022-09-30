package com.cucei;

import java.util.LinkedList;
import java.util.Random;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class MainController {
    private static final LinkedList<Process> process_queue = new LinkedList<>();

    private int processCount;

    private int processEnded;

    private Object lock = new Object();
    
    Random r = new Random();

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
    private void addProcess() {
        // clear view list and process bars
        clear();
        endProcessing();

        Process process = generateFakeProcess();
        createNewProcessToProcess(process.getName(), process.getId(), process.getAcutalState());        
        
        // add porcess to the list view
        processListView.getItems().add(process.getName());
        // add porcess to the controller queue (the algoritms will work with this)
        process_queue.add(process);
        newProcessTextInput.clear();
        newProcessTextInput.requestFocus();
    }
    
    @FXML
    private void startBatch(){
        // start new tread to avoid block main javafx thread
        // to update the progress bar
        new Thread("Batch"){
            @Override
            public void run() {
                startProcessing();
                while(!process_queue.isEmpty()){
                    // delete first element from the queue to be processed
                    Process toProcess = process_queue.remove();
                    // get progress bar reference
                    ProgressBar processProgressbar = getProgressBar(String.valueOf(toProcess.getId()));

                    for (double i = 0.0; i < toProcess.getProcessTime(); i++){
                        final double step = i + 1;
                        Platform.runLater(() -> processProgressbar.setProgress( (double) step / toProcess.getProcessTime() ));                        
                        try {
                            Thread.sleep(1000); 
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                endProcessing();
            }
        }.start();
    }

    @FXML
    private void startMultiProcess() {
        startProcessing();
        while(!process_queue.isEmpty()){
            // delete first element from the queue to be processed
            Process toProcess = process_queue.remove();
            
            // get progress bar reference
            ProgressBar processProgressbar = getProgressBar(String.valueOf(toProcess.getId()));

            // create task (needed to use progress property to update the progress bar)
            Task<Void> task = taskCreator(toProcess.getProcessTime());
            processProgressbar.progressProperty().unbind();
            processProgressbar.progressProperty().bind(task.progressProperty());
            // create the thread, and start the task 
            // (if not will enter in a undertemined progress property, negative o not valid value)
            new Thread(task, "Task " + toProcess.getName() + "for " + toProcess.getProcessTime()).start();
        }
    }

    @FXML
    private void processWithStates(){
        // for processProcessingVBox.getChildren() -> get hbox.getchildren.add("acutal State", "pausebuton", "terminatebuton")
        // set onhandle pause -> change text to resume (set to undertermined progress property the progress bar)
        // set onhandle terminatebutton to set progress bar as complete
        // move process states
        // ... check how to use wait/notify with javafx
    }

    @FXML
    private void clearUI(){
        clearAll();
    }

    //Create a New Task
    private Task<Void> taskCreator(int seconds){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for(int i=0; i<seconds;i++){
                    Thread.sleep(1000);
                    updateProgress(i+1, seconds);
                }
                synchronized(lock){
                    processEnded++;
                    if(processEnded == processCount){
                        endProcessing();
                    }
                }
                return null;
            }
        };
    }

    private Process generateFakeProcess() {
        // generate data fake
        int maxTime = 10;
        int minTime = 1;
        int id = process_queue.size() + 1;
        String name = newProcessTextInput.getText();
        int time = minTime + r.nextInt(maxTime);
        return new Process(id, name, time);
    }

    private void createNewProcessToProcess(String processName, int processId, String processState) {
        Label processNameLabel = new Label(processName);
        
        ProgressBar processProgressbar = new ProgressBar(0.0); // value is form 0 to 1
        processProgressbar.setPrefWidth(350.0);
        processProgressbar.setId("progressbar" + processId);
        processProgressbar.setPrefWidth(280);
        
        Label processStateLabel = new Label(processState);
        processStateLabel.setId("statelabel" + processId);
        

        // Button processPauseButton = new Button("Pausar");
        // processPauseButton.setTextAlignment(TextAlignment.CENTER);
        // processPauseButton.setDisable(true);
        // Button processResumeButton = new Button("Reanudar");
        // processResumeButton.setTextAlignment(TextAlignment.CENTER);
        // processResumeButton.setDisable(true);
        // processPauseButton.setOnAction((arg0) -> );
        
        // HBox hbox = new HBox(30, processNameLabel, processProgressbar, processStateLabel, processPauseButton, processResumeButton);
        HBox hbox = new HBox(30, processNameLabel, processProgressbar, processStateLabel);
        hbox.setId("hbox" + processId);
        
        processProcessingVBox.getChildren().add(hbox);
    }

    private ProgressBar getProgressBar(String index) {
        return (ProgressBar) processProcessingVBox.lookup("#progressbar"+index);
    }

    private void clear() {
        if(process_queue.isEmpty()){
            clearAll();
        }
    }

    private void clearAll(){
        processListView.getItems().clear();
        processProcessingVBox.getChildren().clear();
        processCount = 0;
    }

    private void startProcessing(){
        processCount = process_queue.size();
        addProcessButton.setDisable(true);
        batchButton.setDisable(true);
        multiprogramingButton.setDisable(true);
        processWithStatesButton.setDisable(true);
        clearButton.setDisable(true);
    }

    private void endProcessing(){
        processCount = 0;
        addProcessButton.setDisable(false);
        batchButton.setDisable(false);
        multiprogramingButton.setDisable(false);
        processWithStatesButton.setDisable(false);
        clearButton.setDisable(false);
    }
}
