package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.Question;
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

    @Query("SELECT q FROM Question q WHERE q.category.id  = :categorieId ")
    public List<Question> findByCategoryId(@Param("categorieId") Long categorieId);

    @Query("SELECT q.questions FROM Questionnaire q WHERE q.id  = :questionnaireId ")
    public List<Question> findByQuestionnaireId(@Param("questionnaireId") Long questionnaireId);

    @Query("SELECT q FROM Question q WHERE q.questionnaire.id  = :questionnaireId and q.category.id  = :categorieId ")
    public List<Question> findByQuestionnaireIdAndCategoryId(@Param("questionnaireId") Long questionnaireId, @Param("categorieId") Long categorieId);

}
