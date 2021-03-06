package com.example.demo.dtos.facebook.unit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class NodeList<T> {
    @JsonProperty("data")
    private List<T> data = null;

    @JsonProperty("paging")
    private Paging paging;

    public Boolean hasNextPage() {
        if (Objects.isNull(paging) || Objects.isNull(paging.getNext())) {
            return false;
        }
        return !paging.getNext().isEmpty();
    }

    public Boolean hasPreviousPage() {
        if (Objects.isNull(paging) || Objects.isNull(paging.getPrevious())) {
            return false;
        }
        return !paging.getPrevious().isEmpty();
    }

    public String getAfterPageHash() {
        if (Objects.nonNull(paging) &&
                Objects.nonNull(paging.getCursors()) &&
                Objects.nonNull(paging.getCursors().getAfter())) {

            return paging.getCursors().getAfter();
        }
        return null;
    }

    public String getBeforePageHash() {
        if (Objects.nonNull(paging) &&
                Objects.nonNull(paging.getCursors()) &&
                Objects.nonNull(paging.getCursors().getBefore())) {

            return paging.getCursors().getBefore();
        }
        return null;
    }
}
