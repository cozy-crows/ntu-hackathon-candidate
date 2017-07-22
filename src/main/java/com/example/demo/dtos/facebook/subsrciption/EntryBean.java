package com.example.demo.dtos.facebook.subsrciption;

import lombok.Data;

import java.util.List;

@Data
public class EntryBean {
    /**
     * changes : [{"field":"name"}]
     * id : 0
     * time : 1500702492
     */

    private String id;
    private int time;
    private List<ChangesBean> changes;

}
