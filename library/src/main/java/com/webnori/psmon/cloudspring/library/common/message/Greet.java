package com.webnori.psmon.cloudspring.library.common.message;

public class Greet {
    private String name;

    public Greet(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
