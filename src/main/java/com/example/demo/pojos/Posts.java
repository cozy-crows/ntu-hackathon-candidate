package com.example.demo.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jerry on 2017/7/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Posts {
    @JsonProperty("data")
    private List<PostData> data = null;
    @JsonProperty("paging")
    private Paging paging;
}
