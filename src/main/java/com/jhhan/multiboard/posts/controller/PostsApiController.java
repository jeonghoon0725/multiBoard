package com.jhhan.multiboard.posts.controller;

import com.jhhan.multiboard.posts.dto.PostsDto;
import com.jhhan.multiboard.posts.service.PostsService;
import com.jhhan.multiboard.system.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /* CREATE */
    @PostMapping
    public ResponseEntity save(@RequestBody PostsDto dto, HttpSession session) {
        UserSessionDto user = (UserSessionDto) session.getAttribute("user");
        return ResponseEntity.ok(postsService.save(dto, user.getNickname()));
    }
}
