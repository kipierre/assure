package org.cephas.acdia.service;

import org.cephas.acdia.exception.ResourceNotFoundException;

import org.cephas.acdia.model.InscriptionSeminaire;

import org.cephas.acdia.repository.InscriptionSeminaireRepository;
import org.cephas.acdia.repository.SeminaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by admin on 24-05-19.
 */
@Service
public class InscriptionSeminaireService {
    @Autowired
    private InscriptionSeminaireRepository inscriptionSeminaireRepository;

    @Autowired
    private SeminaireRepository seminaireRepository;

    public Optional<InscriptionSeminaire> findById(Long Id) {
        return inscriptionSeminaireRepository.findById(Id);
    }

    public List<InscriptionSeminaire> getAllInscriptionSeminaires() {
        return inscriptionSeminaireRepository.findAll();
    }

    public Optional<InscriptionSeminaire> getInscriptionSeminaireById(Long id) {
        if (!inscriptionSeminaireRepository.existsById(id)) {
            throw new ResourceNotFoundException("InscriptionSeminaire with id " + id + " not found");
        }
        return inscriptionSeminaireRepository.findById(id);
    }


    public InscriptionSeminaire createInscriptionSeminaire( Long seminaireId, InscriptionSeminaire inscriptionSeminaire) {
        Set<InscriptionSeminaire> inscriptionSeminaires = new LinkedHashSet<>();
        return seminaireRepository.findById(seminaireId).map(dbFile -> {
            inscriptionSeminaire.setSeminaire(dbFile);
            InscriptionSeminaire inscriptionSeminaire1 = inscriptionSeminaireRepository.save(inscriptionSeminaire);
            inscriptionSeminaires.add(inscriptionSeminaire1);
            dbFile.setInscriptionSeminaires(inscriptionSeminaires);
            return inscriptionSeminaire1;
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + seminaireId + " not found"));
    }






    public InscriptionSeminaire updateInscriptionSeminaireById( Long seminaireId, Long id,InscriptionSeminaire inscriptionSeminaireRequest) {
        if(!seminaireRepository.existsById(seminaireId)) {
            throw new ResourceNotFoundException("seminaireId " + seminaireId+ " not found");
        }

        return inscriptionSeminaireRepository.findById(id)
                .map(inscriptionSeminaire -> {
                    inscriptionSeminaire.setName(inscriptionSeminaireRequest.getName());
                    inscriptionSeminaire.setPhoneNumber(inscriptionSeminaireRequest.getPhoneNumber());
                    inscriptionSeminaire.setAddress1(inscriptionSeminaireRequest.getAddress1());
                    inscriptionSeminaire.setCity(inscriptionSeminaireRequest.getCity());
                    inscriptionSeminaire.setCountry(inscriptionSeminaireRequest.getCountry());

                    return inscriptionSeminaireRepository.save(inscriptionSeminaireRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("InscriptionseminaireId " + id + "not found"));
    }



}
