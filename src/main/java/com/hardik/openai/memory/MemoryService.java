package com.hardik.openai.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MemoryService {

    private final ChatClient chatClient;

    public MemoryService(ChatClient.Builder builder, ChatMemory chatMemory, @Value("${sysInstructions}") String sysInstructions) {
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultSystem(sysInstructions)
                .build();
    }

    public String memory(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
