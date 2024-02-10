package com.tananushka.grpcdemo.controller;

import com.tananushka.grpcdemo.service.PingPongService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class PingPongController {

    private final PingPongService pingPongService;

    @GetMapping("/ping")
    public String ping() {
        return pingPongService.ping();
    }
}
