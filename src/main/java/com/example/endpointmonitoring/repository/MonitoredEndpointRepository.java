package com.example.endpointmonitoring.repository;

import com.example.endpointmonitoring.entity.MonitoredEndpointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonitoredEndpointRepository extends JpaRepository<MonitoredEndpointEntity, Long> {

     Optional<MonitoredEndpointEntity> findById(Long id);

}