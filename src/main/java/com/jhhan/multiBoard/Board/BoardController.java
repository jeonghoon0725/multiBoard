package com.jhhan.multiBoard.Board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/1")
    public String index1() {
        return "index1";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "user/login";
    }
}
