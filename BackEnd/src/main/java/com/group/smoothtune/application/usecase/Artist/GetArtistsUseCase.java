package com.group.smoothtune.application.usecase.Artist;

import com.group.smoothtune.domain.model.Artist;
import com.group.smoothtune.domain.port.ArtistRepository;

import java.util.List;

public class GetArtistsUseCase {
    private final ArtistRepository artistRepository;

    public GetArtistsUseCase(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> execute(){
        return artistRepository.findAll();
    }
}
