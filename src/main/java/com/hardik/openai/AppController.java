package com.hardik.openai;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/chat")
    public Flux<String> chat(@RequestParam(value = "message", defaultValue = "Introduce yourself") String message) {

        return appService.response(message);
    }

    @GetMapping("/find")
    public Flux<String> similarGames(@RequestParam(value = "gameName", defaultValue = "El Shaddai") String gameName) {

        return appService.similar(gameName);

    }

    @GetMapping("/info")
    public GameData gameInfo(@RequestParam(value = "prompt", defaultValue = "five turn based dark fantasy games similar to Baldur's gate 3") String prompt) {

        return appService.gameInfo(prompt);

    }
}
