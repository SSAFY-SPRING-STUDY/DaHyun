package ssafy.study.ssafystudy.domain.post.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.study.ssafystudy.domain.member.entity.MemberEntity;
import ssafy.study.ssafystudy.domain.member.repository.MemberRepository;
import ssafy.study.ssafystudy.domain.post.controller.dto.PostRequest;
import ssafy.study.ssafystudy.domain.post.controller.dto.PostResponse;
import ssafy.study.ssafystudy.domain.post.entity.PostEntity;
import ssafy.study.ssafystudy.domain.post.repository.PostRepository;
import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final MemberRepository memberRepository;

  public PostResponse create(PostRequest request, Long authorId) {
    MemberEntity author = memberRepository.findById(authorId)
        .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

    PostEntity entity = request.fromEntity(author);
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

  public PostResponse getPostById(Long id) {
    PostEntity entity = postRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

    return PostResponse.fromEntity(entity);
  }

  @Transactional
  public PostResponse update(PostRequest request, Long id, Long authorId) {
    MemberEntity author = memberRepository.findById(authorId)
        .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

    PostEntity entity = postRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

    // entity 수정로직
    entity.update(request.title(), request.content());

    return PostResponse.fromEntity(entity);
  }

  public void delete(Long id, Long authorId) {
    MemberEntity author = memberRepository.findById(authorId)
        .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

    PostEntity entity = postRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

    if (!entity.getAuthor().getId().equals(author.getId())) {
      throw new CustomException(ErrorCode.INVALID_PERMISSION);
    }

    postRepository.deleteById(id);
  }
}

