package org.cephas.acdia.repository;
import org.cephas.acdia.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 * Created by admin on 08-05-19.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndPostId(Long id, Long postId);

    Collection<Comment> findByPost_Id(Long id);

    @Query(value = "SELECT * FROM comment c where c.post_id = ?1", nativeQuery = true)
    public List<Comment> findByPostId(Long postId);
}