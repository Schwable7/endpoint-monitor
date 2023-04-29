package com.example.endpointmonitoring.converter;

import com.example.endpointmonitoring.dto.MonitoringResult;
import com.example.endpointmonitoring.entity.MonitoringResultEntity;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MonitoringResultConverter {

    @Autowired
    private MonitoredEndpointConverter monitoredEndpointConverter;
    public MonitoringResult convertEntityToDto(MonitoringResultEntity monitoringResultEntity) {
        MonitoringResult monitoringResult = new MonitoringResult();
        monitoringResult.setId(monitoringResultEntity.getId());
        monitoringResult.setDateOfCheck(new DateTime(monitoringResultEntity.getDateOfCheck()));
        monitoringResult.setMonitoredEndpoint(monitoredEndpointConverter.convertEntityToDto(monitoringResultEntity.getMonitoredEndpoint()));
        monitoringResult.setReturnedHttpStatus(monitoringResultEntity.getReturnedHttpStatus());
        monitoringResult.setReturnedPayload(monitoringResultEntity.getReturnedPayload());
        return monitoringResult;
    }

    public MonitoringResultEntity convertDtoToEntity(MonitoringResult monitoringResult) {
        MonitoringResultEntity monitoringResultEntity = new MonitoringResultEntity();
        monitoringResultEntity.setId(monitoringResult.getId());
        monitoringResultEntity.setDateOfCheck(new Timestamp(monitoringResult.getDateOfCheck().getMillis()));
        monitoringResultEntity.setMonitoredEndpoint(monitoredEndpointConverter.convertDtoToEntity(monitoringResult.getMonitoredEndpoint()));
        monitoringResultEntity.setReturnedHttpStatus(monitoringResult.getReturnedHttpStatus());
        monitoringResultEntity.setReturnedPayload(monitoringResult.getReturnedPayload());
        return monitoringResultEntity;
    }
}
