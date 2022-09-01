package com.cucei;

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

    Process(int id, String name, int processTime) {
        this.id = id;
        this.name = name;
        this.processTime = processTime;
        this.acutalState = ProcessStates.NEW;
        this.isTerminated = false;

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
