package com.jhhan.multiboard.board.repository;

import com.jhhan.multiboard.board.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRepository extends JpaRepository<Posts, Long> {
    //

}
