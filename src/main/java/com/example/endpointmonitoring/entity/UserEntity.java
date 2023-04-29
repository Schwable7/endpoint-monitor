package com.example.endpointmonitoring.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

//    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
//    private List<MonitoredEndpointEntity> monitoredEndpoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<MonitoredEndpointEntity> getMonitoredEndpoints() {
//        return monitoredEndpoints;
//    }
//
//    public void setMonitoredEndpoints(List<MonitoredEndpointEntity> monitoredEndpoints) {
//        this.monitoredEndpoints = monitoredEndpoints;
//    }
}
