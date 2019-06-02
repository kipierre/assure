package org.cephas.acdia.repository;

import org.cephas.acdia.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 08-05-19.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}