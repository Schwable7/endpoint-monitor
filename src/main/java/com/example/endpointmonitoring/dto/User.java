package com.example.endpointmonitoring.dto;


import java.util.List;

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<MonitoredEndpoint> monitoredEndpoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MonitoredEndpoint> getMonitoredEndpoints() {
        return monitoredEndpoints;
    }

    public void setMonitoredEndpoints(List<MonitoredEndpoint> monitoredEndpoints) {
        this.monitoredEndpoints = monitoredEndpoints;
    }
}
