package org.cephas.acdia.repository;

import org.cephas.acdia.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by admin on 08-05-19.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  //  @Query("select p from Post p order by p.dateTimeOfPost desc")
  //  Collection<Post> findAllSortedByDateReverse();

  //  Post findFirstByOrderByIdDesc();

   // @Query("select p from Post p order by p.dateTimeOfPost desc")
   // Page<Post> findAll(final Pageable pageable);
    @Override
    Optional<Post> findById(Long id);



}