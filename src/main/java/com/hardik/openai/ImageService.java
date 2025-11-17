package com.hardik.openai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

@Service
public class ImageService {


    private final ChatClient chatClient;

    @Value("classpath:/images/deadspace.jpg")
    Resource image;

    public ImageService(ChatClient.Builder chatClient,
                        @Value("${sysInstructions}")
                        String sysInstructions) {

        this.chatClient = chatClient
                .defaultSystem(sysInstructions)
                .build();
    }
    public Flux<String> imageToText() {

        return chatClient.prompt()
                .user(u -> {
                    u.text("Describe the image");
                    u.media(MimeTypeUtils.IMAGE_JPEG, image);
                })
                .stream()
                .content();
    }
}
