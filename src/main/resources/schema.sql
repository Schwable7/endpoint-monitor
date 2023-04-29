CREATE SCHEMA IF NOT EXISTS endpoint_monitoring;

USE endpoint_monitoring;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS monitored_endpoints (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,v
    name VARCHAR(50) NOT NULL,
    url VARCHAR(255) NOT NULL,
    creation_time datetime NOT NULL,
    last_check_time datetime,
    monitored_interval INTEGER NOT NULL
--     owner_id BIGINT NOT NULL,
--     FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS monitoring_results (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_of_check datetime NOT NULL,
    returned_http_status INTEGER NOT NULL,
    returned_payload LONGTEXT NOT NULL,
    monitored_endpoint_id BIGINT NOT NULL,
    FOREIGN KEY (monitored_endpoint_id) REFERENCES monitored_endpoints(id)
);

INSERT INTO users(username, email, password) VALUES ("Applifting", 'info@applifting.cz', 'myPassword');

