package com.jhhan.multiboard.application;

import com.jhhan.multiboard.application.dto.PostsDto;
import com.jhhan.multiboard.domain.Posts;
import com.jhhan.multiboard.infrastructure.persistence.PostsRepository;
import com.jhhan.multiboard.domain.User;
import com.jhhan.multiboard.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        return new PostsDto.Response(posts);
    }

    @Transactional
    public int updateView(Long id) {
        return postsRepository.updateView(id);
    }

    @Transactional
    public void update(Long id, PostsDto dto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));

        posts.update(dto.getTitle(), dto.getContent());
    }
}
