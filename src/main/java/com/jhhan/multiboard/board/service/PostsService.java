package com.jhhan.multiboard.board.service;

import com.jhhan.multiboard.board.entity.Posts;
import com.jhhan.multiboard.board.repository.PostsRepository;
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
