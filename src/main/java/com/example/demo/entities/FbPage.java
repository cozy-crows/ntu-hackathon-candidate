package com.example.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jerry on 2017/7/22.
 */
@Entity
@Table(name = "fb_page")
@Data
public class FbPage {

    @Id
    @Column(name = "page_id")
    private String pageId;

    @Column(name = "name")
    private String name;
}
