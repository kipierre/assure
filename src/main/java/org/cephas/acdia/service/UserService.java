package org.cephas.acdia.service;

import org.cephas.acdia.exception.ResourceNotFoundException;
import org.cephas.acdia.model.InscriptionSeminaire;
import org.cephas.acdia.model.User;
import org.cephas.acdia.repository.InscriptionSeminaireRepository;
import org.cephas.acdia.repository.SeminaireRepository;
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
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public Optional<User> findById(Long Id) {
        return userRepository.findById(Id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("user with id " + id + " not found");
        }
        return userRepository.findById(id);
    }


    public User createUser( User user) {
        return userRepository.save(user);
    }







    public User updateUserById(Long userid,User userRequest) {


        return userRepository.findById(userid)
                .map(user -> {
                    user.setUsername(userRequest.getUsername());

                    return userRepository.save(userRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("userId " + userid + "not found"));
    }






    public boolean deleteUser(Long id) {

        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        userRepository.delete(user.get());
        return true;
    }

}
