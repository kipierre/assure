package org.cephas.acdia.repository;

import org.cephas.acdia.model.FilePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 23-05-19.
 */
@Repository
public interface FilePostRepository extends JpaRepository<FilePost, Long> {

    Optional<FilePost> findById(Long Id);

    List<FilePost> findAllByPostId(long postId);


}
