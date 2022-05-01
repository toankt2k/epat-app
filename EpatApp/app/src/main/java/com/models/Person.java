package com.models;

public class Person {
    private long id;
    private String name;
    private long code;

    public Person() {
    }

    public Person(long id, String name, long code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public long getIds() {
        return id;
    }

    public void setIds(long id) {
        this.id = id;
    }

    public String getNames() {
        return name;
    }

    public void setNames(String name) {
        this.name = name;
    }

    public long getCodes() {
        return code;
    }

    public void setCodes(long code) {
        this.code = code;
    }
}
