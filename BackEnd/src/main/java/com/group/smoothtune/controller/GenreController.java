package com.group.smoothtune.controller;

import com.group.smoothtune.application.dtos.GenreRequestDTO;
import com.group.smoothtune.application.dtos.GenreResponseDTO;
import com.group.smoothtune.application.mapper.GenreMapper;
import com.group.smoothtune.application.usecase.Genre.CreateGenreUseCase;
import com.group.smoothtune.application.usecase.Genre.GetGenresUseCase;
import com.group.smoothtune.domain.model.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final CreateGenreUseCase createGenreUseCase;
    private final GetGenresUseCase getGenresUseCase;

    public GenreController(CreateGenreUseCase createGenreUseCase, GetGenresUseCase getGenresUseCase) {
        this.createGenreUseCase = createGenreUseCase;
        this.getGenresUseCase = getGenresUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<GenreResponseDTO> createGenre(@RequestBody GenreRequestDTO dto){
        Genre resultGenre = createGenreUseCase.execute(dto.getName(), dto.getDescription());

        GenreResponseDTO response = GenreMapper.toResponse((resultGenre));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getGenres")
    public ResponseEntity<List<GenreResponseDTO>> getGenres(){
        List<GenreResponseDTO> response = getGenresUseCase.execute().stream().map(GenreMapper :: toResponse).toList();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
