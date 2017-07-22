package com.example.demo.dtos.facebook.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Image {
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("src")
    private String src;
    @JsonProperty("width")
    private Integer width;
}
