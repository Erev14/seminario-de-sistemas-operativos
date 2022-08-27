package com.cucei;

import java.util.LinkedList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController {
    private static final LinkedList<Process> process_queue = new LinkedList<Process>();

    private boolean working;
    @FXML
    private TextField newProcessTextInput;

    @FXML
    private ListView<String> processListView = new ListView<String>();

    @FXML
    private VBox processProcessingVBox;
    @FXML
    private void addProcess() {
        // clear view list and process bars
        clear();
        
        Process process = generateFakeProcess();
        createNewProcessToProcess(process.getName(), process.getId());        
        
        // add porcess to the list view
        processListView.getItems().add(process.getName());
        // add porcess to the controller queue (the algoritms will work with this)
        process_queue.add(process);
    }
    
    private Process generateFakeProcess() {
        // generate data fake
        int max_time = 10;
        int min_time = 1;
        int id = process_queue.size() + 1;
        String name = newProcessTextInput.getText();
        int time = (int) (Math.random() * (max_time - min_time + 1) + min_time);
        return new Process(id, name, time);
    }

    private void createNewProcessToProcess(String process_name, int process_id) {
        Label process_name_label = new Label(process_name);
        
        ProgressBar process_progressbar = new ProgressBar(0.0); // value is form 0 to 1
        process_progressbar.setPrefWidth(350.0);
        process_progressbar.setId("progressbar" + String.valueOf(process_id));
        
        HBox hbox = new HBox(30, process_name_label, process_progressbar);
        hbox.setId("hbox" + String.valueOf(process_id));
        
        processProcessingVBox.getChildren().add(hbox);
    }
    
    @FXML
    private void startBatch() throws InterruptedException{
        while(process_queue.size() > 0){
            if(working) continue;
            System.out.println("New process running");
            // delete first element from the queue to be processed
            Process to_process = process_queue.remove();
            
            // get progress bar reference
            ProgressBar process_progressbar = getProgressBar(String.valueOf(to_process.getId()));

            // create task (needed to use progress property to update the progress bar)
            Task task = taskCreator(to_process.getProcess_time());
            process_progressbar.progressProperty().unbind();
            process_progressbar.progressProperty().bind(task.progressProperty());

            // create the thread, and start the task 
            // (if not will enter in a undertemined progress property, negative o not valid value)
            // Thread dummy_task = new Thread(task);
            // dummy_task.start();
            working = true;
            System.out.println("working...");
            new Thread(task).start();
        }
    }

    private void startMultiProcess() {
        while(process_queue.size() > 0){
            // delete first element from the queue to be processed
            Process to_process = process_queue.remove();
            
            // get progress bar reference
            ProgressBar process_progressbar = getProgressBar(String.valueOf(to_process.getId()));

            // create task (needed to use progress property to update the progress bar)
            Task task = taskCreator(to_process.getProcess_time());
            process_progressbar.progressProperty().unbind();
            process_progressbar.progressProperty().bind(task.progressProperty());
            // create the thread, and start the task 
            // (if not will enter in a undertemined progress property, negative o not valid value)
            // Thread dummy_task = new Thread(task);
            // dummy_task.start();
            new Thread(task).start();
        }
    }

    private ProgressBar getProgressBar(String index) {
        return (ProgressBar) processProcessingVBox.lookup("#progressbar"+index);
    }

    //Create a New Task
    private Task taskCreator(int seconds){
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for(int i=0; i<seconds;i++){
                    System.out.println("updating...");
                    Thread.sleep(1000);
                    updateProgress(i+1, seconds);
                }
                System.out.println("finish update");
                working = false;
                System.out.println("No more working");
                return true;
            }
        };
    }

    private void clear() {
        if(process_queue.size() == 0){
            processListView.getItems().clear();
            processProcessingVBox.getChildren().clear();
        }
    }
}
