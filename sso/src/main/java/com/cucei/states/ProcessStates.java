package com.cucei.states;

public enum ProcessStates {
  NEW("Nuevo"), READY("Preparado"), RUNNING("Ejecución"), TERMINATED("Terminado"), WAITING("Bloqueado");

  public final String label;

  private ProcessStates(String label) {
      this.label = label;
  }
}
