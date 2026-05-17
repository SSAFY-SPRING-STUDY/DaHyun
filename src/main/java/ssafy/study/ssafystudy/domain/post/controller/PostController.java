package ssafy.study.ssafystudy.domain.post.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ssafy.study.ssafystudy.domain.auth.component.SessionManager;
import ssafy.study.ssafystudy.domain.auth.util.AuthTokenUtils;
import ssafy.study.ssafystudy.domain.post.controller.dto.PostRequest;
import ssafy.study.ssafystudy.domain.post.controller.dto.PostResponse;
import ssafy.study.ssafystudy.domain.post.service.PostService;
import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final SessionManager sessionManager;

  @PostMapping("/api/posts")
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponse createPost(
      @RequestBody PostRequest request,
      @RequestHeader("Authorization") String bearerToken
  ) {
    if (bearerToken == null) {
      throw new CustomException(ErrorCode.UNAUTHORIZED);
    }

    String sessionKey = AuthTokenUtils.pareBearerToken(bearerToken);
    Long authorId = sessionManager.getMemberId(sessionKey);

    return postService.create(request, authorId);
  }

  @GetMapping("/api/posts")
  public List<PostResponse> findAllPosts() {
    return postService.findAll();
  }

  @GetMapping("/api/posts/{id}")
  public PostResponse findPostById(@PathVariable Long id) {
    return postService.getPostById(id);
  }

  @PutMapping("/api/posts/{id}")
  public PostResponse updatePost(
      @PathVariable Long id,
      @RequestBody PostRequest postRequest,
      @RequestHeader("Authorization") String bearerToken
  ) {
    String sessionKey = AuthTokenUtils.pareBearerToken(bearerToken);
    Long authorId = sessionManager.getMemberId(sessionKey);
    return postService.update(postRequest, id, authorId);

  }

  @DeleteMapping("/api/posts/{id}")
  public void deletePost(
      @PathVariable Long id,
      @RequestHeader("Authorization") String bearerToken
  ) {
    String sessionKey = AuthTokenUtils.pareBearerToken(bearerToken);
    Long authorId = sessionManager.getMemberId(sessionKey);
    postService.delete(id, authorId);
  }


}
