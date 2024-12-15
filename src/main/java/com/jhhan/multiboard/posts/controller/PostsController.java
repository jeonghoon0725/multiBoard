package com.jhhan.multiboard.posts.controller;

import com.jhhan.multiboard.posts.dto.PostsDto;
import com.jhhan.multiboard.posts.entity.Posts;
import com.jhhan.multiboard.posts.service.PostsService;
import com.jhhan.multiboard.system.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
            model.addAttribute("user", user);
        }

        model.addAttribute("posts", posts);

        return "index";
    }

    @GetMapping("/posts/write")
    public String write(Model model, HttpSession session) {
        UserSessionDto user = (UserSessionDto) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "posts/write";
    }

    @GetMapping("/posts/info/{id}")
    public String info(@PathVariable Long id, Model model, HttpSession session) {
        UserSessionDto user = (UserSessionDto) session.getAttribute("user");
        PostsDto.Response dto = postsService.findById(id);

        /* 사용자 관련 */
        if (user != null) {
            model.addAttribute("user", user);

            /* 게시글 작성자 본인인지 확인 */
            if (dto.getUserId().equals(user.getId())) {
                model.addAttribute("isPostWriter", true);
            }
        }

        model.addAttribute("post", dto);
        return "posts/info";
    }

}
