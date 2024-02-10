package com.tananushka.grpcdemo.config;

import com.tananushka.grpcdemo.client.PingPongClient;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApplicationShutdownHook {

    private PingPongClient pingPongClient;

    @PreDestroy
    public void onShutdown() {
        pingPongClient.shutdown();
    }
}