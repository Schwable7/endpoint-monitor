package com.example.endpointmonitoring.service.impl;

import com.example.endpointmonitoring.converter.MonitoringResultConverter;
import com.example.endpointmonitoring.dto.MonitoredEndpoint;
import com.example.endpointmonitoring.dto.MonitoringResult;
import com.example.endpointmonitoring.dto.MonitoringResultsResponse;
import com.example.endpointmonitoring.entity.MonitoringResultEntity;
import com.example.endpointmonitoring.repository.MonitoringResultRepository;
import com.example.endpointmonitoring.service.MonitoredEndpointService;
import com.example.endpointmonitoring.service.MonitoringResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitoringResultServiceImpl implements MonitoringResultService {

    private final MonitoredEndpointService monitoredEndpointService;
    private final MonitoringResultRepository monitoringResultRepository;
    private final MonitoringResultConverter monitoringResultConverter;

    @Autowired
    public MonitoringResultServiceImpl(MonitoredEndpointService monitoredEndpointService, MonitoringResultRepository monitoringResultRepository, MonitoringResultConverter monitoringResultConverter) {
        this.monitoredEndpointService = monitoredEndpointService;
        this.monitoringResultRepository = monitoringResultRepository;
        this.monitoringResultConverter = monitoringResultConverter;
    }

    @Override
    public MonitoringResult saveMonitoringResult(MonitoringResult monitoringResult) {
        final MonitoringResultEntity monitoringResultEntity = monitoringResultConverter.convertDtoToEntity(monitoringResult);
        return monitoringResultConverter.convertEntityToDto(monitoringResultRepository.save(monitoringResultEntity));
    }

    @Override
    public List<MonitoringResultsResponse> findLast10MonitoringResultsForAllEndpoints() {
        final List<MonitoredEndpoint> endpoints = monitoredEndpointService.getAllMonitoredEndpoints();
        return endpoints.stream().map(this::getMonitoringResultsResponse).collect(Collectors.toList());
    }

    private MonitoringResultsResponse getMonitoringResultsResponse(MonitoredEndpoint endpoint) {
        final List<MonitoringResult> monitoringResults = findLast10MonitoringResultsForEndpoint(endpoint.getId());
        MonitoringResultsResponse monitoringResultsResponse = new MonitoringResultsResponse();
        monitoringResultsResponse.setMonitoringResults(monitoringResults);
        monitoringResultsResponse.setMonitoredEndpoint(endpoint);
        return monitoringResultsResponse;
    }

    private List<MonitoringResult> findLast10MonitoringResultsForEndpoint(Long id) {
        final List<MonitoringResultEntity> top10MonitoringResults = monitoringResultRepository.findTop10ByMonitoredEndpointIdOrderByDateOfCheck(id);
        return top10MonitoringResults.stream().map(monitoringResultConverter::convertEntityToDto).collect(Collectors.toList());
    }

}
