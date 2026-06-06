package com.example.demo.exception_handling.palestra;

import com.example.demo.controller.palestra.CourseController;
import com.example.demo.controller.palestra.CustomerController;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.CourseResponseWrapperDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.dto.response.CustomerResponseWrapperDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = {CustomerController.class, CourseController.class})
public class PalestraExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomerResponseWrapperDTO> notFoundHandlerCustomer(NoSuchElementException e){
        System.out.println(e.getMessage());
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setEsito(false);
        customerResponseDTO.setErrorMessages(List.of(e.getMessage()));
        customerResponseDTO.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(customerResponseDTO.getStatus()).body(customerResponseDTO);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CourseResponseWrapperDTO> notFoundHandlerCourse(NoSuchElementException e){
        System.out.println(e.getMessage());
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setSuccess(false);
        courseResponseDTO.setErrorMessages(List.of(e.getMessage()));
        courseResponseDTO.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(courseResponseDTO.getStatus()).body(courseResponseDTO);
    }
}
