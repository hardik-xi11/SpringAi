package com.hardik.openai.images;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class ImageService {


    private final ChatClient chatClient;
    private final ImageModel imageModel;

    @Value("classpath:/images/metaphor.png")
    Resource image;

    public ImageService(ChatClient chatClient, ImageModel imageModel) {
        this.imageModel = imageModel;
        this.chatClient = chatClient;
    }

    public Flux<String> imageToText() {

        return chatClient.prompt()
                .user(u -> {
                    u.text("Describe the image and find which game the image is taken from and mention the name of the game.");
                    u.media(MimeTypeUtils.IMAGE_JPEG, image);
                })
                .stream()
                .content();
    }

    public ResponseEntity<Map<String, String>> generateImage(String prompt) {
        ImageOptions options = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1792)
                .height(1024)
                .quality("hd")
                .style("vivid")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt, options);
        ImageResponse imageResponse = imageModel.call(imagePrompt);

        String url = imageResponse.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of(
                "prompt", prompt,
                "image-url", url
        ));
    }
}
