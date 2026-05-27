package com.example.demo.services.libreria;

import com.example.demo.dto.libreria.AuthorDTO;
import com.example.demo.dto.libreria.GenreDTO;
import com.example.demo.entity.libreria.Genre;
import com.example.demo.mapper.libreria.GenreMapper;
import com.example.demo.repository.libreria.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    @Override
    public List<GenreDTO> findAll() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.entityToDTO(genres);
    }

    @Override
    public GenreDTO findById(Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not founded with id - " + id));
        return genreMapper.entityToDTO(genre);
    }

    @Override
    public GenreDTO save(GenreDTO genreDTO) {
        Genre genre = genreMapper.dtoToEntity(genreDTO);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.entityToDTO(savedGenre);
    }

    @Override
    public void deletedById(Integer id) {

    }
}
