package com.emu.apps.sample.services.repositories;

import com.emu.apps.sample.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eric on 05/06/2017.
 */
@Repository
public interface QuestionCrudRepository extends CrudRepository<Question, Long> {

    // @Query("select p from #{#entityName} p where ?1 member of  p.categories")
    // Iterable<Question> findByCategory(String category);

}
