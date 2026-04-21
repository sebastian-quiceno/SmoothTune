package com.group.smoothtune.application.usecase.Genre;

import com.group.smoothtune.domain.model.Genre;
import com.group.smoothtune.domain.port.GenreRepository;

public class CreateGenreUseCase {

    private final GenreRepository genreRepository;

    public CreateGenreUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre execute(String name,String description){
        Genre genre = new Genre(name, description);
        Genre newGenre = genreRepository.save(genre);
        System.out.println("El ID del genero creado es: "+newGenre.getId());
        return newGenre;
    }
}
