package com.example.practice1.post.service;

import com.example.practice1.post.controller.dto.PostRequest;
import com.example.practice1.post.controller.dto.PostResponse;
import com.example.practice1.post.controller.entity.PostEntity;
import com.example.practice1.post.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public PostResponse save(PostRequest request) {
    PostEntity entity = PostEntity.fromRequest(request);
    return PostResponse.fromEntity(postRepository.save(entity));
  }

  public List<PostResponse> findAll() {
    List<PostEntity> entityList = postRepository.findAll();
    List<PostResponse> responseList = new ArrayList<>();

    for (PostEntity entity : entityList) {
      PostResponse response = PostResponse.fromEntity(entity);
      responseList.add(response);
    }
    return responseList;
  }


  public PostResponse findById(Long id) {
    PostEntity entity = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

    return PostResponse.fromEntity(entity);
  }

  public PostResponse update(Long id, PostRequest postRequest) {
    PostEntity entity = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    // entity 수정로직
    entity.update(postRequest.title(), postRequest.content(), postRequest.author());
    return PostResponse.fromEntity(entity);
  }

  public void delete(Long id) {
    postRepository.deleteById(id);
  }
}

