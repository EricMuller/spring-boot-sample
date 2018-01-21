package com.emu.apps.qcm.services;

import com.emu.apps.qcm.model.*;
import com.emu.apps.qcm.services.dtos.FileQuestionDto;
import com.emu.apps.qcm.services.dtos.QuestionnaireDto;
import com.emu.apps.qcm.services.mappers.FileQuestionMapper;
import com.emu.apps.qcm.services.mappers.QuestionnaireMapper;
import com.emu.apps.qcm.services.repositories.CategoryCrudRepository;
import com.emu.apps.qcm.services.repositories.QuestionnaireCrudRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

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


    public QuestionnaireDto saveQuestionnaire(QuestionnaireDto questionnaireDto) {


        Questionnaire questionnaire = questionnaireDto.getId() != null ? questionnaireCrudRepository.findOne(questionnaireDto.getId()) : null;
        if (questionnaireDto.getId() != null) {
            questionnaireMapper.updateQuestionnaire(questionnaire, questionnaireDto);
        } else {
            questionnaire = questionnaireMapper.dtoToModel(questionnaireDto);
        }

        if (questionnaireDto.getCategory() != null) {
            Category category = categorieRepository.findOne(Long.valueOf(questionnaireDto.getCategory().getId()));
            questionnaire.setCategory(category);
        }

        return questionnaireMapper.modelToDto(questionnaireCrudRepository.save(questionnaire));
    }


    public void saveQuestionnaire(String fullName, FileQuestionDto[] questions) {

        String name = FilenameUtils.getBaseName(fullName);

        Map<String, Questionnaire> questionnaireMap = Maps.newHashMap();
        Map<String, Long> categoryNumberMap = Maps.newHashMap();

        for (FileQuestionDto fileQuestionDto : questions) {
            if (StringUtils.isNotEmpty(fileQuestionDto.getCategorie())) {
                Question question = fileQuestionMapper.dtoToModel(fileQuestionDto);
                question.setDate(new Date());

                Category category = categorieRepository.findByLibelle(fileQuestionDto.getCategorie());
                if (category == null) {
                    category = new Category();
                    category.setLibelle(fileQuestionDto.getCategorie());
                    categorieRepository.save(category);
                    categoryNumberMap.put(category.getLibelle(), Long.valueOf(1));
                } else {
                    Long aLong = categoryNumberMap.get(category.getLibelle());
                    categoryNumberMap.put(category.getLibelle(), ++aLong);
                }

                question.setType(Type.FREE_TEXT);
                question.setNumber(categoryNumberMap.get(category.getLibelle()));

                Response response = new Response();
                response.setResponse(fileQuestionDto.getResponse());

                question.setResponses(Lists.newArrayList(response));

                Questionnaire questionnaire = questionnaireMap.get(fileQuestionDto.getCategorie());
                if (questionnaire == null) {
                    questionnaire = new Questionnaire(name + "-" + fileQuestionDto.getCategorie());
                    questionnaire.setQuestions(Lists.newArrayList());
                    questionnaire.setCategory(category);
                    questionnaireMap.put(fileQuestionDto.getCategorie(), questionnaire);
                }
                question.setQuestionnaire(questionnaire);
                questionnaire.getQuestions().add(question);

            }
        }

        questionnaireMap.forEach((k, v) -> questionnaireCrudRepository.save(v));

    }


}
