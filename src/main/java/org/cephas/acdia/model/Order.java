package org.cephas.acdia.model;

/**
 * Created by admin on 10-05-19.
 */
import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private String content;
    private Date timestamp;

    public Order() {
    }

    public Order(String content, Date timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}