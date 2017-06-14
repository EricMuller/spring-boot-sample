package com.emu.apps.sample.services.repositories;

import com.emu.apps.sample.model.Category;
import com.emu.apps.sample.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by eric on 05/06/2017.
 */
public interface CategoryCrudRepository extends CrudRepository<Category, Long> {

    @Query("SELECT DISTINCT q FROM Category q WHERE q.libelle  = :libelle ")
    public Category findByLibelle(@Param("libelle") String libelle);

}
