package org.fadhel.springbootlab12.repository;


import org.fadhel.springbootlab12.model.Blog;
import org.fadhel.springbootlab12.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    Blog findBlogById(Long todoId);

    List<Blog> findAllByUser(User user);

    Blog findBlogByTitle(String title);
}
