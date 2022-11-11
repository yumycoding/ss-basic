package com.yumyapps.ssbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping(path = "greet")
    public String sayGreets() {
        return "welcome abort";
    }


}
