package com.yumyapps.ssbasic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {





    @GetMapping(path = "greet")
    public String sayGreets() {
        return "welcome abort";
    }


}
