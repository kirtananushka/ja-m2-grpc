package com.tananushka.grpcdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "grpc.server")
@Data
public class GrpcServerProperties {
    private String address;
    private int port;
}