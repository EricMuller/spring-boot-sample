package com.emu.apps.qcm.services;

import com.emu.apps.qcm.model.Questionnaire;
import com.emu.apps.qcm.services.repositories.QuestionnaireJpaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
public class QuestionnaireService {

    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionnaireJpaRepository questionnaireJpaRepository;

    public Questionnaire findOne(long id) {
        return questionnaireJpaRepository.findOne(id);
    }

    public Iterable<Questionnaire> findAll() {
        return questionnaireJpaRepository.findAll();
    }

    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireJpaRepository.save(questionnaire);
    }

    public void saveQuestionnaire(Iterable<Questionnaire> questionnaires) {
        questionnaireJpaRepository.save(questionnaires);
    }

}
