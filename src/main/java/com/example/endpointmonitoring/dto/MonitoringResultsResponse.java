package com.example.endpointmonitoring.dto;

import java.util.List;

public class MonitoringResultsResponse {
    private MonitoredEndpoint monitoredEndpoint;
    private List<MonitoringResult> monitoringResults;

    public MonitoredEndpoint getMonitoredEndpoint() {
        return monitoredEndpoint;
    }

    public void setMonitoredEndpoint(MonitoredEndpoint monitoredEndpoint) {
        this.monitoredEndpoint = monitoredEndpoint;
    }

    public List<MonitoringResult> getMonitoringResults() {
        return monitoringResults;
    }

    public void setMonitoringResults(List<MonitoringResult> monitoringResults) {
        this.monitoringResults = monitoringResults;
    }
}
