package com.tananushka.grpcdemo.service;

import com.tananushka.grpcdemo.client.PingPongClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PingPongService {

    private final PingPongClient pingPongClient;

    public String ping() {
        return pingPongClient.ping("Java Ping");
    }
}
