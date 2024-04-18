package com.asclepius.asclepiusservice.config;

import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
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
}
