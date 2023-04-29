package com.example.endpointmonitoring.dto;

import com.example.endpointmonitoring.util.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

public class MonitoredEndpoint {
    private Long id;
    private String name;
    private String url;
    private Integer interval;
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime creationTime;
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime lastCheckTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    public DateTime getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(DateTime lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

}
