package com.example.demo.services.libreria;

import com.example.demo.dto.libreria.AuthorDTO;
import com.example.demo.entity.libreria.Author;
import com.example.demo.mapper.libreria.AuthorMapper;
import com.example.demo.repository.libreria.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.entityToDTO(authors);
    }

    @Override
    public AuthorDTO findById(Integer id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Author not foundend with id - " + id)
                );
        return authorMapper.entityToDTO(author);
    }

    @Override
    public AuthorDTO save(AuthorDTO authorDTO) {
        Author author = authorMapper.dtoToEntity(authorDTO);

        Author savedAuthor = authorRepository.save(author);

        return authorMapper.entityToDTO(savedAuthor);
    }

    @Override
    public void deletedById(Integer id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("AAuthor not founded, with id - " + id);
        }
        authorRepository.deleteById(id);
    }
}
