package org.cephas.acdia.service;
import org.cephas.acdia.exception.FileStorageException;
import org.cephas.acdia.exception.MyFileNotFoundException;

import org.cephas.acdia.model.DBFile;

import org.cephas.acdia.model.InscriptionSeminaire;
import org.cephas.acdia.repository.DBFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


/**
 * Created by admin on 08-05-19.
 */
@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;


    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
          // DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            DBFile dbFile = new DBFile();
            dbFile.setFileName(file.getName());
            dbFile.setFileType(file.getContentType());
            dbFile.setData(file.getBytes());
            dbFile.getTitle();
            dbFile.getContent();
            return dbFile;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }



    public DBFile getFile(Long fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }



}
