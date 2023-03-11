package com.example.app.errors;

import com.example.app.dto.responses.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.app.errors.ExceptionMessage.INVALID_INPUT;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage handleResponseStatusException(
            ResponseStatusException     ex,
            WebRequest                  request
    ) {

        return ErrorMessage.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .timeStamp(new Date())
                .message(ex.getReason())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleUsernameNotFoundException(
            UsernameNotFoundException   ex,
            WebRequest                  request
    ) {

        return ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timeStamp(new Date())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleNotArgumentValidException(
            MethodArgumentNotValidException ex,
            WebRequest                      request
    ) {

        List<String> errorMessageKeys = INVALID_INPUT.stream()
                .filter(msg -> ex.getMessage().contains(msg))
                .toList();

        String errorMessages = errorMessageKeys.stream()
                .map(key -> messageSource.getMessage(
                        key,
                        null,
                        Locale.getDefault())
                )
                .collect(Collectors.joining(System.lineSeparator()));

        return ErrorMessage.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(new Date())
                .message(errorMessages)
                .description(request.getDescription(false))
                .build();
    }

//    @ExceptionHandler(InvalidTokenException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleInvalidVerificationToken(
//            InvalidTokenException   ex,
//            WebRequest              request
//    ) {
//
//        return ErrorMessage.builder()
//                .statusCode(HttpStatus.BAD_REQUEST.value())
//                .timeStamp(new Date())
//                .message(ex.getMessage())
//                .description(request.getDescription(false))
//                .build();
//    }
}
