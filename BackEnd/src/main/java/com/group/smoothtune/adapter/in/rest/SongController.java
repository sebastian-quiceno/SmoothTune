package com.group.smoothtune.adapter.in.rest;

import com.group.smoothtune.adapter.in.rest.dtos.SongResponseDTO;
import com.group.smoothtune.adapter.in.rest.mapper.SongMapper;
import com.group.smoothtune.application.usecase.Song.GetSongByIdUseCase;
import com.group.smoothtune.application.usecase.Song.UploadSongUseCase;
import com.group.smoothtune.domain.model.FileResource;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.infrastructure.storage.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final UploadSongUseCase uploadSongUseCase;
    private final GetSongByIdUseCase getSongByIdUseCase;
    private final S3Service s3Service;

    public SongController(UploadSongUseCase uploadSongUseCase, GetSongByIdUseCase getSongByIdUseCase, S3Service s3Service) {
        this.uploadSongUseCase = uploadSongUseCase;
        this.getSongByIdUseCase = getSongByIdUseCase;
        this.s3Service = s3Service;
    }

    @PostMapping("/uploadSong")
    public ResponseEntity<SongResponseDTO> uploadSong(
            @RequestParam("song") MultipartFile song,
            @RequestParam("image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("artistId") Long artistId,
            @RequestParam("userId") Long userId,
            @RequestParam("genreId") Long genreId
            ) throws IOException {
        FileResource songFile = new FileResource(song.getBytes(), song.getOriginalFilename(), song.getContentType());
        FileResource imageFile = new FileResource(image.getBytes(), image.getOriginalFilename(), image.getContentType());

        Song resultSong = uploadSongUseCase.execute(
                songFile,
                imageFile,
                title,
                artistId,
                userId,
                genreId
        );
        SongResponseDTO response = SongMapper.toResponse(resultSong);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getSong/{id}")
    public ResponseEntity<SongResponseDTO> getSong(@PathVariable Long id){
        Song song = getSongByIdUseCase.execute(id);
        return ResponseEntity.ok().body(SongMapper.toResponse(song));
    }

    @GetMapping("/getUrl/{id}")
    public ResponseEntity<String> getUrl(@PathVariable Long id){
        Song song = getSongByIdUseCase.execute(id);
        return ResponseEntity.ok().body(s3Service.generatePresignedUrl(song.getAudioPath()));
    }

}
