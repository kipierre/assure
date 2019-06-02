package org.cephas.acdia.repository;


import org.cephas.acdia.model.InscriptionSeminaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 24-05-19.
 */
@Repository
public interface InscriptionSeminaireRepository extends JpaRepository<InscriptionSeminaire, Long> {

    Optional<InscriptionSeminaire> findById(Long Id);

    List<InscriptionSeminaire> findAllBySeminaireId(long seminaireId);
}
