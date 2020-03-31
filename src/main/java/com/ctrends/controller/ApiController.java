package com.ctrends.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController
{
    @GetMapping("/home")
    public String home(){
        return "You have used valid token. ";
    }
}
