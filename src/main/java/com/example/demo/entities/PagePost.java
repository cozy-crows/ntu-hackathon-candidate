package com.example.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Created by jerry on 2017/7/22.
 */
@Entity
@Table(name = "page_post")
@Data
public class PagePost {

    @Id
    @Column(name = "post_id")
    private String postId;

    @Lob
    @Column(name = "message")
    private String message;

    @Column(name = "story")
    private String story;

    @Column(name = "created_time")
    private String createdTime;

}
