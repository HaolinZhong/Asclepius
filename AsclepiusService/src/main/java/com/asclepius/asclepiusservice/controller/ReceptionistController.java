package com.asclepius.asclepiusservice.controller;

import com.asclepius.asclepiusservice.AIModel.ChatContextMonitor;
import com.asclepius.asclepiusservice.model.SimpleMessage;
import com.asclepius.asclepiusservice.AIModel.Receptionist;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReceptionistController {

    private Receptionist receptionist;

    private ChatContextMonitor chatContextMonitor;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceptionistController.class);

    @PostMapping(value = "/chat")
    public SimpleMessage chat(@RequestBody SimpleMessage simpleMessage) {

        try {
            SimpleMessage response = receptionist.chat(simpleMessage.getContent());

            if (chatContextMonitor.isAboutPhysician(response.getContent())) {
                return SimpleMessage.physicianDataMessage(response.getContent(), receptionist.sendJsonPhysicianInfo());
            }
            return response;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return SimpleMessage.assistantMessage("Oops...Something's wrong about the sever. Try to check back later...");
        }

    }
}
