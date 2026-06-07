package com.example.demo.exception_handling.palestra;

import com.example.demo.controller.palestra.CourseController;
import com.example.demo.controller.palestra.CustomerController;
import com.example.demo.controller.palestra.TrainerController;
import com.example.demo.dto.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = {CustomerController.class, CourseController.class, TrainerController.class})
public class PalestraExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDTO> notFoundHandlerCustomer(NoSuchElementException e) {

        System.out.println(e.getMessage());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess(false);
        errorResponseDTO.setErrorMessages(List.of(e.getMessage()));
        errorResponseDTO.setStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity
                .status(errorResponseDTO.getStatus())
                .body(errorResponseDTO);
    }


}
