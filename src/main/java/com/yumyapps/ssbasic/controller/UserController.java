package com.yumyapps.ssbasic.controller;

import com.yumyapps.ssbasic.entity.RegisterUserDto;
import com.yumyapps.ssbasic.entity.SsUser;
import com.yumyapps.ssbasic.service.SsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final SsUserService userService;

    @GetMapping(path = "reg")
    public SsUser registerUser() {
        var user = RegisterUserDto.builder()
                .userName("faisal@gmail.com")
                .userPassword("123456")
                .authorities(new String[]{"read", "write", "delete"})
                .enable(true)
                .notLocked(true)
                .build();
        return userService.registerNewUser(user);
    }

    @GetMapping("user")
    public SsUser getUser(@RequestParam(name = "name") String name) {
        return userService.readUserByName(name);
    }

    @GetMapping(path = "hello")
    public String sayHello() {
        var u = SecurityContextHolder.getContext().getAuthentication();
        u.getAuthorities().forEach(System.out::println);
        return "Hello World";
    }

    @GetMapping(path = "greet")
    public String sayGreets() {
        return "welcome abort";
    }


}
