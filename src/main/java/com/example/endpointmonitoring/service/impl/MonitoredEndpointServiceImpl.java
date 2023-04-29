package com.example.endpointmonitoring.service.impl;

import com.example.endpointmonitoring.converter.MonitoredEndpointConverter;
import com.example.endpointmonitoring.dto.MonitoredEndpoint;
import com.example.endpointmonitoring.entity.MonitoredEndpointEntity;
import com.example.endpointmonitoring.repository.MonitoredEndpointRepository;
import com.example.endpointmonitoring.service.MonitoredEndpointService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MonitoredEndpointServiceImpl implements MonitoredEndpointService {

    private final MonitoredEndpointRepository monitoredEndpointRepository;
    private final MonitoredEndpointConverter monitoredEndpointConverter;

    @Autowired
    public MonitoredEndpointServiceImpl(MonitoredEndpointRepository monitoredEndpointRepository, MonitoredEndpointConverter monitoredEndpointConverter) {
        this.monitoredEndpointRepository = monitoredEndpointRepository;
        this.monitoredEndpointConverter = monitoredEndpointConverter;

    }

    @Override
    public List<MonitoredEndpoint> getAllMonitoredEndpoints() {
        List<MonitoredEndpointEntity> monitoredEndpointEntities = monitoredEndpointRepository.findAll();
        return monitoredEndpointEntities.stream()
                .map(monitoredEndpointConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MonitoredEndpoint createMonitoredEndpoint(MonitoredEndpoint monitoredEndpoint) {
        validatedMonitoredEndpoint(monitoredEndpoint);
        MonitoredEndpointEntity monitoredEndpointEntity = monitoredEndpointConverter.convertDtoToEntity(monitoredEndpoint);
        MonitoredEndpointEntity savedEntity = monitoredEndpointRepository.save(monitoredEndpointEntity);
        return monitoredEndpointConverter.convertEntityToDto(savedEntity);
    }


    @Override
    public Optional<MonitoredEndpoint> getMonitoredEndpointById(Long id) {
        Optional<MonitoredEndpointEntity> monitoredEndpointEntity = monitoredEndpointRepository.findById(id);

        return monitoredEndpointEntity.map(e->monitoredEndpointConverter.convertEntityToDto(e));
    }

    @Override
    public MonitoredEndpoint updateMonitoredEndpoint(Long id, MonitoredEndpoint monitoredEndpoint) {
        MonitoredEndpointEntity existingEntity = monitoredEndpointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Monitored endpoint not found with id: " + id));
        validatedMonitoredEndpoint(monitoredEndpoint);
        existingEntity.setName(monitoredEndpoint.getName());
        existingEntity.setUrl(monitoredEndpoint.getUrl());
        existingEntity.setInterval(monitoredEndpoint.getInterval());
        MonitoredEndpointEntity updatedEntity = monitoredEndpointRepository.save(existingEntity);
        return monitoredEndpointConverter.convertEntityToDto(updatedEntity);
    }

    @Override
    public void updateMonitoredEndpointLastCheckTime(Long id) {
        MonitoredEndpointEntity existingEntity = monitoredEndpointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Monitored endpoint not found with id: " + id));
        existingEntity.setLastCheckTime(new Timestamp(System.currentTimeMillis()));
        monitoredEndpointRepository.save(existingEntity);
    }

    @Override
    public void deleteMonitoredEndpoint(Long id) {
        final MonitoredEndpointEntity monitoredEndpointEntity = monitoredEndpointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Monitored endpoint not found with id: " + id));
        monitoredEndpointRepository.delete(monitoredEndpointEntity);
    }


    protected void validatedMonitoredEndpoint(MonitoredEndpoint monitoredEndpoint) {
        Pattern pattern = Pattern.compile("^(http|https)://.*$");
        if (!pattern.matcher(monitoredEndpoint.getUrl()).matches()) {
            throw new IllegalArgumentException("Url must be of correct format and start with http:// or https://");
        }
        if (monitoredEndpoint.getName() == null || monitoredEndpoint.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (monitoredEndpoint.getInterval() == null || monitoredEndpoint.getInterval() < 10) {
            throw new IllegalArgumentException("Interval is required and must be 10 or greater");
        }
    }

}
