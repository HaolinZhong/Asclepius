package com.asclepius.asclepiusservice.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ChatContextMonitor {

    @SystemMessage("You are a conversation context monitor. You task is to ")
    @UserMessage("Is the following text within the specified context? text: {{it}}")
    boolean isWithinContext(String text);
}
