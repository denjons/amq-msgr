package org.msgr.watch.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Watch {

    @Id
    public String _id;

    @NotNull
    @Min(1)
    public List<String> tags;

    public List<String> TagRequestSetIds;
 }
