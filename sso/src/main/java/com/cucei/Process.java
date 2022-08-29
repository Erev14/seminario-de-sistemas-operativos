package com.cucei;

public class Process {
    private int id;
    private String name;
    private int processTime;

    Process(int id, String name, int processTime){
        this.id = id;
        this.name = name;
        this.processTime = processTime;
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

}
