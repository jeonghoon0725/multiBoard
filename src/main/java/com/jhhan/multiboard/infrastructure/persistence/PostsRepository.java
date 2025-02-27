package com.jhhan.multiboard.infrastructure.persistence;

import com.jhhan.multiboard.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);
}
