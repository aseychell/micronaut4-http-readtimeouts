package com.aseychell.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Singleton;

import com.aseychell.client.HttpBinClient;

import reactor.core.publisher.Mono;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class HttpBinCaller {
    
    private final HttpBinClient httpBinClient;

    public String callApiAndBlock() {
        return httpBinClient.testPost("test")
            .flatMap(response -> {
                log.info("Received response {}", response);
                return Mono.just(response.getUrl());
            })
            .block();
    }
    
}
