package com.group.smoothtune.controller;

import com.group.smoothtune.application.dtos.GenreRequestDTO;
import com.group.smoothtune.application.dtos.GenreResponseDTO;
import com.group.smoothtune.application.mapper.GenreMapper;
import com.group.smoothtune.application.usecase.Genre.CreateGenreUseCase;
import com.group.smoothtune.domain.model.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final CreateGenreUseCase createGenreUseCase;

    public GenreController(CreateGenreUseCase createGenreUseCase) {
        this.createGenreUseCase = createGenreUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<GenreResponseDTO> createGenre(@RequestBody GenreRequestDTO dto){
        Genre resultGenre = createGenreUseCase.execute(dto.getName(), dto.getDescription());

        GenreResponseDTO response = GenreMapper.toResponse((resultGenre));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
