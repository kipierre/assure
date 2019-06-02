package org.cephas.acdia.controller;


import org.cephas.acdia.model.Post;
import org.cephas.acdia.payload.PostResponse;
import org.cephas.acdia.repository.PostRepository;
import org.cephas.acdia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 * Created by admin on 08-05-19.
 */
@RestController
@RequestMapping("/api/auth")
public class PostController {


    @Autowired
    private PostService postService;


    @RequestMapping(value = "/getAllPosts", method = RequestMethod.GET)
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }


    //
    @RequestMapping(value = "/{userId}/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post createPost(@PathVariable(value = "userId") Long userId, @RequestBody Post post) {
        return postService.createPost(userId, post);
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public Optional<Post> getPostById(@PathVariable(value = "postId") Long postId) {
        return postService.getPostById(postId);
    }


    @RequestMapping(value = "/userId/postId", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post updatePost(@PathVariable(value = "userId") Long userId,@PathVariable(value = "postId") Long postId, @RequestBody Post post) {
        return postService.updatePostById( userId,postId, post);
    }

/**
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
*/
    @DeleteMapping("/{id}")
    public ResponseEntity deletePostById(@PathVariable  Long id) {
        this.postService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}/rate")
    public ResponseEntity<Post> ratePost(@PathVariable Long id, @RequestBody Integer buttonState) {
        postService.rate(id, buttonState);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/search")
    public ResponseEntity<Collection<Post>> searchPost(String q) {
        return new ResponseEntity<>(postService.search(q), HttpStatus.OK);
    }
}
