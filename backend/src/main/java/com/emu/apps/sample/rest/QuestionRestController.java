package com.emu.apps.sample.rest;

import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.QuestionService;
import com.emu.apps.sample.services.dtos.FileQuestionJson;
import com.emu.apps.sample.services.dtos.MessageDto;
import com.emu.apps.sample.services.dtos.QuestionDto;
import com.emu.apps.sample.services.mappers.FileQuestionMapper;
import com.emu.apps.sample.services.mappers.QuestionMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

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
    public Iterable<QuestionDto> getQuestions() {
        return questionService.findAll();

    }


    @ApiOperation(value = "Find all questions by category", response = Iterable.class)
    @RequestMapping(value="/category/{id}",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("true")
    public Iterable<QuestionDto> getQuestionsByCategoryId(@PathVariable("id") String id) {
        return questionService.findByCategoryId(id);
    }


    @ApiOperation(value = "Create Dummy questions", response = String.class)
    @RequestMapping(value = "/dummy", method = RequestMethod.GET, produces = "application/json")
    public String dummy() {
        logger.info("init");
        questionService.init();
        return "Done";
    }

    @ApiOperation(value = "upload a question json file", response = ResponseEntity.class)
    @RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "Content-Type= multipart/form-data", produces = "application/json")
    @ResponseBody
    @Secured("ROLE_USER")
    public ResponseEntity<?> uploadQuestionFile(@RequestParam("file") MultipartFile file) throws IOException {

        try {
            logger.info(file.getOriginalFilename());
            ObjectMapper objectMapper = new ObjectMapper();
            //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            final FileQuestionJson[] questions = objectMapper.readValue(file.getInputStream(), FileQuestionJson[].class);
            for (FileQuestionJson fileQuestionJson : questions) {
                logger.info("saving question question:= " + fileQuestionJson.getQuestion());
                questionService.save(fileQuestionJson);
            }
        } catch (Exception e) {
            logger.error("err", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new MessageDto("Ok"),HttpStatus.OK);
    }


    @ExceptionHandler({JsonProcessingException.class,IOException.class})
    public ResponseEntity<?> handleAllexception(Exception e) throws IOException {
        return new ResponseEntity<>(new MessageDto(e.getMessage()),HttpStatus.BAD_REQUEST);
    }

}
