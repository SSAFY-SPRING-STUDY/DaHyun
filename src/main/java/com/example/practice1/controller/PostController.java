package com.example.practice1.controller;

import com.example.practice1.controller.dto.PostRequest;
import com.example.practice1.controller.dto.PostResponse;
import com.example.practice1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest request) {
        return postService.save(request);
    }

    @GetMapping("/api/posts")
    public List<PostResponse> findAllPosts () {
        return postService.findAll();
    }

    @GetMapping("/api/posts/{id}")
    public PostResponse findPostById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/api/posts/{id}")
    public PostResponse update(@PathVariable Long id,
                               @RequestBody PostRequest postRequest) {
        return postService.update(id, postRequest);
    }

    @DeleteMapping("/api/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }




}
