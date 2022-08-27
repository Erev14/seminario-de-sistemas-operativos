package com.cucei;

public class Process {
    private int id;
    private String name;
    private int process_time;

    Process(int id, String name, int process_time){
        int max_time = 4;
        int min_time = 1;
        this.id = id;
        this.name = name;
        this.process_time = process_time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProcess_time() {
        return process_time;
    }

}
