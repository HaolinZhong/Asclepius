package com.asclepius.asclepiusservice.service;

import com.asclepius.asclepiusservice.model.SimpleMessage;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ReceptionistServiceImpl implements ReceptionistService {

    private OpenAiStreamingChatModel openAiStreamingChatModel;

//    @Override
    public Flux<SimpleMessage> streamingChat(String prompt) {
        return Flux.create(stringFluxSink -> {
            openAiStreamingChatModel.generate(prompt, new StreamingResponseHandler<AiMessage>() {
                @Override
                public void onNext(String s) {
                    stringFluxSink.next(SimpleMessage.assistantMessage(s));
                }

                @Override
                public void onError(Throwable throwable) {
                    stringFluxSink.error(throwable);
                }

                @Override
                public void onComplete(Response<AiMessage> response) {
                    StreamingResponseHandler.super.onComplete(response);
                    stringFluxSink.complete();
                }
            });
        });
    }

    @Override
    public SimpleMessage chat(String prompt) {
        return null;
    }
}


