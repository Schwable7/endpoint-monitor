package com.example.endpointmonitoring;

import com.example.endpointmonitoring.service.MonitoredEndpointService;
import com.example.endpointmonitoring.service.MonitoringResultService;
import com.example.endpointmonitoring.service.impl.EndpointMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class EndpointMonitoringApplication {

	@Autowired
	private MonitoredEndpointService monitoredEndpointService;
	@Autowired
	private MonitoringResultService monitoringResultService;


	public static void main(String[] args) {
		SpringApplication.run(EndpointMonitoringApplication.class, args);
		System.out.println("Monitoring application started!");
	}

	@Bean
	public HttpClient httpClient() {
		return HttpClient.newHttpClient();
	}


	@Bean
	public ScheduledExecutorService scheduledExecutorService() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		EndpointMonitor endpointMonitor = new EndpointMonitor(monitoredEndpointService, monitoringResultService, httpClient());
		executorService.scheduleAtFixedRate(endpointMonitor, 0, 10, TimeUnit.SECONDS);
		return executorService;
	}

}
