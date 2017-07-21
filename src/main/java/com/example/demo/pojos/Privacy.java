package com.example.demo.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jerry on 2017/7/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Privacy {

    @JsonProperty("allow")
    private String allow;
    @JsonProperty("deny")
    private String deny;
    @JsonProperty("description")
    private String description;
    @JsonProperty("friends")
    private String friends;
    @JsonProperty("value")
    private String value;
}
