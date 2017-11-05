package com.emu.apps.qcm.services;

import com.emu.apps.qcm.model.Category;
import com.emu.apps.qcm.model.Question;
import com.emu.apps.qcm.model.Questionnaire;
import com.emu.apps.qcm.model.Response;
import com.emu.apps.qcm.services.dtos.FileQuestionDto;
import com.emu.apps.qcm.services.dtos.QuestionnaireDto;
import com.emu.apps.qcm.services.mappers.FileQuestionMapper;
import com.emu.apps.qcm.services.mappers.QuestionnaireMapper;
import com.emu.apps.qcm.services.repositories.CategoryCrudRepository;
import com.emu.apps.qcm.services.repositories.QuestionnaireCrudRepository;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional()
public class QuestionnaireService {

    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionnaireCrudRepository questionnaireCrudRepository;

    @Autowired
    private CategoryCrudRepository categorieRepository;

    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    @Autowired
    private FileQuestionMapper fileQuestionMapper;

    public QuestionnaireDto findOne(long id) {
        return questionnaireMapper.modelToDto(questionnaireCrudRepository.findOne(id));
    }

    public Iterable<QuestionnaireDto> findAll() {
        return questionnaireMapper.modelToDtos(questionnaireCrudRepository.findAll());
    }

    public void saveQuestionnaire(String name, FileQuestionDto[] questions) {

        Questionnaire questionnaire = new Questionnaire(name);

        List<Question> questionList = Lists.newArrayList();
        for (FileQuestionDto fileQuestionDto : questions) {
            Question question = fileQuestionMapper.dtoToModel(fileQuestionDto);
            question.setDate(new Date());
            if (StringUtils.isNotEmpty(fileQuestionDto.getCategorie())) {
                Category category = categorieRepository.findByLibelle(fileQuestionDto.getCategorie());
                if (category == null) {
                    category = new Category();
                    category.setLibelle(fileQuestionDto.getCategorie());
                    categorieRepository.save(category);
                }
                question.setCategory(category);
            }

            Response response = new Response();
            response.setTrue(Boolean.TRUE);
            response.setResponse(fileQuestionDto.getResponse());

            question.setResponses(Lists.newArrayList(response));
            questionList.add(question);
            question.setQuestionnaire(questionnaire);
        }
        questionnaire.setQuestions(questionList);
        questionnaireCrudRepository.save(questionnaire);
    }


}
