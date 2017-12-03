package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by eric on 05/06/2017.
 */
@Repository
public interface QuestionCrudRepository extends CrudRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.category.id  = :categorieId ")
    List<Question> findByCategoryId(@Param("categorieId") Long categorieId);

    @Query("SELECT q.questions FROM Questionnaire q WHERE q.id  = :questionnaireId ")
    List<Question> findByQuestionnaireId(@Param("questionnaireId") Long questionnaireId);

    @Query("SELECT q FROM Question q WHERE q.questionnaire.id  = :questionnaireId and q.category.id  = :categorieId ")
    List<Question> findByQuestionnaireIdAndCategoryId(@Param("questionnaireId") Long questionnaireId, @Param("categorieId") Long categorieId);

}
