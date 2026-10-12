package org.fadhel.springbootlab12.service;

import lombok.RequiredArgsConstructor;
import org.fadhel.springbootlab12.api.ApiException;
import org.fadhel.springbootlab12.model.Blog;
import org.fadhel.springbootlab12.model.User;
import org.fadhel.springbootlab12.repository.AuthRepository;
import org.fadhel.springbootlab12.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public List<Blog> getMyBlogs(Long userId){
        User user = authRepository.findUserById(userId);

        if(user == null){
            throw new ApiException("User not found");
        }

        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Long userId, Blog blog){
        User user = authRepository.findUserById(userId);
        if(user == null){
            throw new ApiException("User not found");
        }

        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Long userId, Long blogId, Blog blog){
        Blog oldBlog = blogRepository.findBlogById(blogId);
        User user = authRepository.findUserById(userId);

        if(user == null){
            throw new ApiException("User not found");
        }

        if(oldBlog == null){
            throw new ApiException("Blog Not found");
        }

        if(oldBlog.getUser().getId() != userId){
            throw new ApiException("Can not update blog");
        }

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());

        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Long userId, Long blogId){
        User user = authRepository.findUserById(userId);
        if(user == null){
            throw new ApiException("User not found");
        }

        Blog blog = blogRepository.findBlogById(blogId);
        blogRepository.delete(blog);
    }

    public Blog getBlogByTitle(String title) {
        return blogRepository.findBlogByTitle(title);
    }

    public Blog getBlogById(Long blogId) {
        return blogRepository.findBlogById(blogId);
    }
}
