package com.example.demo.services.libreria.implementation;

import com.example.demo.dto.libreria.GenreDTO;
import com.example.demo.dto.libreria.requestdto.GenreRequestDTO;
import com.example.demo.dto.libreria.responsedto.GenreResponseDTO;
import com.example.demo.entity.libreria.Genre;
import com.example.demo.mapper.libreria.GenreMapper;
import com.example.demo.repository.libreria.GenreRepository;
import com.example.demo.services.libreria.abstraction.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreResponseDTO> findAll() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.entityToResponseDTO(genres);
    }

    @Override
    public GenreResponseDTO findById(Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not founded with id - " + id));
        return genreMapper.entityToResponseDTO(genre);
    }

    @Override
    public GenreResponseDTO save(GenreRequestDTO genreRequestDTO) {
        Genre genre = genreMapper.requestDTOToEntity(genreRequestDTO);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.entityToResponseDTO(savedGenre);
    }

    @Override
    public void deletedById(Integer id) {
        if (!genreRepository.existsById(id)) {
            throw new RuntimeException("Genre not founded, with id - " + id);
        }
        genreRepository.deleteById(id);
    }
}
