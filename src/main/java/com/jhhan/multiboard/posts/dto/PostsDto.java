package com.jhhan.multiboard.posts.dto;

import com.jhhan.multiboard.posts.entity.Posts;
import com.jhhan.multiboard.user.entity.Role;
import com.jhhan.multiboard.user.entity.User;
import lombok.*;

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

    @Getter
    public static class Response {
        private final Long id;
        private final String title;
        private final String writer;
        private final String content;
        private final String createdDate, modifiedDate;
        private final int view;
        private final Long userId;

        /* Entity -> Dto*/
        public Response(Posts posts) {
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.writer = posts.getWriter();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
            this.view = posts.getView();
            this.userId = posts.getUser().getId();
        }
    }
}
