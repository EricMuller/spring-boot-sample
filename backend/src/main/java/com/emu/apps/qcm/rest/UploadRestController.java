package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.model.*;
import com.emu.apps.qcm.rest.dtos.FileQuestionDto;
import com.emu.apps.qcm.rest.dtos.MessageDto;
import com.emu.apps.qcm.services.CategoryService;
import com.emu.apps.qcm.services.QuestionnaireService;
import com.emu.apps.qcm.services.mappers.FileQuestionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/upload")
@Api(value = "upload-store", description = "All operations ", tags = "upload")
public class UploadRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionnaireService questionnairesService;

    @Autowired
    private FileQuestionMapper fileQuestionMapper;

    @Autowired
    private CategoryService categoryService;


    @ApiOperation(value = "upload a questionnaire json file", responseContainer = "ResponseEntity", response = MessageDto.class, tags = "Questionnaires", nickname = "uploadFile")
    @ResponseBody
    @Secured("ROLE_USER")
    @RequestMapping(value = "/questionnaire", method = RequestMethod.POST, headers = "Content-Type= multipart/form-data", produces = "application/json")
    public ResponseEntity<?> uploadQuestionFile(@RequestParam("file") MultipartFile file) throws IOException {

        try {
            logger.info(file.getOriginalFilename());
            ObjectMapper objectMapper = new ObjectMapper();
            final FileQuestionDto[] questions = objectMapper.readValue(file.getInputStream(), FileQuestionDto[].class);

            String name = FilenameUtils.getBaseName(file.getOriginalFilename());
            Map<String, Questionnaire> questionnaireMap = Maps.newHashMap();
            Map<String, Long> categoryNumberMap = Maps.newHashMap();

            for (FileQuestionDto fileQuestionDto : questions) {
                if (StringUtils.isNotEmpty(fileQuestionDto.getCategorie())) {
                    Question question = fileQuestionMapper.dtoToModel(fileQuestionDto);

                    Category category = categoryService.findByLibelle(fileQuestionDto.getCategorie());
                    if (category == null) {
                        category = new Category();
                        category.setLibelle(fileQuestionDto.getCategorie());
                        categoryService.save(category);
                        categoryNumberMap.put(category.getLibelle(), Long.valueOf(1));
                    } else {
                        Long aLong = categoryNumberMap.containsKey(category.getLibelle()) ? categoryNumberMap.get(category.getLibelle()) : Long.valueOf(0);
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
            questionnairesService.saveQuestionnaire(questionnaireMap.values());
        } catch (Exception e) {
            logger.error("err", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessageDto("Ok"), HttpStatus.OK);
    }

}
