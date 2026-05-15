package com.group.smoothtune.application.usecase.Artist;

import com.group.smoothtune.domain.model.Artist;
import com.group.smoothtune.domain.port.ArtistRepository;

public class CreateArtistUseCase {
    private final ArtistRepository artistRepository;

    public CreateArtistUseCase(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist execute(String name, String description){
        Artist artist = new Artist(name, description);
        return artistRepository.save(artist);
    }

}
