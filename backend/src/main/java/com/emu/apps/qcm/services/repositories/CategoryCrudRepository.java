package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by eric on 05/06/2017.
 */
public interface CategoryCrudRepository extends CrudRepository<Category, Long> {

    @Query("SELECT DISTINCT q FROM Category q WHERE q.libelle  = :libelle ")
    public Category findByLibelle(@Param("libelle") String libelle);

}
