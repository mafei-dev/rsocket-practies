package com.mafei.rsocket.practies.bean;

public class RequestBean {
    private int input;

    @Override
    public String toString() {
        return "RequestBean{" +
                "input=" + input +
                '}';
    }

    public RequestBean() {
    }

    public RequestBean(int input) {
        this.input = input;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }
}
