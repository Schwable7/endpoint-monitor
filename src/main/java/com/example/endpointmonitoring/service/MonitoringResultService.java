package com.example.endpointmonitoring.service;

import com.example.endpointmonitoring.dto.MonitoredEndpoint;
import com.example.endpointmonitoring.dto.MonitoringResult;
import com.example.endpointmonitoring.dto.MonitoringResultsResponse;

import java.util.List;
import java.util.Map;

public interface MonitoringResultService {

    MonitoringResult saveMonitoringResult(MonitoringResult monitoringResult);
    List<MonitoringResultsResponse> findLast10MonitoringResultsForAllEndpoints();

}
