package com.emu.apps.qcm.services;

import com.emu.apps.qcm.model.*;
import com.emu.apps.qcm.services.dtos.*;
import com.emu.apps.qcm.services.mappers.*;
import com.emu.apps.qcm.services.repositories.*;
import com.google.common.collect.*;
import org.apache.commons.io.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

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
        Questionnaire questionnaire = questionnaireMapper.dtoToModel(questionnaireDto);
        return questionnaireMapper.modelToDto(questionnaireCrudRepository.save(questionnaire));
    }


    public void saveQuestionnaire(String fullName, FileQuestionDto[] questions) {

        String name = FilenameUtils.getBaseName(fullName);

        Map<String, Questionnaire> questionnaireMap = Maps.newHashMap();

        for (FileQuestionDto fileQuestionDto : questions) {
            if (StringUtils.isNotEmpty(fileQuestionDto.getCategorie())) {
                Question question = fileQuestionMapper.dtoToModel(fileQuestionDto);
                question.setDate(new Date());

                Category category = categorieRepository.findByLibelle(fileQuestionDto.getCategorie());
                if (category == null) {
                    category = new Category();
                    category.setLibelle(fileQuestionDto.getCategorie());
                    categorieRepository.save(category);
                }

                question.setType(Type.TEXTE_LIBRE);


                Response response = new Response();
                response.setTrue(Boolean.TRUE);
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
