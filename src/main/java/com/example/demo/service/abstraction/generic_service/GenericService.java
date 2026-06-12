package com.example.demo.service.abstraction.generic_service;

import java.util.List;

public interface GenericService <ResponseDTO, RequestDTO, ID>{

    List<ResponseDTO> findAll();

    ResponseDTO findById(ID id);

    ResponseDTO save (RequestDTO requestDTO);

    ResponseDTO update(ID id, RequestDTO requestDTO);

    String deletedById(ID id);
}
