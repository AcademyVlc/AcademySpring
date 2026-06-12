package com.example.demo.exception_handling.palestra;

import com.example.demo.controller.palestra.CourseController;
import com.example.demo.controller.palestra.CustomerController;
import com.example.demo.controller.palestra.TrainerController;
import com.example.demo.dto.response.ErrorResponseDTO;
import com.example.demo.exception_handling.palestra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice(assignableTypes = {CustomerController.class, CourseController.class, TrainerController.class})
public class PalestraExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundHandler(NoSuchElementException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> customerNotFound(CustomerNotFoundException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> courseNotFound(CourseNotFoundException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> roomNotFound(RoomNotFoundException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }

    @ExceptionHandler(CourseHasSubscribersException.class)
    public ResponseEntity<ErrorResponseDTO> courseHasSubscribers(CourseHasSubscribersException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.BAD_REQUEST); // La richiesta del client è sbagliata o contiene dati non validi.

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }


    @ExceptionHandler(RoomFullException.class)
    public ResponseEntity<ErrorResponseDTO> roomFullException(RoomFullException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.CONFLICT); // La richiesta è sintatticamente corretta, ma non può essere eseguita a causa dello stato attuale della risorsa.

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> roomFullException(SubscriptionNotFoundException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.CONFLICT); // La richiesta è sintatticamente corretta, ma non può essere eseguita a causa dello stato attuale della risorsa.

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }

    @ExceptionHandler(TrainerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> roomFullException(TrainerNotFoundException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.CONFLICT); // La richiesta è sintatticamente corretta, ma non può essere eseguita a causa dello stato attuale della risorsa.

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

}
