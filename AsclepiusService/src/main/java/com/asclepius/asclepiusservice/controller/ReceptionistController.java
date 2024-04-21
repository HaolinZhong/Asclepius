package com.asclepius.asclepiusservice.controller;

import com.asclepius.asclepiusservice.AIModel.ChatContextMonitor;
import com.asclepius.asclepiusservice.model.SimpleMessage;
import com.asclepius.asclepiusservice.AIModel.Receptionist;
import com.asclepius.asclepiusservice.repository.PhysicianRepository;
import dev.langchain4j.service.MemoryId;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ReceptionistController {

    private Receptionist receptionist;

    private ChatContextMonitor chatContextMonitor;

    private PhysicianRepository physicianRepository;

    @Autowired
    public ReceptionistController(Receptionist receptionist, ChatContextMonitor chatContextMonitor, PhysicianRepository physicianRepository) {
        this.receptionist = receptionist;
        this.chatContextMonitor = chatContextMonitor;
        this.physicianRepository = physicianRepository;
    }

    private AtomicInteger memoryId = new AtomicInteger(0);

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceptionistController.class);

    @PostMapping(value = "/chat")
    public SimpleMessage chat(@RequestBody SimpleMessage simpleMessage) {

        try {
            if (simpleMessage.getType().equals("init")) {
                memoryId.incrementAndGet();
                return null;
            }

            String response = receptionist.chat(memoryId.get(), simpleMessage.getContent());

            if (chatContextMonitor.isAboutPhysician(response)) {
                return SimpleMessage.physicianDataMessage(response, physicianRepository.getPhysicianById(chatContextMonitor.getPhysicianId(response)));
            }
            return SimpleMessage.assistantMessage(response);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return SimpleMessage.assistantMessage("Oops...Something's wrong about the sever. Try to check back later...");
        }

    }
}
