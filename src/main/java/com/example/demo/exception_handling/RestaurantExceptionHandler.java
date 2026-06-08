package com.example.demo.exception_handling;

import com.example.demo.controller.ristorante.ChefController;
import com.example.demo.controller.ristorante.CustomerController;
import com.example.demo.controller.ristorante.DishController;
import com.example.demo.dto.ristorante.responsedto.DishResponseDTO;
import com.example.demo.dto.ristorante.responsedto.DishResponseWrapperDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice(assignableTypes = {
        DishController.class,
        ChefController.class,
        CustomerController.class
})
public class RestaurantExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<DishResponseWrapperDTO> notFoundHandler(NoSuchElementException e){
        System.out.println(e.getMessage());
        DishResponseDTO res = new DishResponseDTO();
        res.setEsito(false);
        res.setErrorMessages(List.of(e.getMessage()));
        res.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
