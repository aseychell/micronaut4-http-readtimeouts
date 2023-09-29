package com.aseychell.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.serde.annotation.Serdeable;

import reactor.core.publisher.Mono;

@Client("httpbin")
@Produces("application/json")
public interface HttpBinClient {
    
    @Post("/post")
    Mono<HttpBinPostResponse> testPost(@Body String body);
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Serdeable
    class HttpBinPostResponse {
        
        private String url;
        private String origin;
    }
}
