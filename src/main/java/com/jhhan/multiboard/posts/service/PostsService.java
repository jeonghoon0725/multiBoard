package com.jhhan.multiboard.posts.service;

import com.jhhan.multiboard.posts.entity.Posts;
import com.jhhan.multiboard.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {

    @Autowired
    private PostsRepository postsRepository;

    public List<Posts> getPostsList() {
        return postsRepository.findAll();
    }
}
