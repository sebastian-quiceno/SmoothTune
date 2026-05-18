package com.group.smoothtune.adapter.in.rest;

import com.group.smoothtune.adapter.in.rest.dtos.response.SongResponseDTO;
import com.group.smoothtune.adapter.in.rest.mapper.SongMapper;
import com.group.smoothtune.application.usecase.Song.GetSongByIdUseCase;
import com.group.smoothtune.application.usecase.Song.GetSongsByUploaderId;
import com.group.smoothtune.application.usecase.Song.GetSongsUseCase;
import com.group.smoothtune.application.usecase.Song.UploadSongUseCase;
import com.group.smoothtune.domain.model.FileResource;
import com.group.smoothtune.domain.model.Song;
import com.group.smoothtune.infrastructure.storage.S3Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final UploadSongUseCase uploadSongUseCase;
    private final GetSongByIdUseCase getSongByIdUseCase;
    private final GetSongsUseCase getSongsUseCase;
    private final GetSongsByUploaderId getSongsByUploaderId;
    private final S3Service s3Service;

    public SongController(UploadSongUseCase uploadSongUseCase, GetSongByIdUseCase getSongByIdUseCase, GetSongsUseCase getSongsUseCase, GetSongsByUploaderId getSongsByUploaderId, S3Service s3Service) {
        this.uploadSongUseCase = uploadSongUseCase;
        this.getSongByIdUseCase = getSongByIdUseCase;
        this.getSongsUseCase = getSongsUseCase;
        this.getSongsByUploaderId = getSongsByUploaderId;
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

    @GetMapping("/getAudioUrl/{id}")
    public ResponseEntity<String> getAudioUrl(@PathVariable Long id){
        Song song = getSongByIdUseCase.execute(id);
        return ResponseEntity.ok().body(s3Service.generatePresignedUrl(song.getAudioPath()));
    }

    @GetMapping("/getImageUrl/{id}")
    public ResponseEntity<String> getImageUrl(@PathVariable Long id){
        Song song = getSongByIdUseCase.execute(id);
        return ResponseEntity.ok().body(s3Service.generatePresignedUrl(song.getImagePath()));
    }

    @GetMapping("/getSongsUploadedByUserId/{userId}")
    public ResponseEntity<List<SongResponseDTO>> getSongsUploadedByUserId(@PathVariable Long userId){
        List<Song> songs = getSongsByUploaderId.execute(userId);
        return ResponseEntity.ok().body(
                songs.
                stream().
                map(SongMapper::toResponse).
                toList()
        );
    }

    @GetMapping("/getSongs")
    public ResponseEntity<Page<SongResponseDTO>> getSongs(@PageableDefault(page= 0, size = 10)Pageable pageable){
        Page<Song> page = getSongsUseCase.execute(pageable);
        return ResponseEntity.ok().body(page.map(SongMapper::toResponse));
    }

}
