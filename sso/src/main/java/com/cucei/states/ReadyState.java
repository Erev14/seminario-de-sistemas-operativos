package com.cucei.states;

import com.cucei.Process;

public class ReadyState extends State{

  ReadyState(Process process) {
    super(process);
    process.setAcutalState(ProcessStates.READY);
  }

  @Override
  public String onRun() {
    process.changeState(new RunningState(process));
    // TODO check if change for get acutal state 
    return "Running...";
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
    return "Next Locked...";
  }
  
}