package com.group.smoothtune.adapter.in.rest;

import com.group.smoothtune.adapter.in.rest.dtos.ArtistRequestDTO;
import com.group.smoothtune.adapter.in.rest.dtos.ArtistResponseDTO;
import com.group.smoothtune.adapter.in.rest.mapper.ArtistMapper;
import com.group.smoothtune.application.usecase.Artist.CreateArtistUseCase;
import com.group.smoothtune.application.usecase.Artist.GetArtistsUseCase;
import com.group.smoothtune.domain.model.Artist;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    private final CreateArtistUseCase createArtistUseCase;
    private final GetArtistsUseCase getArtistsUseCase;

    public ArtistController(CreateArtistUseCase createArtistUseCase, GetArtistsUseCase getArtistsUseCase) {
        this.createArtistUseCase = createArtistUseCase;
        this.getArtistsUseCase = getArtistsUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<ArtistResponseDTO> createArtist(@RequestBody ArtistRequestDTO dto){
        Artist resultArtist = createArtistUseCase.execute(dto.getName(), dto.getBiography());
        return ResponseEntity.status(HttpStatus.CREATED).body(ArtistMapper.toRespose(resultArtist));
    }

    @GetMapping("/getArtists")
    public ResponseEntity<List<ArtistResponseDTO>> getArtists(){
        List<ArtistResponseDTO> artists = getArtistsUseCase.execute().stream().map(ArtistMapper::toRespose).toList();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(artists);
    }
}
