package org.cephas.acdia.repository;

import org.cephas.acdia.model.Seminaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 20-05-19.
 */
@Repository
public interface SeminaireRepository extends JpaRepository<Seminaire, Long> {
    Optional<Seminaire> findById(Long Id);

  //  List<Seminaire> findAllBySeminaireId(long seminaireId);
}
