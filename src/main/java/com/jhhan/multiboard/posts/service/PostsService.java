package com.jhhan.multiboard.posts.service;

import com.jhhan.multiboard.posts.dto.PostsDto;
import com.jhhan.multiboard.posts.entity.Posts;
import com.jhhan.multiboard.posts.repository.PostsRepository;
import com.jhhan.multiboard.user.entity.User;
import com.jhhan.multiboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Posts> getPostsList() {
        return postsRepository.findAll();
    }

    @Transactional
    public Long save(PostsDto dto, String nickname) {
        User user = userRepository.findByNickname(nickname);
        dto.setUser(user);

        Posts posts = dto.toEntity();
        postsRepository.save(posts);

        return posts.getId();
    }


    public PostsDto.Response findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new PostsDto.Response(posts);
    }

}
