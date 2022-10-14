package com.cucei.states;

import com.cucei.Process;

public class WaitingState extends State {

  WaitingState(Process process) {
    super(process);
    process.setAcutalState(ProcessStates.WAITING);
  }

  @Override
  public String onRun() {
    return "Run is Locked...";
  }

  @Override
  public String onPause() {
    return "Pause is Locked...";
  }

  @Override
  public String onResume() {
    return "Resume is Locked...";
  }

  @Override
  public String onNext() {
    process.changeState(new ReadyState(process));
    return "Ready...";
  }
}
