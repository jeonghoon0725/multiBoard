package com.jhhan.multiboard.presentation;

import com.jhhan.multiboard.application.dto.PostsDto;
import com.jhhan.multiboard.application.PostsService;
import com.jhhan.multiboard.application.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /* UPDATE */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }
}
