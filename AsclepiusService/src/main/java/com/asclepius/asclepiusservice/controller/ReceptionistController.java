package com.asclepius.asclepiusservice.controller;

import com.asclepius.asclepiusservice.model.SimpleMessage;
import com.asclepius.asclepiusservice.service.ReceptionistService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class ReceptionistController {

    private ReceptionistService receptionistService;

    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamingChat(@RequestBody SimpleMessage simpleMessage) {
        return receptionistService.streamingChat(simpleMessage.getMessage()).log();
    }
}
