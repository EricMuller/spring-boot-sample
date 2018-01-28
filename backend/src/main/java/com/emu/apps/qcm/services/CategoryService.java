package com.emu.apps.qcm.services;

import com.emu.apps.qcm.model.Category;
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

    public Category save(Category category) {
        return categoryCrudRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryCrudRepository.findOne(id);
    }

    public Category findByLibelle(String libelle) {
        return categoryCrudRepository.findByLibelle(libelle);
    }

    public Iterable<Category> findAll() {
        return categoryCrudRepository.findAll();
    }

}
