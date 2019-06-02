package org.cephas.acdia.service;

import org.cephas.acdia.exception.ResourceNotFoundException;
import org.cephas.acdia.model.Comment;

import org.cephas.acdia.repository.CommentRepository;
import org.cephas.acdia.repository.PostRepository;
import org.cephas.acdia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 20-05-19.
 */
@Service
public class CommentService {

    @Autowired

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final UserRepository userRepository;
    @Autowired
    public CommentService( PostRepository postRepository,
                          CommentRepository commentRepository,
                          UserRepository userRepository) {

        this.postRepository = postRepository;
        this.commentRepository = commentRepository;

        this.userRepository = userRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment with id " + commentId + " not found");
        }
        return commentRepository.findById(commentId);
    }
    public Comment createComment(Long userId ,Long postId, Comment comment) {

        Set<Comment> comments = new LinkedHashSet<>();
         return   userRepository.findById(userId).map(user -> {
             comment.setUser(user);
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);

            Comment comment1 = commentRepository.save(comment);
            comments.add(comment1);
            post.setComments(comments);
            return comment1;
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
         }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    public Comment updateComment( Long userId ,Long postId, Long commentId, Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }
        return commentRepository.findById(commentId)
                .map(comment -> {
               comment.setText(commentRequest.getText());
                   return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }


    public ResponseEntity<Object> deleteCommentById(long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("CommentId with id " + commentId + " not found");
        }

        commentRepository.deleteById(commentId);

        return ResponseEntity.ok().build();

    }


    public List<Comment> getCommentsByPostId( Long postId) {
      //  System.out.println(postId);
      //  System.out.println(commentRepository.findByPostId(postId));
        return commentRepository.findByPostId(postId);
    }

}
