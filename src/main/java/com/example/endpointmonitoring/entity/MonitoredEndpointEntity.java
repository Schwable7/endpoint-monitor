package com.example.endpointmonitoring.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "monitored_endpoints")
public class MonitoredEndpointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @Column(name = "monitored_interval")
    private Integer interval;
    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(System.currentTimeMillis());
    @Column(name = "last_check_time")
    private Timestamp lastCheckTime;

//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private UserEntity owner;

    @OneToMany(mappedBy = "monitoredEndpoint", cascade = CascadeType.ALL)
    private List<MonitoringResultEntity> monitoringResults;

//    public UserEntity getOwner() {
//        return owner;
//    }
//
//    public void setOwner(UserEntity owner) {
//        this.owner = owner;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(Timestamp lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }
}
