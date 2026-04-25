package com.group.smoothtune.adapter.in.rest;

import com.group.smoothtune.adapter.in.rest.dtos.SongResponseDTO;
import com.group.smoothtune.adapter.in.rest.mapper.SongMapper;
import com.group.smoothtune.application.usecase.Song.UploadSongUseCase;
import com.group.smoothtune.domain.model.FileResource;
import com.group.smoothtune.domain.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final UploadSongUseCase uploadSongUseCase;

    public SongController(UploadSongUseCase uploadSongUseCase) {
        this.uploadSongUseCase = uploadSongUseCase;
    }

    @PostMapping("/uploadSong")
    public ResponseEntity<SongResponseDTO> uploadSong(
            @RequestParam("song") MultipartFile song,
            @RequestParam("image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("userId") Long userId,
            @RequestParam("genreId") Long genreId
            ) throws IOException {
        FileResource songFile = new FileResource(song.getBytes(), song.getOriginalFilename(), song.getContentType());
        FileResource imageFile = new FileResource(image.getBytes(), image.getOriginalFilename(), image.getContentType());

        Song resultSong = uploadSongUseCase.execute(
                songFile,
                imageFile,
                title,
                artist,
                userId,
                genreId
        );
        SongResponseDTO response = SongMapper.toResponse(resultSong);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
