package com.emu.apps.qcm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeContoller {

    @RequestMapping("/api")
    public String index() {
        return "swagger-ui";
    }
}