package com.guster.doitnow;

import java.util.List;

/**
 * Created by Gusterwoei on 9/29/13.
 */
public class BadHabit {
    private String title;
    private int severity;
    private boolean done;
    private List<String> success;
    private List<String> fail;

    public BadHabit(List<String> s, List<String> f) {
        title = "";
        severity = 0;
        done = false;
        success = s;
        fail = f;
    }
    public BadHabit(String t, List<String> s, List<String> f) {
        title = t;
        severity = 0;
        done = false;
        success = s;
        fail = f;
    }

    public List<String> getFail() {
        return fail;
    }
    public void setFail(List<String> fail) {
        this.fail = fail;
    }

    public List<String> getSuccess() {
        return success;
    }
    public void setSuccess(List<String> success) {
        this.success = success;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeverity() {
        return severity;
    }
    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public boolean done() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
}
