package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.Question;
import com.emu.apps.qcm.services.projections.QuestionProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eric on 05/06/2017.
 */
@Repository
public interface QuestionCrudRepository extends CrudRepository<Question, Long> {

    //@Query("SELECT q.questions FROM Questionnaire q WHERE q.id  = :questionnaireId ") @Param("questionnaireId")
    Iterable<QuestionProjection> findByQuestionnaireId(Long questionnaireId);


}
