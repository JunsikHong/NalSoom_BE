package com.pick.nalsoom.repository;

import com.pick.nalsoom.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
