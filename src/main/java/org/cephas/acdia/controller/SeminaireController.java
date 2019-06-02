package org.cephas.acdia.controller;

import org.cephas.acdia.model.*;

import org.cephas.acdia.service.SeminaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 24-05-19.
 */
@RestController
@RequestMapping("/api/auth")
public class SeminaireController {
    @Autowired
    private SeminaireService seminaireService;

    @RequestMapping(value = "/seminaire/{seminaireId}", method = RequestMethod.GET)
    public Optional<Seminaire> getSeminaireById(@PathVariable(value = "seminaireId") Long seminaireId) {
        return  seminaireService.getSeminaireId(seminaireId);
    }

    @RequestMapping(value = { "/seminaires" }, method = RequestMethod.GET)
    public List<Seminaire> listSeminaire() {
        return seminaireService.getAllSeminaires();
    }

    @RequestMapping(value = { "/downloadseminaire/{seminaireId}" }, method = RequestMethod.GET)
    public String  downloadDocument(@PathVariable long seminaireId, HttpServletResponse response, HttpServletRequest request) throws IOException {

        Seminaire seminaire = seminaireService.findById(seminaireId).get();

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(String.valueOf (seminaire));
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/pdf";
        }
        response.setContentType(mimeType);
        response.setContentType(seminaire.getType());
        response.setContentLength(seminaire.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + seminaire.getName() +"\"");
        FileCopyUtils.copy(seminaire.getContent(), response.getOutputStream());
        return "redirect:/add-document";
    }

    @RequestMapping(value = {"/uploadseminaire"}, method = RequestMethod.POST)
    public boolean uploadDocument(@Valid FileBucket fileBucket, BindingResult result)
            throws IOException{

        if (result.hasErrors()) {
            System.out.println("validation errors");
            return  false;
        } else {
            System.out.println("Fetching file");

            saveSeminaire(fileBucket);
            return true;
        }

    }

    private void saveSeminaire(@RequestParam("fileBucket") FileBucket fileBucket) throws IOException {

        Seminaire seminaire = new Seminaire();
        MultipartFile multipartFile = fileBucket.getFile();
        seminaire.setName(multipartFile.getOriginalFilename());
        seminaire.setDescription(fileBucket.getDescription());
        seminaire.setType(multipartFile.getContentType());
        seminaire.setContent(multipartFile.getBytes());
        seminaire.setTitle(fileBucket.getTitle());
        seminaire.setTheme(fileBucket.getTheme());

        seminaireService.saveSeminaire(seminaire);
    }

    @RequestMapping(value = { "/deleteSeminaire/{seminaireId}" }, method = RequestMethod.DELETE)
    public boolean deleteById(@PathVariable  long seminaireId) {
        seminaireService.deleteById(seminaireId);
        return true;
    }
}
