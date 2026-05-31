package com.example.demo.services.ristorante.implementation;

import com.example.demo.dto.ristorante.requestdto.CategoryRequestDTO;
import com.example.demo.dto.ristorante.responsedto.CategoryResponseDTO;
import com.example.demo.entity.ristorante.Category;
import com.example.demo.mapper.ristorante.CategoryMapper;
import com.example.demo.repository.ristorante.CategoryRepository;
import com.example.demo.services.ristorante.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.entityToResponseDTO(categories);
    }

    @Override
    public CategoryResponseDTO findById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not founded with id - " + id));
        return categoryMapper.entityToResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO save(CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryMapper.requestDTOToEntity(categoryRequestDTO);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.entityToResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO update(Integer id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not founded"));

        category.setName(categoryRequestDTO.getName());

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.entityToResponseDTO(updatedCategory);
    }

    @Override
    public String deletedById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category ot founded with id - " + id);
        }
        categoryRepository.deleteById(id);
        return "Deleted category with id - " + id;
    }
}
