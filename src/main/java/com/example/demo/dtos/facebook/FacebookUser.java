package com.example.demo.dtos.facebook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by jerry on 2017/7/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FacebookUser {
    @JsonProperty("id")
    private String id;
    @JsonProperty("about")
    private String about;
    @JsonProperty("birthday")
    private String birdthday;
    @JsonProperty("email")
    private String email;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("link")
    private String link;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("name_format")
    private String nameFormat;
    @JsonProperty("public_key")
    private String publicKey;
    @JsonProperty("quotes")
    private String quotes;
    @JsonProperty("relationship_status")
    private String relationshipStatus;
    @JsonProperty("web_site")
    private String webSite;
}
