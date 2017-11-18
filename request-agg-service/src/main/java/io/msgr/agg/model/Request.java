package io.msgr.agg.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Request {

    @Id
    public String _id;
    public String userId;
    public String request;
    public Date requestDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tag_request_set", joinColumns = @JoinColumn(name = "requestId",
            referencedColumnName = "_id"), inverseJoinColumns = @JoinColumn(name = "tag", referencedColumnName = "tag"))
    public List<Tag> tags;

    public Request() {
    }

    public Request(String _id, String userId, String request, List<Tag> tags) {
        this._id = _id;
        this.userId = userId;
        this.request = request;
        this.tags = tags;
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}