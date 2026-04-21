package com.group.smoothtune.infrastructure.config;

import com.group.smoothtune.application.usecase.Genre.CreateGenreUseCase;
import com.group.smoothtune.application.usecase.Song.UploadSongUseCase;
import com.group.smoothtune.application.usecase.User.CreateUserUseCase;
import com.group.smoothtune.application.usecase.User.FindUserByEmailUseCase;
import com.group.smoothtune.application.usecase.User.FindUserByUsernameUseCase;
import com.group.smoothtune.application.usecase.auth.SignInUseCase;
import com.group.smoothtune.application.usecase.auth.SignUpUseCase;
import com.group.smoothtune.domain.port.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    //========= USER =========
    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    public FindUserByEmailUseCase findUserByEmailUseCase(UserRepository userRepository) {
        return new FindUserByEmailUseCase(userRepository);
    }

    @Bean
    public FindUserByUsernameUseCase findUserByUsernameUseCase(UserRepository userRepository) {
        return new FindUserByUsernameUseCase(userRepository);
    }

    //========= LOGIN =========
    @Bean
    public SignInUseCase signInUseCase(
            AuthenticatePort authenticatePort,
            TokenPort tokenPort
    ) {
        return new SignInUseCase(authenticatePort, tokenPort);
    }

    @Bean
    public SignUpUseCase signUpUseCase(
            FindUserByEmailUseCase findUserByEmailUseCase,
            FindUserByUsernameUseCase findUserByUsernameUseCase,
            CreateUserUseCase createUserUseCase,
            PasswordEncoderPort passwordEncoderPort,
            TokenPort tokenPort,
            AuthenticatePort authenticatePort
    ) {
        return new SignUpUseCase(
                findUserByEmailUseCase,
                findUserByUsernameUseCase,
                createUserUseCase,
                passwordEncoderPort,
                tokenPort,
                authenticatePort
        );
    }

    //========= SONG =========
    @Bean
    public UploadSongUseCase uploadSongUseCase(
            FileStoragePort fileStoragePort,
            SongRepository songRepository,
            UserRepository userRepository,
            GenreRepository genreRepository
    ) {
        return new UploadSongUseCase(
                fileStoragePort,
                songRepository,
                userRepository,
                genreRepository
        );
    }

    //========= Genre =========
    @Bean
    public CreateGenreUseCase createGenreUseCase(
            GenreRepository genreRepository
    ){
        return new CreateGenreUseCase(genreRepository);
    }


}
