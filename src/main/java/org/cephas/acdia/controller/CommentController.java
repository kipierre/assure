package org.cephas.acdia.controller;

import org.cephas.acdia.exception.ResourceNotFoundException;
import org.cephas.acdia.model.Comment;
import org.cephas.acdia.repository.CommentRepository;
import org.cephas.acdia.repository.PostRepository;
import org.cephas.acdia.service.CommentService;
import org.cephas.acdia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by admin on 08-05-19.
 */

@RestController
@RequestMapping("/api/auth")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/getAllComments", method = RequestMethod.GET)
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.GET)
    public Optional<Comment> getCommentById(@PathVariable(value = "commentId")Long commentId) {
        return commentService.getCommentById(commentId);
    }
    @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCommentById(long commentId) {
        return commentService.deleteCommentById(commentId);
    }
    @RequestMapping(value = "/{userId}/{postId}/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

    public Comment createComment(@PathVariable(value = "userId") Long userId,
                                  @PathVariable (value = "postId") Long postId,
                                  @Valid @RequestBody Comment comment) {
        return commentService.createComment(userId,postId, comment);
    }

    @PutMapping("/{userId}/{postId}/comment/{commentId}")

    public Comment updateComment(@PathVariable(value = "userId") Long userId,
                                  @PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        return commentService.updateComment( userId,postId, commentId, commentRequest);
    }


}
