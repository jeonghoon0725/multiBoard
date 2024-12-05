package com.jhhan.multiboard.board.controller;

import com.jhhan.multiboard.board.entity.Posts;
import com.jhhan.multiboard.board.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostsController {

    @Autowired
    private PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {

        List<Posts> posts = postsService.getPostsList();

//        log.debug("==========================");
//        log.debug(posts.toString());
//        log.debug("==========================");

        System.out.println("======================");
        for (Posts post : posts) {
            System.out.println(post.toString());
        }

        System.out.println("======================");

        model.addAttribute("posts", posts);

        return "index";
    }

    @GetMapping("/1")
    public String index1() {
        return "index1";
    }




}
