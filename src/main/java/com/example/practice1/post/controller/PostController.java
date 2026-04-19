package com.example.practice1.post.controller;

import com.example.practice1.post.controller.dto.PostRequest;
import com.example.practice1.post.controller.dto.PostResponse;
import com.example.practice1.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
  public List<PostResponse> findAllPosts() {
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
