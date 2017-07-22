package com.example.demo.dtos.facebook.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AttachmentsData {
    @JsonProperty("subattachments")
    private SubAttachments subAttachments;
    @JsonProperty("target")
    private Target target;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("description")
    private String description;
    @JsonProperty("media")
    private Media media;
    @JsonProperty("description_tags")
    private List<DescriptionTag> descriptionTags = null;
}
