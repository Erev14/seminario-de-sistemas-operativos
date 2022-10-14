package com.cucei.states;

import com.cucei.Process;

public abstract class State {
  Process process;

  State(Process process) {
    this.process = process;
  }

  public abstract String onRun();
  public abstract String onPause();
  public abstract String onResume();
  // Automated call when some process complete (no user/button interaction)
  public abstract String onNext(); 
}
