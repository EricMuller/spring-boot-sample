package com.emu.apps.sample.services.repositories;

import com.emu.apps.sample.model.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by eric on 05/06/2017.
 */
public interface CategoryCrudRepository extends CrudRepository<Category, String> {

}
