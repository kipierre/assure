package org.cephas.acdia.controller;

import org.cephas.acdia.model.User;
import org.cephas.acdia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 25-05-19.
 */
@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllusers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }








    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {

        User userRe = userService.createUser(user);
        if(userRe == null ){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(userRe, HttpStatus.OK);

    }



    @RequestMapping(value = "/userProfileId/userId", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUserById( @PathVariable Long userId, @RequestBody User user) {
        return userService.updateUserById( userId, user);
    }



    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id) {

        boolean b=userService.deleteUser(id);
        if(b==false){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}
