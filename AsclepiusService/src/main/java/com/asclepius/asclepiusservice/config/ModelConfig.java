package com.asclepius.asclepiusservice.config;

import com.asclepius.asclepiusservice.AIModel.ChatContextMonitor;
import com.asclepius.asclepiusservice.AIModel.Receptionist;
import com.asclepius.asclepiusservice.AItools.AppointmentManager;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.UrlDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.transformer.HtmlTextExtractor;
import dev.langchain4j.data.segment.TextSegment;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocuments;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class ModelConfig {

    @Value("${langchain4j.open-ai.chat-model.api-key}")
    private String openAIApiKey;

    @Value("${physician.api}")
    private String physicianApi;

    @Bean
    public OpenAiStreamingChatModel openAiStreamingChatModel() {
        return OpenAiStreamingChatModel.withApiKey(openAIApiKey);
    }

    @Bean
    public Receptionist receptionistModel() throws MalformedURLException {
        URL url = new URL(physicianApi);
        Document htmlDocument = UrlDocumentLoader.load(url, new TextDocumentParser());
        HtmlTextExtractor transformer = new HtmlTextExtractor(null, null, true);
        Document document = transformer.transform(htmlDocument);

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);

        return AiServices.builder(Receptionist.class)
                .chatLanguageModel(OpenAiChatModel.withApiKey(openAIApiKey))
                .chatMemory(chatMemory)
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
                .tools(new AppointmentManager())
                .build();
    }

    @Bean
    public ChatContextMonitor chatContextMonitorModel() {
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        return AiServices.builder(ChatContextMonitor.class)
                .chatLanguageModel(OpenAiChatModel.withApiKey(openAIApiKey))
                .chatMemory(chatMemory)
                .build();
    }
}
