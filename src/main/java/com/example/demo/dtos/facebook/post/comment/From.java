package com.example.demo.dtos.facebook.post.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class From {

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
}
