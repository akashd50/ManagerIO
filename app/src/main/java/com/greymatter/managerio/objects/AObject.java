package com.greymatter.managerio.objects;

public abstract class AObject {
    private int id;
    public AObject() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
