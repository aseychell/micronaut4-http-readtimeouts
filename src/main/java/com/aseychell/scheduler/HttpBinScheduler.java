package com.aseychell.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Singleton;

import io.micronaut.scheduling.annotation.Scheduled;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class HttpBinScheduler {
    
    private final HttpBinCaller httpBinCaller;
    
    @Scheduled(fixedDelay = "10s", initialDelay = "1s")
    public void execute() {
        try {
            Flux.just("")
                .subscribeOn(Schedulers.boundedElastic())
                .map(jackpotType -> httpBinCaller.callApiAndBlock())
                .subscribe();
        } catch (final Exception e) {
            log.error("Unexpected error on calling httpbin", e);
        }
    }
}
