package com.example.endpointmonitoring.converter;

import com.example.endpointmonitoring.dto.MonitoredEndpoint;
import com.example.endpointmonitoring.entity.MonitoredEndpointEntity;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MonitoredEndpointConverter {

    public MonitoredEndpointEntity convertDtoToEntity(MonitoredEndpoint monitoredEndpoint) {
        MonitoredEndpointEntity monitoredEndpointEntity = new MonitoredEndpointEntity();
        monitoredEndpointEntity.setId(monitoredEndpoint.getId());
        monitoredEndpointEntity.setName(monitoredEndpoint.getName());
        monitoredEndpointEntity.setUrl(monitoredEndpoint.getUrl());
        monitoredEndpointEntity.setInterval(monitoredEndpoint.getInterval());
        return monitoredEndpointEntity;
    }

    public MonitoredEndpoint convertEntityToDto(MonitoredEndpointEntity monitoredEndpointEntity) {
        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
        monitoredEndpoint.setId(monitoredEndpointEntity.getId());
        monitoredEndpoint.setName(monitoredEndpointEntity.getName());
        monitoredEndpoint.setUrl(monitoredEndpointEntity.getUrl());
        monitoredEndpoint.setInterval(monitoredEndpointEntity.getInterval());
        monitoredEndpoint.setCreationTime(new DateTime(monitoredEndpointEntity.getCreationTime()));
        monitoredEndpoint.setLastCheckTime(new DateTime(getLastCheckTime(monitoredEndpointEntity)));
        return monitoredEndpoint;
    }

    private Timestamp getLastCheckTime(MonitoredEndpointEntity monitoredEndpointEntity) {
        return monitoredEndpointEntity.getLastCheckTime() == null ? monitoredEndpointEntity.getCreationTime() : monitoredEndpointEntity.getLastCheckTime();
    }
}
