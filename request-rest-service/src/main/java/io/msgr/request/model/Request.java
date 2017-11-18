package io.msgr.request.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Request {

    @Id
    public String _id;
    @NotNull
    @Size(min = 20)
    String userId;
    @NotNull
    @Size(min = 1, max = 250)
    String request;
    @NotNull
    @Size(min = 1, max = 10)
    List<Tag> tags;

    public Request() {
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

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(50);
        builder.append("{");
        builder.append("'_id': '");
        builder.append(_id);
        builder.append("',");
        builder.append("'userId': '");
        builder.append(userId);
        builder.append("',");
        builder.append("'request': '");
        builder.append(request);
        builder.append("',");
        builder.append("'tags': [");
        for (Tag tag : tags) {
            builder.append(tag);
            builder.append(",");
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append("]}");
        return builder.toString();
    }
}
