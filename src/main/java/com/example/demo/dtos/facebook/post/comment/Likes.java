package com.example.demo.dtos.facebook.post.comment;

import com.example.demo.dtos.facebook.unit.Paging;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Likes {
    @JsonProperty("data")
    private List<LikesData> data = null;
    @JsonProperty("paging")
    private Paging paging;
    @JsonProperty("summary")
    private Summary summary;
}
