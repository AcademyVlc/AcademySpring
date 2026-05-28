package com.example.demo.services.libreria;

import com.example.demo.dto.libreria.BookDTO;
import com.example.demo.dto.libreria.OrderDTO;
import com.example.demo.entity.libreria.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderService{
    // JPA implementa i metodi crud da solo

    List<OrderDTO> findAll();

    OrderDTO findById(Integer id);

    OrderDTO save(OrderDTO orderDTO);

    void deletedById(Integer id);
}
