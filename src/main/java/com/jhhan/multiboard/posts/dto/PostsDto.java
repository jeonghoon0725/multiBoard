package com.jhhan.multiboard.posts.dto;

import com.jhhan.multiboard.posts.entity.Posts;
import com.jhhan.multiboard.user.entity.Role;
import com.jhhan.multiboard.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private String createdDate, modifiedDate;
    private int view;
    private User user;

    /* Dto -> Entity */
    public Posts toEntity() {
        Posts posts = Posts.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .view(0)
                .user(user)
                .build();

        return posts;
    }
}
