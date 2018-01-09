package com.emu.apps.qcm.services;

import com.emu.apps.qcm.model.*;
import com.emu.apps.qcm.services.dtos.*;
import com.emu.apps.qcm.services.mappers.*;
import com.emu.apps.qcm.services.repositories.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

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

    public Iterable<QuestionDto> findByQuestionnaireId(Long questionnaireId) {
        return questionMapper.modelToDtos(questionRepository.findByQuestionnaireId(questionnaireId));
    }

    public Iterable<QuestionDto> findAll() {
        return questionMapper.modelToDtos(questionRepository.findAll());
    }


}
