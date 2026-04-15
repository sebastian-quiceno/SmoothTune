package com.group.smoothtune.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {

//    private final UploadSongUseCase uploadSongUseCase;
//    private final DeleteSongUseCase deleteSongUseCase;
//
//    @PostMapping("/upload")
//    public ResponseEntity<SongEntity> uploadSong(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam String title,
//            @RequestParam String artist
//    ) {
//        return ResponseEntity.ok(
//                uploadSongUseCase.execute(file, title, artist)
//        );
//    }
//
//    @DeleteMapping("/{songId}/user/{userId}")
//    public ResponseEntity<Void> deleteSong(@PathVariable Long songId, @PathVariable Long userId) {
//        deleteSongUseCase.execute(songId, userId);
//        return ResponseEntity.noContent().build();
//    }
}
