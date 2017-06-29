package com.emu.apps.sample.rest.restmodel;

import com.emu.apps.sample.model.Question;
import com.emu.apps.sample.services.QuestionService;
import com.emu.apps.sample.services.dtos.FileQuestionJson;
import com.emu.apps.sample.services.dtos.MessageDto;
import com.emu.apps.sample.services.dtos.QuestionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by eric on 05/06/2017.
 */
@RestController
@RequestMapping("api/auth")
@Api(value = "auth-store", description = "All operations ", tags = "auth")
public class AuthRestController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    @ApiOperation(value = "show current user", response = String.class)
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public String user() {
        //logger.info("init");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }


}
