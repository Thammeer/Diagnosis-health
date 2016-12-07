package com.app.diagnosis.diagnosis;

import java.util.List;

public class Results {

    private String message;
    private String name;
    private List<json> appointments;
    private List<previous_json> previous;

    public Results() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public List<json> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<json> appointments) {
        this.appointments = appointments;
    }


    public List<previous_json> getPrevious() {
        return previous;
    }

    public void setPrevious(List<previous_json> previous) {
        this.previous = previous;
    }






}