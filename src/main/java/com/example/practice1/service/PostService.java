package com.example.practice1.service;

import com.example.practice1.controller.dto.PostRequest;
import com.example.practice1.controller.dto.PostResponse;
import com.example.practice1.controller.entity.PostEntity;
import com.example.practice1.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public PostResponse save(PostRequest request) {
    PostEntity entity = new PostEntity(request.title(), request.content(), request.author());
    PostEntity returnedEntity = postRepository.save(entity);

    return new PostResponse(returnedEntity.getId(), returnedEntity.getTitle(),
        returnedEntity.getContent(), returnedEntity.getAuthor());
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

