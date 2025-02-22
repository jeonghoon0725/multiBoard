package com.jhhan.multiboard.presentation;

import com.jhhan.multiboard.application.dto.UserSessionDto;
import com.jhhan.multiboard.application.dto.UserRequestDto;
import com.jhhan.multiboard.application.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "isError", required = false)String isError, @RequestParam(value = "exception", required = false)String exception, Model model) {

        model.addAttribute("isError", isError);
        model.addAttribute("exception", exception);

        return "user/login";
    }

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }

    @GetMapping("/info")
    public String info(Model model, HttpSession session) {

        UserSessionDto userSessionDto = (UserSessionDto) session.getAttribute("user");
        if(userSessionDto != null) {
            model.addAttribute("userSessionDto", userSessionDto);
        }

        return "user/info";
    }

    /**
     * 회원가입
     * @param
     * @return
     */
    @PostMapping("/joinProc")
    public String joinProc(@Valid UserRequestDto dto, Errors errors, Model model) {
        if(errors.hasErrors()) {
            //회원가입 실패 시 입력값 보존
            model.addAttribute("userDto", dto);

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for(String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "/user/join";
        }

        //중복검사 필요
        userService.userJoin(dto);

        return "redirect:/user/login";
    }


//    @PostMapping ("/loginProc")
//    public String loginForm(Model model) {
//        return "user/loginForm";
//    }



}
