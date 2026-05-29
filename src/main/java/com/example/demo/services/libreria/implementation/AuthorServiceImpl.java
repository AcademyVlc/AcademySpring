package com.example.demo.services.libreria.implementation;

import com.example.demo.dto.libreria.AuthorDTO;
import com.example.demo.dto.libreria.requestdto.AuthorRequestDTO;
import com.example.demo.dto.libreria.responsedto.AuthorResponseDTO;
import com.example.demo.entity.libreria.Author;
import com.example.demo.mapper.libreria.AuthorMapper;
import com.example.demo.repository.libreria.AuthorRepository;
import com.example.demo.services.libreria.abstraction.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    @Override
    public List<AuthorResponseDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.entityToResponseDTO(authors);
    }

    @Override
    public AuthorResponseDTO findById(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Author not foundend with id - " + id)
                );
        return authorMapper.entityToResponseDTO(author);
    }

    @Override
    public AuthorResponseDTO save(AuthorRequestDTO authorRequestDTO) {
        Author author = authorMapper.requestDTOToEntity(authorRequestDTO);

        Author savedAuthor = authorRepository.save(author);

        return authorMapper.entityToResponseDTO(savedAuthor);
    }

    @Override
    public void deletedById(Integer id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("AAuthor not founded, with id - " + id);
        }
        authorRepository.deleteById(id);
    }
}
