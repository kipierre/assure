package org.cephas.acdia.controller;

import org.cephas.acdia.model.FileBucket;

import org.cephas.acdia.model.User;
import org.cephas.acdia.model.UserProfile;

import org.cephas.acdia.service.UserProfileService;
import org.cephas.acdia.service.UserService;
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
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userProfilee/{userProfileId}", method = RequestMethod.GET)
    public Optional<UserProfile> getUserProfileById(@PathVariable(value = "userProfileId") Long userProfileId) {
        return  userProfileService.getUserProfileById(userProfileId);
    }

    @RequestMapping(value = {" /userProfiles" }, method = RequestMethod.GET)
    public List<UserProfile> listUserProfile() {
        return userProfileService.getAllUserProfiles();
    }

    @RequestMapping(value = { "/downloaduserProfile/{userId}/{userProfileId}" }, method = RequestMethod.GET)
    public String  downloadDocument(@PathVariable long userId ,@PathVariable long userProfileId, HttpServletResponse response, HttpServletRequest request) throws IOException {

        UserProfile userProfile = userProfileService.findById(userProfileId).get();

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(String.valueOf (userProfile));
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/pdf";
        }
        response.setContentType(mimeType);
        response.setContentType(userProfile.getType());
        response.setContentLength(userProfile.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + userProfile.getName() +"\"");
        FileCopyUtils.copy(userProfile.getContent(), response.getOutputStream());
        return "redirect:/add-document"+userId;
    }

    @RequestMapping(value = {"/uploaduserProfile/{userId}"}, method = RequestMethod.POST)
    public boolean uploadDocument(@Valid FileBucket fileBucket,@PathVariable long userId)
            throws IOException{


            System.out.println("Fetching file");
            User user = userService.findById(userId).get();
            saveUserProfile(fileBucket, user);

            return true;
        }




    private void saveUserProfile(@RequestParam("fileBucket") FileBucket fileBucket,User user) throws IOException {

        UserProfile userProfile = new UserProfile();
        MultipartFile multipartFile = fileBucket.getFile();
        userProfile.setName(multipartFile.getOriginalFilename());
        userProfile.setContent(multipartFile.getBytes());
        userProfile.setType(multipartFile.getContentType());
        userProfile.setCity(fileBucket.getCity());
        userProfile.setAddress1(fileBucket.getAddress1());
        userProfile.setAddress2(fileBucket.getAddress2());
        userProfile.setCountry(fileBucket.getCountry());
        userProfile.setGender(fileBucket.getGender());
        userProfile.setPhoneNumber(fileBucket.getPhoneNumber());

        userProfile.setUser(user);
        userProfileService.saveUserProfile(userProfile);
    }

    @RequestMapping(value = { "/deleteuserProfile/{userProfileId}" }, method = RequestMethod.DELETE)
    public boolean deleteById(@PathVariable  long userProfileId) {
        userProfileService.deleteById(userProfileId);
        return true;
    }
}
