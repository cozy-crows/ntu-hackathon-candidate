package com.example.demo.dtos.facebook.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PostInfo {
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("message")
    private String message;
    @JsonProperty("id")
    private String id;
    @JsonProperty("story")
    private String story;
}
