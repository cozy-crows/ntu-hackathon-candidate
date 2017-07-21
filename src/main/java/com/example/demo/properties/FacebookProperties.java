package com.example.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * Created by jerry on 2017/7/6.
 */
@Data
@ConfigurationProperties(prefix = "facebook")
public class FacebookProperties {

    @NotNull
    private String graphUrl;

    @NotNull
    private String accessToken;

    @NotNull
    private String userId;

    @NotNull
    private String pageHong;
}
