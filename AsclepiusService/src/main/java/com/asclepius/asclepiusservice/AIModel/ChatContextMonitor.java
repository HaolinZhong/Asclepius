package com.asclepius.asclepiusservice.AIModel;

import dev.langchain4j.service.UserMessage;

public interface ChatContextMonitor {

    @UserMessage("Is this text talking about a specific physician? text: {{it}}")
    boolean isAboutPhysician(String text);
}
