package com.asclepius.asclepiusservice.controller;

import com.asclepius.asclepiusservice.model.SimpleMessage;
import com.asclepius.asclepiusservice.service.ReceptionistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
//@AllArgsConstructor
public class ReceptionistController {

    @Autowired
    @Qualifier("receptionistModel")
    private ReceptionistService receptionistService;

//    @PostMapping(value = "/streamingChat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<SimpleMessage> streamingChat(@RequestBody SimpleMessage simpleMessage) {
//        return receptionistService.streamingChat(simpleMessage.getContent()).log();
//    }

    @PostMapping(value = "/chat")
    public String chat(@RequestBody SimpleMessage simpleMessage) {
        return receptionistService.chat(simpleMessage.getContent()).getContent();
    }
}
