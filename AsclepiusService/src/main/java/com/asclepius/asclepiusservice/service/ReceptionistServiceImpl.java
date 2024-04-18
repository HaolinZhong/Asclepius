package com.asclepius.asclepiusservice.service;

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

    @Override
    public Flux<String> streamingChat(String prompt) {
        return Flux.create(stringFluxSink -> {
            openAiStreamingChatModel.generate(prompt, new StreamingResponseHandler<AiMessage>() {
                @Override
                public void onNext(String s) {
                    stringFluxSink.next(s);
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
}
