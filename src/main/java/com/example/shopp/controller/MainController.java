package com.example.shopp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class MainController {

    @GetMapping("/main")
    public String mainC(){
        return "/main";
    }
}
