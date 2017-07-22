package com.example.demo.dtos.facebook.subsrciption;

import lombok.Data;

import java.util.List;

@Data
public class SubscriptionUpdateBean {

    /**
     * entry : [{"changes":[{"field":"name"}],"id":"0","time":1500702492}]
     * object : page
     */

    private String object;
    private List<EntryBean> entry;

}
