package com.emu.apps.qcm.services;

import com.emu.apps.qcm.services.dtos.CategoryDto;
import com.emu.apps.qcm.services.mappers.CategoryMapper;
import com.emu.apps.qcm.services.repositories.CategoryCrudRepository;
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
        return categoryMapper.modelToDto(categoryCrudRepository.findOne(id));
    }

    public CategoryDto findByLibelle(String libelle) {
        return categoryMapper.modelToDto(categoryCrudRepository.findByLibelle(libelle));
    }

    public Iterable<CategoryDto> findAll() {
        return categoryMapper.modelsToDtos(categoryCrudRepository.findAll());
    }

}
