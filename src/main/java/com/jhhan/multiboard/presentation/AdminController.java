package com.jhhan.multiboard.presentation;

import com.jhhan.multiboard.application.dto.UserSessionDto;
import com.jhhan.multiboard.domain.User;
import com.jhhan.multiboard.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/user/list")
    public String getAllUser(Model model, HttpSession session) {

        UserSessionDto user = (UserSessionDto) session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user", user);
        }

        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);

        return "user/list";
    }
}
