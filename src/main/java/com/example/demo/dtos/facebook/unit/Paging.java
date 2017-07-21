package com.example.demo.dtos.facebook.unit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jerry on 2017/7/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class Paging {
    @JsonProperty("cursors")
    private Cursors cursors;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private String previous;
}
