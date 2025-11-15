package com.hardik.openai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class AppController {

    private final ChatClient chatClient;

    public AppController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/chat")
    public Flux<String> response(){
        System.out.println("asking the llm");
        return chatClient.prompt()
                .user("List some turn based RPGs with the most interesting and unique mechanics of the genre")
                .system("only answer things related to video games: for any other question say sorry u are a gamer and gaming is all you know about!")
                .stream()
                .content();
    }
}
