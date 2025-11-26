package com.hardik.openai.text;

import com.hardik.openai.entity.GameData;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TextService {

    private final ChatClient chatClient;

    public TextService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public Flux<String> response(String message){
        System.out.println("asking the llm");
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }

    public Flux<String> similar(String gameName) {

        return chatClient.prompt()
                .user(u -> {
                    u.text("Find and recommend 5 games that are of the same style, genre and aesthetic and similar to {gameName}");
                    u.param("gameName", gameName);
                })
                .system("Dont give a detailed response. Only mention the name of the game and its full name as the release name is and as everyone knows it and its release/publishing date in parenthesis example- Metaphor Refantazio(2023) follows by the game name")
                .stream()
                .content();
    }

    public GameData gameInfo(String prompt) {

        return chatClient.prompt()
                .user(prompt)
                .call()
                .entity(GameData.class);
    }

}
