package com.project.portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstCorntroller {

    @GetMapping
    public String firstCorntroller(){
        return "FirstCorntroller";
    }
}
