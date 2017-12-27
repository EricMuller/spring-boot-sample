package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.model.*;
import com.emu.apps.qcm.services.*;
import com.emu.apps.qcm.services.dtos.*;
import com.fasterxml.jackson.core.*;
import io.swagger.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * Created by eric on 05/06/2017.
 */
@RestController
@RequestMapping("/api/v1/questions")
@Api(value = "questions-store", description = "All operations ", tags = "Questions")
public class QuestionRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionService questionService;

    @ApiOperation(value = "Find a question by ID", response = Question.class, nickname = "getQuestionById")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public QuestionDto getQuestionById(@PathVariable("id") long id) {
        return questionService.findOne(id);
    }


    @ApiOperation(value = "Find all questions", responseContainer = "List", response = QuestionDto.class, nickname = "getQuestionByQuestionnaireIDAndCategorieId")
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
    public Iterable<QuestionDto> getQuestions(@RequestParam(value = "questionnaireId", required = false) Long questionnaireId) {
        if (questionnaireId != null) {

            return questionService.findByQuestionnaireId(questionnaireId);

        }
        return questionService.findAll();
    }


    @ApiOperation(value = "Update a question", response = Question.class, nickname = "updateQuestion")
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public QuestionDto updateQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.updateQuestion(questionDto);
    }

    @ApiOperation(value = "Save a question", response = Question.class, nickname = "saveQuestion")
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