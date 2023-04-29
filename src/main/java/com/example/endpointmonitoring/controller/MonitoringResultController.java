package com.example.endpointmonitoring.controller;

import com.example.endpointmonitoring.dto.MonitoringResultsResponse;
import com.example.endpointmonitoring.service.MonitoringResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring-results")
public class MonitoringResultController {

    private final MonitoringResultService monitoringResultService;

    @Autowired
    public MonitoringResultController(MonitoringResultService monitoringResultService) {
        this.monitoringResultService = monitoringResultService;
    }

    @GetMapping
    public List<MonitoringResultsResponse> getMonitoringResults() {
        return monitoringResultService.findLast10MonitoringResultsForAllEndpoints();
    }
}
