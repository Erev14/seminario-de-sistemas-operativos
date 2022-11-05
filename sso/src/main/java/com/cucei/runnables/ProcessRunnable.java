package com.cucei.runnables;

import com.cucei.models.Process;
public class ProcessRunnable implements Runnable{
    private int id;
    private Process process;
    private Object lock = new Object();

    public ProcessRunnable(int id, Process process){
        this.id = id;
        this.process = process;
        this.process.getState().onNext();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        this.process.getState().onRun();
    }
    
    public void pause() {
        this.process.getState().onPause();
        synchronized(lock){
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public void resume() {
        this.process.getState().onResume();
        synchronized(lock){
            notify();
        }
    }
    
    public void terminate() {
        this.process.getState().onNext();

    }
}
