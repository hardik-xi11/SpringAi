package com.hardik.openai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ChatClient openaiChatClient(GoogleGenAiChatModel googleGenAiChatModel) {
        return ChatClient.create(googleGenAiChatModel);
    }
}
