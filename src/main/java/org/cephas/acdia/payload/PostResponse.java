package org.cephas.acdia.payload;

import org.cephas.acdia.model.Tag;
import org.cephas.acdia.model.User;

/**
 * Created by admin on 20-05-19.
 */
public class PostResponse {

    private String title;
    private String description;
    private String content;
    private Long ratingPoints;
    private User user;
    private Tag tags ;

    public PostResponse() {
    }

    public PostResponse(String title, String description, String content, Long ratingPoints, User user, Tag tags) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.ratingPoints = ratingPoints;
        this.user = user;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getRatingPoints() {
        return ratingPoints;
    }

    public void setRatingPoints(Long ratingPoints) {
        this.ratingPoints = ratingPoints;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tag getTags() {
        return tags;
    }

    public void setTags(Tag tags) {
        this.tags = tags;
    }
}
