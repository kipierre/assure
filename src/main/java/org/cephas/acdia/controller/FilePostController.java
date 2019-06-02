package org.cephas.acdia.controller;


import org.cephas.acdia.model.FileBucket;
import org.cephas.acdia.model.FilePost;
import org.cephas.acdia.model.Post;
import org.cephas.acdia.service.FilePostService;
import org.cephas.acdia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;




/**
 * Created by admin on 23-05-19.
 */
@RestController
@RequestMapping("/api/auth")
public class FilePostController {

    @Autowired
    private FilePostService filePostService;
    @Autowired
    private PostService postService;




    @RequestMapping(value = "/filePost/{fileId}", method = RequestMethod.GET)
    public Optional<FilePost> getFilePosById(@PathVariable(value = "fileId") Long fileId) {
        return  filePostService.getFilePosById(fileId);
    }
    @RequestMapping(value = { "/downloadfilepost/{postId}" }, method = RequestMethod.GET)
    public boolean addDocuments(@PathVariable long postId ) {

       postService.findById (postId);

        FileBucket fileModel = new FileBucket();
        new FileBucket ();

        filePostService.findAllByPostId(postId);
        return true;

    }

    @RequestMapping(value = {"/filePosts" }, method = RequestMethod.GET)
    public List<FilePost> listFilePost() {
        return filePostService.getAllFilePosts();
    }

    @RequestMapping(value = { "/downloadfilepost/{postId}/{fileId}" }, method = RequestMethod.GET)
    public String  downloadDocument(@PathVariable long postId, @PathVariable long fileId, HttpServletResponse response, HttpServletRequest request) throws IOException {

        FilePost filePost = filePostService.findById(fileId).get();

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(String.valueOf (filePost));
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/pdf";
        }
        response.setContentType(mimeType);
        response.setContentType(filePost.getType());
        response.setContentLength(filePost.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + filePost.getName() +"\"");
        FileCopyUtils.copy(filePost.getContent(), response.getOutputStream());
        return "redirect:downloadfilepost/"+postId;
    }

    @RequestMapping(value = {"/uploadfilePost/{postId}"}, method = RequestMethod.POST)
    public boolean uploadDocument( @Valid FileBucket fileBucket, BindingResult result, @PathVariable long postId)
            throws IOException{


            System.out.println("Fetching file");
            Post post = postService.findById(postId).get();
            saveFilePost(fileBucket, post);
            return true;


    }

    private void saveFilePost(@RequestParam("fileBucket") FileBucket fileBucket, Post post) throws IOException {

        FilePost filePost = new FilePost();
        MultipartFile multipartFile = fileBucket.getFile();
        filePost.setName(multipartFile.getOriginalFilename());
        filePost.setDescription(fileBucket.getDescription());
        filePost.setType(multipartFile.getContentType());
        filePost.setContent(multipartFile.getBytes());

        filePost.setPost(post);

        filePostService.saveFilePost(filePost);
    }

    @RequestMapping(value = { "/deletefilePost/{fileId}" }, method = RequestMethod.DELETE)
    public boolean deleteById(@PathVariable  long fileId) {
        filePostService.deleteById(fileId);
        return true;
    }
}
