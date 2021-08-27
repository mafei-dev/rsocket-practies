package com.mafei.rsocket.practies.bean;

public class ResponseBean {
    private int input;
    private int output;

    @Override
    public String toString() {
        return "ResponseBean{" +
                "input=" + input +
                ", output=" + output +
                '}';
    }

    public ResponseBean() {
    }

    public ResponseBean(int input, int output) {
        this.input = input;
        this.output = output;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
}
