package com.example.endpointmonitoring.dto;

import com.example.endpointmonitoring.util.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

import java.time.LocalDateTime;

public class MonitoringResult {

    private Long id;
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime dateOfCheck;
    private Integer returnedHttpStatus;
    private String returnedPayload;
    private MonitoredEndpoint monitoredEndpoint;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDateOfCheck() {
        return dateOfCheck;
    }

    public void setDateOfCheck(DateTime dateOfCheck) {
        this.dateOfCheck = dateOfCheck;
    }

    public Integer getReturnedHttpStatus() {
        return returnedHttpStatus;
    }

    public void setReturnedHttpStatus(Integer returnedHttpStatus) {
        this.returnedHttpStatus = returnedHttpStatus;
    }

    public String getReturnedPayload() {
        return returnedPayload;
    }

    public void setReturnedPayload(String returnedPayload) {
        this.returnedPayload = returnedPayload;
    }

    public MonitoredEndpoint getMonitoredEndpoint() {
        return monitoredEndpoint;
    }

    public void setMonitoredEndpoint(MonitoredEndpoint monitoredEndpoint) {
        this.monitoredEndpoint = monitoredEndpoint;
    }
}
