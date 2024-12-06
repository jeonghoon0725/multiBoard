package com.jhhan.multiboard.posts.repository;

import com.jhhan.multiboard.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRepository extends JpaRepository<Posts, Long> {
    //

}
