package com.testecall.testecall.controller;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class CallPanel {

    private String professional;

    private String personal;

    private String password;

    private String priority;

    private String priorityColor;

    private String local;

    private Integer attempts;

    private String date;

    private Boolean wasCalled;

    private Panel panel;

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriorityColor() {
        return priorityColor;
    }

    public void setPriorityColor(String priorityColor) {
        this.priorityColor = priorityColor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getWasCalled() {
        return wasCalled;
    }

    public void setWasCalled(Boolean wasCalled) {
        this.wasCalled = wasCalled;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public void setPanel(String uuid) {
        Panel panel_ = new Panel();
        panel_.setId(UUID.fromString(uuid.replaceAll("\\s", "-")));
        setPanel(panel_);
    }
}

class Panel {

    UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}