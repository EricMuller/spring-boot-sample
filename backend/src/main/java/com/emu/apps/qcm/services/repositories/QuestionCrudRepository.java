package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

/**
 * Created by eric on 05/06/2017.
 */
@Repository
public interface QuestionCrudRepository extends CrudRepository<Question, Long> {

    @Query("SELECT q.questions FROM Questionnaire q WHERE q.id  = :questionnaireId ")
    Iterable<Question> findByQuestionnaireId(@Param("questionnaireId") Long questionnaireId);


}
