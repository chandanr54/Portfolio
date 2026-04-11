package com.project.portfolio.controller;

import com.project.portfolio.commanutil.PageName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FirstCorntroller {

    @GetMapping
    public String firstCorntroller(Model model) {
        model.addAttribute("titel", "Portfolio");
        return PageName.INDEX.getValue();
    }
}
