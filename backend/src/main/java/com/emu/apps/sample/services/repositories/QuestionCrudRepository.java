package com.emu.apps.sample.services.repositories;

import com.emu.apps.sample.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eric on 05/06/2017.
 */
@Repository
public interface QuestionCrudRepository extends CrudRepository<Question, Long> {

    // @Query("select p from #{#entityName} p where ?1 member of  p.categories")
    // Iterable<Question> findByCategory(String category);

    @Query("SELECT q FROM Question q WHERE q.category.id  = :id ")
    public List<Question> findByCategoryId(@Param("id") String id);

}
