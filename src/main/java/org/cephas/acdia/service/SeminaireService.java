package org.cephas.acdia.service;

import org.cephas.acdia.exception.ResourceNotFoundException;

import org.cephas.acdia.model.Seminaire;

import org.cephas.acdia.repository.SeminaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by admin on 20-05-19.
 */
@Service
public class SeminaireService {
    @Autowired
    private SeminaireRepository seminaireRepository;


    public Optional<Seminaire> findById(Long Id) {
        return seminaireRepository.findById(Id);
    }


    public List<Seminaire> getAllSeminaires() {
        return seminaireRepository.findAll();
    }

    public Optional<Seminaire> getSeminaireId(Long seminaireId) {
        if (!seminaireRepository.existsById(seminaireId)) {
            throw new ResourceNotFoundException("seminaireId with id " + seminaireId + " not found");
        }
        return seminaireRepository.findById(seminaireId);
    }



    public void saveSeminaire(Seminaire seminaire) {
        seminaireRepository.save(seminaire);
    }

    public void deleteById(long id) {
        seminaireRepository.deleteById(id );
    }
}
