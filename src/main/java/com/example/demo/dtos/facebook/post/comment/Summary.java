package com.example.demo.dtos.facebook.post.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Summary {
    @JsonProperty("order")
    private String order;
    @JsonProperty("total_count")
    private Integer totalCount;
    @JsonProperty("can_comment")
    private Boolean canComment;
}
