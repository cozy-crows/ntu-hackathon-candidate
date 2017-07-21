package com.example.demo.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by jerry on 2017/7/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PostData {
    @JsonProperty("coordinates")
    private Coordinates coordinates;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("description")
    private String description;
    @JsonProperty("from")
    private From from;
    @JsonProperty("full_picture")
    private String fullPicture;
    @JsonProperty("id")
    private String id;
    @JsonProperty("actions")
    private List<Action> actions = null;
    @JsonProperty("is_published")
    private Boolean isPublished;
    @JsonProperty("is_spherical")
    private Boolean isSpherical;
    @JsonProperty("link")
    private String link;
    @JsonProperty("message")
    private String message;
    @JsonProperty("multi_share_optimized")
    private Boolean multiShareOptimized;
    @JsonProperty("name")
    private String name;
    @JsonProperty("object_id")
    private String objectId;
    @JsonProperty("parent_id")
    private String parentId;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("privacy")
    private Privacy privacy;
    @JsonProperty("promotable_id")
    private String promotableId;
    @JsonProperty("promotion_status")
    private String promotionStatus;
    @JsonProperty("status_type")
    private String statusType;
    @JsonProperty("story")
    private String story;
    @JsonProperty("story_tags")
    private List<StoryTag> storyTags = null;
    @JsonProperty("subscribed")
    private Boolean subscribed;
    @JsonProperty("timeline_visibility")
    private String timelineVisibility;
    @JsonProperty("type")
    private String type;
    @JsonProperty("updated_time")
    private String updatedTime;
    @JsonProperty("attachments")
    private Attachments attachments;
    @JsonProperty("comments")
    private Comments comments;
}
