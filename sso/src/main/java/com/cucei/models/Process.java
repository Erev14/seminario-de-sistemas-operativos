package com.cucei.models;

import java.util.Random;
import com.cucei.states.NewState;
import com.cucei.states.State;
import com.cucei.states.ProcessStates;

public class Process {
    private int id;
    private String name;
    private int processTime;
    private ProcessStates acutalState;
    private State state;
    private boolean isTerminated;
    private boolean isPaused;
    Random r = new Random();

    public Process(int id, String name){
        int maxTime = 10;
        int minTime = 1;
        this.id = id + 1;
        this.name = name;
        this.processTime = minTime + r.nextInt(maxTime);
        this.acutalState = ProcessStates.NEW;
        this.isTerminated = false;
        this.isPaused = false;

        this.state = new NewState(this);
    }
    
    public void changeState(State state) {
        this.state = state;
    }
    
    public void setTerminated(boolean isTerminated) {
      this.isTerminated = isTerminated;
    }

    public void setAcutalState(ProcessStates acutalState) {
      this.acutalState = acutalState;
    }

    public boolean isTerminated() {
      return isTerminated;
    }

    public boolean isPaused() {
      return isPaused;
    }

    public void tooglePaused() {
        this.isPaused = !this.isPaused;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProcessTime() {
        return processTime;
    }

    public String getAcutalState() {
        return acutalState.label;
    }

    public State getState() {
      return state;
    }

}
