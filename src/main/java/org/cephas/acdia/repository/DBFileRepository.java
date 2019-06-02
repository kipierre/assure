package org.cephas.acdia.repository;

import org.cephas.acdia.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by admin on 08-05-19.
 */
@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long> {



    Optional<DBFile> findById(Long fileId);

}