package com.example.demo.controller.palestra;

import com.example.demo.dto.request.CustomerRequestDTO;
import com.example.demo.dto.response.CourseResponseDTO;
import com.example.demo.dto.response.CustomerResponseDTO;
import com.example.demo.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        List<CustomerResponseDTO> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable Integer id) {

        CustomerResponseDTO customer = customerService.findById(id);
        customer.setSuccess(true);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO savedCustomer = customerService.save(customerRequestDTO);
        return ResponseEntity.ok(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable Integer id, @RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO updatedCustomer = customerService.update(id, customerRequestDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedByID(@PathVariable Integer id){
        customerService.deletedById(id);
        return ResponseEntity.noContent().build();
    }

    // Non ti serve il CustomerRequestDTO!!! I dati arrivano già dalla URL
    @PostMapping("/{customerId}/courses/{courseId}")
    public ResponseEntity<CustomerResponseDTO> subscribeCustomerToCourse(@PathVariable Integer customerId, @PathVariable Integer courseId){
        CustomerResponseDTO customerResponseDTO = customerService.subscribeCustomerToCourse(customerId, courseId);
        return ResponseEntity.ok(customerResponseDTO);
    }

    // Togliere l'iscrizione ad un corso da parte di un cliente
    @DeleteMapping("/{customerId}/courses/{courseId}")
    public ResponseEntity<Void> unsubscribeCustomerFromCourse(@PathVariable Integer customerId, @PathVariable Integer courseId){
        customerService.unsubscribeCustomerFromCourse(customerId, courseId);
        return ResponseEntity.noContent().build();
    }

    // Vedere tutti i corsi a cui è iscritto un customer
    @GetMapping("/{customerId}/courses")
    public ResponseEntity<List<CourseResponseDTO>> seeAllCoursesOfCustomer(@PathVariable Integer customerId){
        List<CourseResponseDTO> courseResponseDTOS = customerService.seeAllCoursesOfCustomer(customerId);
        return ResponseEntity.ok(courseResponseDTOS);
    }



}
