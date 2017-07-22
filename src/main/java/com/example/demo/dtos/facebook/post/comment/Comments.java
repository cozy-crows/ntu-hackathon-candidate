package com.example.demo.dtos.facebook.post.comment;

import com.example.demo.dtos.facebook.unit.Paging;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Comments {
    @JsonProperty("data")
    private List<CommentsData> data = null;
    @JsonProperty("paging")
    private Paging paging;
    @JsonProperty("summary")
    private Summary summary;

    public Integer getSummaryTotalCount() {
        if(Objects.nonNull(summary) &&
                Objects.nonNull(summary.getTotalCount())) {
            return summary.getTotalCount();
        }
        return 0;
    }
}
