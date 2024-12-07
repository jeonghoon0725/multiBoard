package com.jhhan.multiboard.user.controller;

import com.jhhan.multiboard.user.dto.UserDto;
import com.jhhan.multiboard.user.entity.User;
import com.jhhan.multiboard.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }

    /**
     * 회원가입
     * @param
     * @return
     */
    @PostMapping("/joinProc")
    public String joinProc(UserDto dto) {
        userService.userJoin(dto);

        return "redirect:/user/login";
    }

    @GetMapping("")
    public String getAllUser(Model model, HttpSession session) {

        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);
        return "user/list";
    }


//    @PostMapping ("/loginProc")
//    public String loginForm(Model model) {
//        return "user/loginForm";
//    }



}
