package com.emu.apps.qcm.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Api(value = "user-store", description = "All operations ", tags = "Users")
@RequestMapping("/api/v1/users/me")
public class UserRestController {

    @Value("${spring.profiles.active}")
    private String profile;

    @ApiOperation(value = "get Current user", response = Map.class, nickname = "getCurrentUser")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        map.put("profile", profile);
        return map;
    }

}
