package com.app.diagnosis.diagnosis;

/**
 * Created by MSB on 10/16/2016.
 */
public class Results {
    /**
     * message : Required field(s) is missing
     */
    public Results() {

    }

    private String message;
    private String name;
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

}