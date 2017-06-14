package com.emu.apps.sample.services;

import com.emu.apps.sample.model.Category;
import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.dtos.CategoryDto;
import com.emu.apps.sample.services.mappers.CategoryMapper;
import com.emu.apps.sample.services.repositories.CategoryCrudRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eric on 14/06/2017.
 */
@Service
public class CategoryService {

    protected final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryDto findById(Long id) {
        Category category = categoryCrudRepository.findOne(id);
        return categoryMapper.modelToDto(category);
    }

    public CategoryDto findByLibelle(String libelle) {
        Category category = categoryCrudRepository.findByLibelle(libelle);
        return categoryMapper.modelToDto(category);
    }

    public Iterable<CategoryDto> findAll() {
        log.info("find All question");
        Iterable<Category> categories = categoryCrudRepository.findAll();
        return categoryMapper.modelsToDtos(categories);
    }

}
