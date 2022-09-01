package com.cucei.states;

import com.cucei.Process;

public class TerminatedState extends State{

  TerminatedState(Process process) {
    super(process);
    process.setAcutalState(ProcessStates.TERMINATED);
  }
  
  @Override
  public String onRun() {
    return "Run Locked is already terminated.";
  }

  @Override
  public String onPause() {
    return "Pause Locked is already terminated.";
  }

  @Override
  public String onResume() {
    return "Resume Locked is already terminated.";
  }

  @Override
  public String onNext() {
    return "Next Locked is already terminated.";
  }

}
