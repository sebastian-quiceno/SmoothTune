package com.group.smoothtune.application.usecase.Genre;

import com.group.smoothtune.domain.model.Genre;
import com.group.smoothtune.domain.port.GenreRepository;

import java.util.List;

public class GetGenresUseCase {

    private final GenreRepository genreRepository;

    public GetGenresUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> execute(){
        return genreRepository.findAll();
    }
}
