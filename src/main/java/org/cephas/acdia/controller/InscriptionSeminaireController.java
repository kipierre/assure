package org.cephas.acdia.controller;

import org.cephas.acdia.model.InscriptionSeminaire;
import org.cephas.acdia.service.InscriptionSeminaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 24-05-19.
 */
@RestController
@RequestMapping("/api/auth")
public class InscriptionSeminaireController {
    @Autowired
    private InscriptionSeminaireService inscriptionSeminaireService;


    @RequestMapping(value = "/getAllInscriptionSeminaires", method = RequestMethod.GET)
    public List<InscriptionSeminaire> getAllInscriptionSeminaires() {
        return inscriptionSeminaireService.getAllInscriptionSeminaires();
    }
    @RequestMapping(value = "/inscriptionSeminaire/{id}", method = RequestMethod.GET)
    public Optional<InscriptionSeminaire> getInscriptionSeminaireById(@PathVariable(value = "id")Long id) {
        return inscriptionSeminaireService.getInscriptionSeminaireById(id);
    }
    @RequestMapping(value = "/{seminaireId}/inscriptionSeminaire", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public InscriptionSeminaire createInscriptionSeminaire(@PathVariable(value = "seminaireId" )Long seminaireId,
                                                           @RequestBody InscriptionSeminaire inscriptionSeminaire) {
        return inscriptionSeminaireService.createInscriptionSeminaire(seminaireId, inscriptionSeminaire);
    }
    @RequestMapping(value = "/seminaireId/id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public InscriptionSeminaire updateInscriptionSeminaireById(@PathVariable(value = "dseminaireId")Long seminaireId,
                                                                   @PathVariable(value = "id") Long id,
                                                               @RequestBody InscriptionSeminaire inscriptionSeminaire) {
        return inscriptionSeminaireService.updateInscriptionSeminaireById(seminaireId, id, inscriptionSeminaire);
    }
}
