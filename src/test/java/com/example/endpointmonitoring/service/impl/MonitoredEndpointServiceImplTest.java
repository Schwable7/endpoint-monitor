package com.example.endpointmonitoring.service.impl;

import com.example.endpointmonitoring.dto.MonitoredEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class MonitoredEndpointServiceImplTest {


    @Autowired
    private MonitoredEndpointServiceImpl service;


    @Test
    public void testValidateMonitoredEndpoint_InvalidUrl() {
        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
        monitoredEndpoint.setName("Test Endpoint");
        monitoredEndpoint.setUrl("invalid-url");
        monitoredEndpoint.setInterval(30);
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> service.validatedMonitoredEndpoint(monitoredEndpoint));
        Assertions.assertEquals("Url must be of correct format and start with http:// or https://", ex.getMessage());

    }

    @Test
    public void testValidateMonitoredEndpoint_NullName() {
        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
        monitoredEndpoint.setName(null);
        monitoredEndpoint.setUrl("http://www.example.com");
        monitoredEndpoint.setInterval(30);
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> service.validatedMonitoredEndpoint(monitoredEndpoint));
        Assertions.assertEquals("Name is required", ex.getMessage());
    }

    @Test
    public void testValidateMonitoredEndpoint_EmptyName() {
        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
        monitoredEndpoint.setName("");
        monitoredEndpoint.setUrl("http://www.example.com");
        monitoredEndpoint.setInterval(30);
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> service.validatedMonitoredEndpoint(monitoredEndpoint));
        Assertions.assertEquals("Name is required", ex.getMessage());
    }

    @Test
    public void testValidateMonitoredEndpoint_NullInterval() {
        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
        monitoredEndpoint.setName("Test Endpoint");
        monitoredEndpoint.setUrl("http://www.example.com");
        monitoredEndpoint.setInterval(null);
        IllegalArgumentException ex3 = Assertions.assertThrows(IllegalArgumentException.class, () -> service.validatedMonitoredEndpoint(monitoredEndpoint));
        Assertions.assertEquals("Interval is required and must be 10 or greater", ex3.getMessage());
    }

    @Test
    public void testValidateMonitoredEndpoint_InvalidInterval() {
        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
        monitoredEndpoint.setName("Test Endpoint");
        monitoredEndpoint.setUrl("http://www.example.com");
        monitoredEndpoint.setInterval(5);
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> service.validatedMonitoredEndpoint(monitoredEndpoint));
        Assertions.assertEquals("Interval is required and must be 10 or greater", ex.getMessage());
    }

    @Test
    public void testValidateMonitoredEndpoint_ValidEndpoint() {
        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
        monitoredEndpoint.setName("Test Endpoint");
        monitoredEndpoint.setUrl("http://www.example.com");
        monitoredEndpoint.setInterval(30);
        Assertions.assertDoesNotThrow(() -> service.validatedMonitoredEndpoint(monitoredEndpoint));
    }

}