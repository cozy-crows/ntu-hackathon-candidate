package com.example.demo.dtos.facebook;

import com.example.demo.dtos.facebook.unit.Paging;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by jerry on 2017/7/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Comments {

    @JsonProperty("data")
    private List<CommentsData> data = null;
    @JsonProperty("paging")
    private Paging paging;

}
