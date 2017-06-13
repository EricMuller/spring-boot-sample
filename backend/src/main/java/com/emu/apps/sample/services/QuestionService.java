package com.emu.apps.sample.services;

import com.emu.apps.sample.model.Category;
import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.dtos.FileQuestionJson;
import com.emu.apps.sample.services.dtos.QuestionDto;
import com.emu.apps.sample.services.mappers.FileQuestionMapper;
import com.emu.apps.sample.services.mappers.QuestionMapper;
import com.emu.apps.sample.services.repositories.CategoryCrudRepository;
import com.emu.apps.sample.services.repositories.QuestionCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by eric on 05/06/2017.
 */
@Service
public class QuestionService {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionCrudRepository questionRepository;

    @Autowired
    private CategoryCrudRepository categorieRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private FileQuestionMapper fileQuestionMapper;


    public QuestionDto findOne(Long id) {
        log.info("findOne");
        Question question = questionRepository.findOne(id);
        return questionMapper.modelToDto(question);
    }

    public Iterable<QuestionDto> findAll() {
        log.info("find All question");
        Iterable<Question> questions = questionRepository.findAll();
        return questionMapper.modelToDtos(questions);
    }

    public void save(FileQuestionJson fileQuestionJson) {
        Question question = fileQuestionMapper.dtoToModel(fileQuestionJson);
        question.setDate(new Date());
        questionRepository.save(question);
    }

    @Transactional()
    public void init() {
        questionRepository.deleteAll();
        Category category = categorieRepository.save(new Category("CAT1", "Categorie1"));
        for (int i = 0; i < 1000; i++) {
            Question question = new Question("question" + String.valueOf(i), "reponse" + String.valueOf(i), new Date());
            question.setCategory(category);
            questionRepository.save(question);
        }

    }

}
