package com.jhhan.multiboard.infrastructure.persistence;

import com.jhhan.multiboard.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRepository extends JpaRepository<Posts, Long> {
    //

}
