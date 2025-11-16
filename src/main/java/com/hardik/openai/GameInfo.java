package com.hardik.openai;

import java.util.List;

public record GameInfo(
        String gameTitle,
        String about,
        List<String> genres,
        List<String> platforms,
        String developer,
        String publisher,
        String releaseYear,
        String completionTime
) {
}
