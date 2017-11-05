package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.model.Question;
import com.emu.apps.qcm.services.QuestionService;
import com.emu.apps.qcm.services.dtos.MessageDto;
import com.emu.apps.qcm.services.dtos.QuestionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by eric on 05/06/2017.
 */
@RestController
@RequestMapping("api/questions")
@Api(value = "questions-store", description = "All operations ", tags = "Questions")
public class QuestionRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionService questionService;

    @ApiOperation(value = "Find a question by ID", response = Question.class)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public QuestionDto getQuestion(@PathVariable("id") long id) {
        return questionService.findOne(id);
    }

    @ApiOperation(value = "Find all questions", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("true")
    public Iterable<QuestionDto> getQuestions(@RequestParam(value = "questionnaireId", required = false) Long questionnaireId,
                                              @RequestParam(value = "categorieId", required = false) Long categorieId) {
        if (questionnaireId != null) {
            if (categorieId != null) {
                return questionService.findByQuestionnaireIdAndCategoryId(questionnaireId, categorieId);
            } else {
                return questionService.findByQuestionnaireId(questionnaireId);
            }
        }
        return questionService.findAll();
    }

    @ApiOperation(value = "Update a question", response = Question.class)
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public QuestionDto updateQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.updateQuestion(questionDto);
    }

    @ApiOperation(value = "Save a question", response = Question.class)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public QuestionDto saveQuestion(@RequestBody QuestionDto questionDto) {

        return questionService.saveQuestion(questionDto);
    }

    @ExceptionHandler({JsonProcessingException.class, IOException.class})
    public ResponseEntity<?> handleAllException(Exception e) throws IOException {
        return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}