package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.model.Question;
import com.emu.apps.qcm.model.Questionnaire;
import com.emu.apps.qcm.rest.dtos.QuestionDto;
import com.emu.apps.qcm.rest.dtos.QuestionnaireDto;
import com.emu.apps.qcm.rest.mappers.QuestionnaireMapper;
import com.emu.apps.qcm.services.QuestionnaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questionnaires")
@Api(value = "questionnaire-store", description = "All operations ", tags = "Questionnaires")
public class QuestionnaireRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionnaireService questionnairesService;

    @Autowired
    private QuestionnaireMapper questionnaireMapper;


    @ApiOperation(value = "Find all questionnaires", responseContainer = "List", response = QuestionDto.class, tags = "Questionnaires", nickname = "getQuestionnaires")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    @ResponseBody
    @PreAuthorize("true")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Iterable<QuestionnaireDto> getQuestionnaires() {
        return questionnaireMapper.modelToDtos(questionnairesService.findAll());
    }

    @ApiOperation(value = "Find a questionnaire by ID", response = Question.class, nickname = "getQuestionnaireById")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public QuestionnaireDto getQuestionnaireById(@PathVariable("id") long id) {
        return questionnaireMapper.modelToDto(questionnairesService.findOne(id));
    }


    @ApiOperation(value = "Save a questionnaire", response = Question.class, nickname = "saveQuestion")
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    @ResponseBody
    public QuestionnaireDto saveQuestionnaire(@RequestBody QuestionnaireDto questionnaireDto) {
        Questionnaire questionnaire = questionnaireMapper.dtoToModel(questionnaireDto);
        return questionnaireMapper.modelToDto(questionnairesService.saveQuestionnaire(questionnaire));
    }

}