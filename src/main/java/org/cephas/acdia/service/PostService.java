package org.cephas.acdia.service;

import org.cephas.acdia.exception.ResourceNotFoundException;
import org.cephas.acdia.model.Comment;
import org.cephas.acdia.model.Post;

import org.cephas.acdia.repository.CommentRepository;
import org.cephas.acdia.repository.PostRepository;
import org.cephas.acdia.repository.PostSearch;
import org.cephas.acdia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by admin on 20-05-19.
 */
@Service
public class PostService {
    private  PostRepository postRepository;
    private  CommentRepository commentRepository;
    private  PostSearch postSearch;
    private  UserRepository userRepository;
    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository,
                       PostSearch postSearch, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.postSearch = postSearch;
        this.userRepository = userRepository;
    }

    public Optional<Post> findById(Long Id) {
        return postRepository.findById(Id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post with id " + postId + " not found");
        }
        return postRepository.findById(postId);
    }


    public Post createPost( Long userId, Post post) {
        Set<Post> posts = new LinkedHashSet<>();
        return userRepository.findById(userId).map(user -> {
            post.setUser(user);
            Post post1 = postRepository.save(post);
            posts.add(post1);
            user.setPosts(posts);
            return post1;
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }


    public void delete( Long id) {
        Collection<Comment> relatedComments = commentRepository.findByPost_Id(id);
        if (relatedComments.size() > 0) {
            for (Comment comment : relatedComments) {
                commentRepository.deleteById(comment.getId());
            }
        }
        this.postRepository.deleteById(id);
    }
    public void rate( Long id, Integer buttonState) {
        Post foundPost = postRepository.findById(id).get();
        if (buttonState.equals(0)) {
            foundPost.setRatingPoints(foundPost.getRatingPoints() - 1);
        } else if (buttonState.equals(1)) {
            foundPost.setRatingPoints(foundPost.getRatingPoints() + 1);
        }
        postRepository.save(foundPost);
    }

    @SuppressWarnings("unchecked")
    public Collection search(String query) {
        Collection<Post> searchResults;
        try {
            searchResults = postSearch.search(query);
            return searchResults.stream()
                    . map(post -> {
                        post.getTitle();
                       // post.getDescription();
                      //  post.getContent();
                        return postRepository.findAll();
                    })
                    .collect(Collectors.toList());
        } catch (Exception ignored) {

        }
        return null;
    }



    public Post updatePostById( Long userId, Long postId,Post postRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return postRepository.findById(postId)
                .map(post -> {
                    post.setTitle(postRequest.getTitle());
                    post.setDescription(postRequest.getDescription());
                    post.setContent(postRequest.getContent());
                    return postRepository.save(postRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + "not found"));
    }



}
