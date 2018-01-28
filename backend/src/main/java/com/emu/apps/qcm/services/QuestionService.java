package com.emu.apps.qcm.services;

import com.emu.apps.qcm.metrics.Timer;
import com.emu.apps.qcm.model.Question;
import com.emu.apps.qcm.services.projections.QuestionProjection;
import com.emu.apps.qcm.services.repositories.QuestionJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by eric on 05/06/2017.
 */
@Service
public class QuestionService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionJpaRepository questionRepository;

    public Question findOne(Long id) {
        return questionRepository.findOne(id);
    }

    @Transactional()
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Timer
    public Iterable<QuestionProjection> findByQuestionnaireId(Long questionnaireId) {
        return questionRepository.findByQuestionnaireId(questionnaireId);
    }

    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }


}
