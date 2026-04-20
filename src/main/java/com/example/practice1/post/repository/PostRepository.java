package com.example.practice1.post.repository;

import com.example.practice1.post.controller.entity.PostEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

  List<PostEntity> postList = new ArrayList<>();

  public PostEntity save(PostEntity postEntity) {
    postList.add(postEntity);

    return postEntity;
  }

  public List<PostEntity> findAll() {
    return postList;
  }

  // null 값이 있을 경우 NullPointException이 아닌
  // 다른 예외처리를 할 수 있도록, null처리를 할 수 있도록 하기 위해
  // Optional<> 사용
  public Optional<PostEntity> findById(Long id) {
    for (PostEntity entity : postList) {
      if (entity.getId().equals(id)) {
        return Optional.of(entity);
      }
    }
    return Optional.empty();
  }

  public void deleteById(Long id) {
    // 람다표현식
    // removeIf(조건) -> 리스트에서 조건이 true인 요소를 삭제하는 메서드
    // entity 한개씩 꺼내면서, entity.getId().equals(id)가 true면
    // entity삭제
    postList.removeIf(entity -> entity.getId().equals(id));
  }
}
