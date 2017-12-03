package com.emu.apps.qcm.rest;

import com.emu.apps.qcm.services.*;
import com.emu.apps.qcm.services.dtos.*;
import com.fasterxml.jackson.databind.*;
import io.swagger.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.io.*;

@RestController
@RequestMapping("/api/v1/questionnaires")
@Api(value = "questionnaire-store", description = "All operations ", tags = "Questionnaires")
public class QuestionnaireRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuestionnaireService questionnairesService;

    @ApiOperation(value = "upload a questionnaire json file", responseContainer = "ResponseEntity", response = MessageDto.class, tags = "Questionnaires")
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

    @ApiOperation(value = "Find all questionnaires", responseContainer = "List", response = QuestionDto.class, tags = "Questionnaires")
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