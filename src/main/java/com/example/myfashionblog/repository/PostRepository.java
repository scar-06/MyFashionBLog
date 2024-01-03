package com.example.myfashionblog.repository;

import com.example.myfashionblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPublished(boolean published);
    List<Post> findByTitleIgnoreCaseContaining(String title);


}
