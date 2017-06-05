package com.emu.apps.sample.rest;

import com.emu.apps.sample.model.Category;
import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.QuestionService;
import com.emu.apps.sample.services.dtos.QuestionDto;
import com.emu.apps.sample.services.repositories.CategoryCrudRepository;
import com.emu.apps.sample.services.repositories.QuestionCrudRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by eric on 05/06/2017.
 */
@RestController
@RequestMapping("questions")
@Api(value = "question-store", description = "View All operations ",tags = "Question Store")
public class QuestionRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

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
    public Iterable<QuestionDto> getQuestions() {
        return questionService.findAll();

    }

    @ApiOperation(value = "Create Dummy questions", response = String.class)
    @RequestMapping(value = "/dummy", method = RequestMethod.GET, produces = "application/json")
    public String dummy() {
        log.info("init");
        questionService.init();
        return "Done";
    }


}
