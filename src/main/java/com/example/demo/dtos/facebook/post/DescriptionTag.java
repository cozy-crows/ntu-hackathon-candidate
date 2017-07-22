package com.example.demo.dtos.facebook.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DescriptionTag {

    @JsonProperty("id")
    private String id;
    @JsonProperty("length")
    private Integer length;
    @JsonProperty("name")
    private String name;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("type")
    private String type;

}
