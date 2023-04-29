package com.example.endpointmonitoring.service.impl;

import com.example.endpointmonitoring.dto.MonitoredEndpoint;
import com.example.endpointmonitoring.dto.MonitoringResult;
import com.example.endpointmonitoring.service.MonitoredEndpointService;
import com.example.endpointmonitoring.service.MonitoringResultService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class EndpointMonitor implements Runnable{

    private final MonitoredEndpointService monitoredEndpointService;
    private final MonitoringResultService monitoringResultService;
    private final HttpClient httpClient;

    @Autowired
    public EndpointMonitor(MonitoredEndpointService monitoredEndpointService, MonitoringResultService monitoringResultService, HttpClient httpClient) {
        this.monitoredEndpointService = monitoredEndpointService;
        this.monitoringResultService = monitoringResultService;
        this.httpClient = httpClient;
    }

    @Override
    public void run() {
        monitorEndpoints();
    }

    public void monitorEndpoints() {
        List<MonitoredEndpoint> endpoints = monitoredEndpointService.getAllMonitoredEndpoints();
        for (MonitoredEndpoint endpoint : endpoints) {
            DateTime lastCheckDate = endpoint.getLastCheckTime();
            DateTime nextCheckDate = lastCheckDate.plusSeconds(endpoint.getInterval());
            if (nextCheckDate.isBefore(DateTime.now())) {
                monitorEndpointAndLogResult(endpoint);
            }
        }
    }

    private void monitorEndpointAndLogResult(MonitoredEndpoint endpoint) {
        try {
            HttpResponse<String> response = getHttpResponse(endpoint);
            logResultAndUpdateEntity(endpoint, response.statusCode(), response.body());
        } catch (RestClientException e) {
            logResultAndUpdateEntity(endpoint, -1, "");
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.out.println("Error while monitoring endpoint " + endpoint.getName());
            System.out.println(e.getMessage());
        }
    }

    private void logResultAndUpdateEntity(MonitoredEndpoint endpoint, int statusCode, String responseBody) {
        logMonitoringResult(endpoint, statusCode, responseBody);
        monitoredEndpointService.updateMonitoredEndpointLastCheckTime(endpoint.getId());
    }


    private void logMonitoringResult(MonitoredEndpoint endpoint, int statusCode, String responseBody) {
        MonitoringResult monitoringResult = new MonitoringResult();
        monitoringResult.setMonitoredEndpoint(endpoint);
        monitoringResult.setReturnedHttpStatus(statusCode);
        monitoringResult.setReturnedPayload(responseBody);
        monitoringResult.setDateOfCheck(DateTime.now());
        monitoringResultService.saveMonitoringResult(monitoringResult);
    }

    private HttpResponse<String> getHttpResponse(MonitoredEndpoint endpoint) throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = httpClient.send(
                HttpRequest.newBuilder().uri(new URI(endpoint.getUrl())).GET().build(),
                HttpResponse.BodyHandlers.ofString());
        return response;
    }

}
