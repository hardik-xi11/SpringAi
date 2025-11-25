package com.hardik.openai.images;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/itt")
    public Flux<String> image() {

        return imageService.imageToText();

    }

    @GetMapping("/tti")
    public ResponseEntity<Map<String, String>> generateImage(@RequestParam(defaultValue = "A game cover for a Jrpg game with a medieval grand fantasy theme and aesthetics similar to metaphor refantazio and final fantasy") String prompt) {
        return imageService.generateImage(prompt);
    }
}
