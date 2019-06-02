package org.cephas.acdia.payload;

import org.cephas.acdia.model.Post;
import org.cephas.acdia.model.User;

/**
 * Created by admin on 21-05-19.
 */
public class CommentResponse {
    private String text;
    private User user;
    private Post post;

    public CommentResponse() {
    }

    public CommentResponse(String text, User user, Post post) {
        this.text = text;
        this.user = user;
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
