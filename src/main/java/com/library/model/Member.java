package com.library.model;

public class Member extends Person {
    private static final long serialVersionUID = 1L;

    public Member(String id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
