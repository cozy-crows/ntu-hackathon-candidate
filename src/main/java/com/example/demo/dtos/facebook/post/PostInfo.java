package com.example.demo.dtos.facebook.post;

import com.example.demo.dtos.facebook.post.comment.Comments;
import com.example.demo.dtos.facebook.post.comment.Likes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by jerry on 2017/7/22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PostInfo {
    @JsonProperty("id")
    private String id;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("message")
    private String message;
    @JsonProperty("actions")
    private List<Action> actions = null;
    @JsonProperty("attachments")
    private Attachments attachments;
    @JsonProperty("story")
    private String story;
    @JsonProperty("story_tags")
    private List<StoryTag> storyTags = null;
    @JsonProperty("message_tags")
    private List<MessageTag> messageTags = null;
    @JsonProperty("comments")
    private Comments comments;
    @JsonProperty("likes")
    private Likes likes;
}
