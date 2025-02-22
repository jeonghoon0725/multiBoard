package com.jhhan.multiboard.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "redirect:/posts";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog/main";
    }
}
