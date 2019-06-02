package org.cephas.acdia.service;

import org.cephas.acdia.exception.ResourceNotFoundException;
import org.cephas.acdia.model.FilePost;
import org.cephas.acdia.model.UserProfile;
import org.cephas.acdia.repository.UserProfileRepository;
import org.cephas.acdia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by admin on 20-05-19.
 */
@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<UserProfile> findById(Long Id) {
        return userProfileRepository.findById(Id);
    }

   /** public List<UserProfile> findAllByUserId(long userId) {
        return userProfileRepository.findAllByUserId (userId);
    }
*/
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> getUserProfileById(Long filePost) {
        if (!userProfileRepository.existsById(filePost)) {
            throw new ResourceNotFoundException("filePost with id " + filePost + " not found");
        }
        return userProfileRepository.findById(filePost);
    }



    public void saveUserProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    public void deleteById(long id) {
        userProfileRepository.deleteById(id );
    }

    public boolean deleteById(Long id) {
        if(id==null){
            throw new ResourceNotFoundException("filePost with id " + id + " not found");
        }
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        userProfileRepository.delete(userProfile.get());
        return true;
    }

    public List<UserProfile> findAllByUserId(long userId) {return userProfileRepository.findAllByUserId (userId);
    }

}
