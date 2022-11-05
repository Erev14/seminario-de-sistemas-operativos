package com.cucei.views;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.cucei.models.Process;

public class MonitorView {
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
    
    public MonitorView(){

    }

    public String getNewProcessName(){
        return newProcessTextInput.getText();
    }

    public void addProcess(Process process){
        Label processNameLabel = new Label(process.getName());
        
        ProgressBar processProgressbar = new ProgressBar(0.0); // value is form 0 to 1
        processProgressbar.setPrefWidth(350.0);
        processProgressbar.setId("progressbar" + process.getId());
        processProgressbar.setPrefWidth(280);
        
        Label processStateLabel = new Label(process.getAcutalState());
        processStateLabel.setId("statelabel" + process.getId());

        Button pauseToogleButton = new Button("Pausar");
        pauseToogleButton.setId("Button" +  String.valueOf(process.getId()));
        Button endPrcoessButton = new Button("Terminar");
        pauseToogleButton.setOnAction((event) -> {
            Platform.runLater(() -> {
                if(process.isPaused()){
                    // process.pause();
                    pauseToogleButton.setText("Pausar");
                }else{
                    pauseToogleButton.setText("Continuar");
                }
                process.tooglePaused();
            });
        });
        
        HBox hbox = new HBox(30, processNameLabel, processProgressbar, pauseToogleButton, endPrcoessButton);
        hbox.setId("hbox" + String.valueOf(process.getId()));
        
        processProcessingVBox.getChildren().add(hbox);
    }

    public void disableElements(){
        setDisableElements(true);
    }
    
    public void enableElements(){
        setDisableElements(false);
    }

    private void setDisableElements(boolean isDisable){
        addProcessButton.setDisable(isDisable);
        batchButton.setDisable(isDisable);
        multiprogramingButton.setDisable(isDisable);
        processWithStatesButton.setDisable(isDisable);
        clearButton.setDisable(isDisable);
    }


    public void clear(){
        processListView.getItems().clear();
        processProcessingVBox.getChildren().clear();
    }
}