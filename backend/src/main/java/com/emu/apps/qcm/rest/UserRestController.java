package com.emu.apps.qcm.rest;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.util.*;

@RestController
@Api(value = "user-store", description = "All operations ", tags = "Users")
@RequestMapping("/api/v1/users/me")
public class UserRestController {
    
    @ApiOperation(value = "get Current user", response = Map.class)
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

}
