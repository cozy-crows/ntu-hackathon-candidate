package com.example.demo.dtos.facebook.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SubAttachmentsData {
    @JsonProperty("media")
    private Media media;
    @JsonProperty("target")
    private Target target;
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("description")
    private String description;
}
