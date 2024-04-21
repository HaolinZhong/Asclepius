package com.asclepius.asclepiusservice.AIModel;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ChatContextMonitor {

    @SystemMessage("Is this text talking about a specific physician? text: {{it}}")
    boolean isAboutPhysician(String text);

    @SystemMessage("According to the description, give the ID of the physician.")
    int getPhysicianId(String response);
}
