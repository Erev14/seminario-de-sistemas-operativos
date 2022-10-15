package com.cucei.states;

import com.cucei.resources.Process;

public class NewState extends State {

  public NewState(Process process) {
    super(process);
    process.setAcutalState(ProcessStates.NEW);
  }

  @Override
  public String onRun() {
    return "Run Locked...";
  }
  
  @Override
  public String onPause() {
    return "Pause Locked...";
  }
  
  @Override
  public String onResume() {
    return "Resume Locked...";
  }
  
  @Override
  public String onNext() {
    process.changeState(new ReadyState(process));
    // TODO check if works or need to return state
    return process.getAcutalState(); 
  }
  
}
