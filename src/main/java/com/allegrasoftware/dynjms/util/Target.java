package com.allegrasoftware.dynjms.util;

public enum Target {

    INTEROPFLEX(99);

    private int value;

    Target(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
