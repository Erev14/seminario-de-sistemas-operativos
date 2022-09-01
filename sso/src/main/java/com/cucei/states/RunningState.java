package com.cucei.states;

import com.cucei.Process;

public class RunningState extends State{

  RunningState(Process process) {
    super(process);
    process.setAcutalState(ProcessStates.RUNNING);
  }

  @Override
  public String onRun() {
    return "Run Locked...";
  }

  @Override
  public String onPause() {
    process.changeState(new WaitingState(process));
    // TODO check if change for get acutal state
    return "Waiting...";
  }

  @Override
  public String onResume() {
    return "Resume Locked...";
  }

  @Override
  public String onNext() {
    // TODO first set terminated before call onNext()
    if(process.isTerminated()){
      process.changeState(new TerminatedState(process));    
      return "Terminating...";
    }

    // Process not terminue and don't get block, (For RR)
    process.changeState(new ReadyState(process));
    return "Go to Ready...";
    
    // return "Next Locked for invalid states...";
  }
}