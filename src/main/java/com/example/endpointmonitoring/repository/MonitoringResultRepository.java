package com.example.endpointmonitoring.repository;

import com.example.endpointmonitoring.dto.MonitoringResult;
import com.example.endpointmonitoring.entity.MonitoringResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonitoringResultRepository extends JpaRepository<MonitoringResultEntity, Long> {
    List<MonitoringResultEntity> findTop10ByMonitoredEndpointIdOrderByDateOfCheck(Long monitoredEndpointId);
}
