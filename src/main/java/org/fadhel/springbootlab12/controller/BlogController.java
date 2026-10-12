package org.fadhel.springbootlab12.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.springbootlab12.api.ApiResponse;
import org.fadhel.springbootlab12.model.Blog;
import org.fadhel.springbootlab12.model.User;
import org.fadhel.springbootlab12.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;


    @GetMapping("/get")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/get/my/blog")
    public ResponseEntity<List<Blog>> getMyBlogs(@AuthenticationPrincipal User user) {

        return ResponseEntity.ok(blogService.getMyBlogs(user.getId()));

    }

    @PostMapping("/add")
    public ResponseEntity<?> addBlog(@AuthenticationPrincipal User user, @Valid @RequestBody Blog blog){

        blogService.addBlog(user.getId(),blog);

        return ResponseEntity.ok(new ApiResponse("Blog added successfully"));
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity<?> updateBlog(@AuthenticationPrincipal User user,
                                        @PathVariable Long blogId,
                                        @Valid @RequestBody Blog blog){
        blogService.updateBlog(user.getId(), blogId, blog);

        return ResponseEntity.status(200).body(new ApiResponse("Blog Updated successfully"));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<?> deleteBlog(@AuthenticationPrincipal User user, @PathVariable Long blogId){
        blogService.deleteBlog(user.getId(), blogId);

        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted successfully"));
    }

    @GetMapping("/get/{blogId}")
    public ResponseEntity<?> getBlogById(@PathVariable Long blogId) {
        return ResponseEntity.ok(blogService.getBlogById(blogId));
    }

    @GetMapping("/get/title/{title}")
    public ResponseEntity<?> getBlogByTitle(@PathVariable String title) {
        return ResponseEntity.ok(blogService.getBlogByTitle(title));
    }
}
