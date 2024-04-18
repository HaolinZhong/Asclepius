package com.asclepius.asclepiusservice.service;

import reactor.core.publisher.Flux;

public interface ReceptionistService {
    Flux<String> streamingChat(String prompt);
}
