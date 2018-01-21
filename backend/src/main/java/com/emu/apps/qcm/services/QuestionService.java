package com.emu.apps.qcm.services;

import com.emu.apps.qcm.metrics.Timer;
import com.emu.apps.qcm.model.Question;
import com.emu.apps.qcm.services.dtos.QuestionDto;
import com.emu.apps.qcm.services.mappers.QuestionMapper;
import com.emu.apps.qcm.services.repositories.CategoryCrudRepository;
import com.emu.apps.qcm.services.repositories.QuestionCrudRepository;
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
    private QuestionCrudRepository questionRepository;

    @Autowired
    private CategoryCrudRepository categorieRepository;

    @Autowired
    private QuestionMapper questionMapper;

    public QuestionDto findOne(Long id) {
        return questionMapper.modelToDto(questionRepository.findOne(id));
    }

    @Transactional()
    public QuestionDto saveQuestion(QuestionDto questionDto) {
        Question question = questionMapper.dtoToModel(questionDto);
        return questionMapper.modelToDto(questionRepository.save(question));
    }

    @Timer
    public Iterable<QuestionDto> findByQuestionnaireId(Long questionnaireId) {
        return questionMapper.projectionsToDtos(questionRepository.findByQuestionnaireId(questionnaireId));
    }

    public Iterable<QuestionDto> findAll() {
        return questionMapper.modelToDtos(questionRepository.findAll());
    }


}
