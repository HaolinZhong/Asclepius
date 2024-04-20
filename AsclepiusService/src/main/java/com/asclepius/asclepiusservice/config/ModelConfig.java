package com.asclepius.asclepiusservice.config;

import com.asclepius.asclepiusservice.service.ReceptionistService;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

    @Value("${langchain4j.open-ai.chat-model.api-key}")
    private String openAIApiKey;

    @Bean
    public OpenAiStreamingChatModel openAiStreamingChatModel() {
        return OpenAiStreamingChatModel.withApiKey(openAIApiKey);
    }

    @Bean
    public ReceptionistService receptionistModel() {
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        return AiServices.builder(ReceptionistService.class)
                .chatLanguageModel(OpenAiChatModel.withApiKey(openAIApiKey))
                .chatMemory(chatMemory)
                .build();
    }
}
