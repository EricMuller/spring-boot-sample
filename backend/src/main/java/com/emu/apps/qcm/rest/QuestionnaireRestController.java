package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.services.QuestionnaireService;
import com.emu.apps.qcm.services.dtos.FileQuestionDto;
import com.emu.apps.qcm.services.dtos.MessageDto;
import com.emu.apps.qcm.services.dtos.QuestionnaireDto;
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

import java.io.IOException;

@RestController
@RequestMapping("api/questionnaires")
@Api(value = "fiche-store", description = "All operations ", tags = "Questionnaires")
public class QuestionnaireRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionnaireService questionnairesService;

    @ApiOperation(value = "upload a questionnaire json file", response = ResponseEntity.class)
    @RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "Content-Type= multipart/form-data", produces = "application/json")
    @ResponseBody
    @Secured("ROLE_USER")
    public ResponseEntity<?> uploadQuestionFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            logger.info(file.getOriginalFilename());
            ObjectMapper objectMapper = new ObjectMapper();
            final FileQuestionDto[] questions = objectMapper.readValue(file.getInputStream(), FileQuestionDto[].class);
            questionnairesService.saveQuestionnaire(file.getOriginalFilename(), questions);
        } catch (Exception e) {
            logger.error("err", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessageDto("Ok"), HttpStatus.OK);
    }

    @ApiOperation(value = "Find all questionnaires", response = Iterable.class)
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
    public Iterable<QuestionnaireDto> getQuestionnaires() {
        return questionnairesService.findAll();
    }

}