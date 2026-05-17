package ssafy.study.ssafystudy.domain.post.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssafy.study.ssafystudy.domain.post.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

  public PostEntity save(PostEntity entity);

  public List<PostEntity> findAll();

  public Optional<PostEntity> findById(Long id);

  public void delete(PostEntity entity);
}
