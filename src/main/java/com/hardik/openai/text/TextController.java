package com.hardik.openai.text;

import com.hardik.openai.entity.GameData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping("/chat")
    public Flux<String> chat(@RequestParam(value = "message", defaultValue = "Introduce yourself") String message) {

        return textService.response(message);
    }

    @GetMapping("/find")
    public Flux<String> similarGames(@RequestParam(value = "gameName", defaultValue = "El Shaddai") String gameName) {

        return textService.similar(gameName);

    }

    @GetMapping("/info")
    public GameData gameInfo(@RequestParam(value = "prompt", defaultValue = "five turn based dark fantasy games similar to Baldur's gate 3") String prompt) {

        return textService.gameInfo(prompt);

    }
}
