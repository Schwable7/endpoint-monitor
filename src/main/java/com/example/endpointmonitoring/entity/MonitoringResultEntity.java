package com.example.endpointmonitoring.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "monitoring_results")
public class MonitoringResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_check")
    private Timestamp dateOfCheck;

    @Column(name = "returned_http_status")
    private Integer returnedHttpStatus;

    @Column(name = "returned_payload")
    private String returnedPayload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitored_endpoint_id")
    private MonitoredEndpointEntity monitoredEndpoint;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateOfCheck() {
        return dateOfCheck;
    }

    public void setDateOfCheck(Timestamp dateOfCheck) {
        this.dateOfCheck = dateOfCheck;
    }

    public Integer getReturnedHttpStatus() {
        return returnedHttpStatus;
    }

    public void setReturnedHttpStatus(Integer returnedHttpStatus) {
        this.returnedHttpStatus = returnedHttpStatus;
    }

    public String getReturnedPayload() {
        return returnedPayload;
    }

    public void setReturnedPayload(String returnedPayload) {
        this.returnedPayload = returnedPayload;
    }

    public MonitoredEndpointEntity getMonitoredEndpoint() {
        return monitoredEndpoint;
    }

    public void setMonitoredEndpoint(MonitoredEndpointEntity monitoredEndpoint) {
        this.monitoredEndpoint = monitoredEndpoint;
    }
}
