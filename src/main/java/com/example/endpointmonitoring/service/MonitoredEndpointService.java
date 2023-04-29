package com.example.endpointmonitoring.service;

import com.example.endpointmonitoring.dto.MonitoredEndpoint;

import java.util.List;
import java.util.Optional;

public interface MonitoredEndpointService {

    List<MonitoredEndpoint> getAllMonitoredEndpoints();

    MonitoredEndpoint createMonitoredEndpoint(MonitoredEndpoint monitoredEndpoint);

    Optional<MonitoredEndpoint> getMonitoredEndpointById(Long id);

    MonitoredEndpoint updateMonitoredEndpoint(Long id, MonitoredEndpoint monitoredEndpoint);

    void updateMonitoredEndpointLastCheckTime(Long id);

    void deleteMonitoredEndpoint(Long id);
}
