package com.group.smoothtune.infrastructure.exception;

import com.group.smoothtune.adapter.in.rest.dtos.ErrorResponse;
import com.group.smoothtune.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
        return switch (ex) {
            case AccessDeniedException e ->
                    ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));

            case UserNotFoundException e ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
            case GenreNotFoundException e ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
            case PlaylistNotFoundException e ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
            case PlaylistUserSongNotFoundException e ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
            case SongNotFoundException e ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
            case UserSongNotFoundException e ->
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));

            case InvalidAudioException e ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
            case InvalidImageException e ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));

            case EmailAlreadyExistException e ->
                    ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
            case SongAlreadySaveException e ->
                    ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
            case UsernameAlreadyExistException e ->
                    ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));

            case ErrorWhileGettingDurationException e ->
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));

            default ->
                    ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse(ex.getMessage()));
        };
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
    }
}
