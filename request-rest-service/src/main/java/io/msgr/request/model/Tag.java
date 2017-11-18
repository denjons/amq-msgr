package io.msgr.request.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Tag {

    @NotNull
    @Size(min = 1, max = 20)
    String tag;

    public Tag() {
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return String.format("{'tag': '%s'}", tag);
    }
}
