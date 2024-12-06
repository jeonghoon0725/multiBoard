package com.jhhan.multiboard.posts.controller;

import com.jhhan.multiboard.posts.entity.Posts;
import com.jhhan.multiboard.posts.service.PostsService;
import com.jhhan.multiboard.system.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostsController {

    @Autowired
    private PostsService postsService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        List<Posts> posts = postsService.getPostsList();

        UserSessionDto user = (UserSessionDto) session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user", user.getNickname());
        }

        model.addAttribute("posts", posts);

        return "index";
    }

    @GetMapping("/1")
    public String index1() {
        return "index1";
    }




}
