package com.example.endpointmonitoring.controller;

import com.example.endpointmonitoring.dto.MonitoredEndpoint;
import com.example.endpointmonitoring.service.MonitoredEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/monitored-endpoints")
public class MonitoredEndpointController {

    private final MonitoredEndpointService monitoredEndpointService;

    @Autowired
    public MonitoredEndpointController(MonitoredEndpointService monitoredEndpointService) {
        this.monitoredEndpointService = monitoredEndpointService;
    }

    @GetMapping
    public List<MonitoredEndpoint> getAllMonitoredEndpoints() {
        return monitoredEndpointService.getAllMonitoredEndpoints();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity createMonitoredEndpoint(@RequestBody MonitoredEndpoint monitoredEndpoint) {
        try {
            return ResponseEntity.ok(monitoredEndpointService.createMonitoredEndpoint(monitoredEndpoint));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getMonitoredEndpointById(@PathVariable Long id) {
        final Optional<MonitoredEndpoint> optionalMonitoredEndpoint = monitoredEndpointService.getMonitoredEndpointById(id);
        if (optionalMonitoredEndpoint.isPresent()) {
            return ResponseEntity.ok(optionalMonitoredEndpoint.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Monitored endpoint not found with id: " + id);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity updateMonitoredEndpoint(@PathVariable Long id, @RequestBody MonitoredEndpoint monitoredEndpointEntity) {
        try {
            return ResponseEntity.ok(monitoredEndpointService.updateMonitoredEndpoint(id, monitoredEndpointEntity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMonitoredEndpoint(@PathVariable Long id) {
        try {
            monitoredEndpointService.deleteMonitoredEndpoint(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
