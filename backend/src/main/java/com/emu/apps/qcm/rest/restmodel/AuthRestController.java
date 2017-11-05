package com.emu.apps.qcm.rest.restmodel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
