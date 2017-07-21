package com.example.demo.configurations;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.properties.FacebookProperties;
import com.example.demo.services.facebook.client.FacebookGraphApiBuilder;
import com.example.demo.services.facebook.client.FacebookService;
import com.example.demo.services.facebook.client.RetryInterceptor;

import javax.inject.Inject;

/**
 * Created by jerry on 2017/6/22.
 */
@Configuration
@EnableConfigurationProperties(FacebookProperties.class)
public class FacebookConfiguration {

    @Inject
    private FacebookProperties facebookProperties;

    @Bean
    public FacebookService facebookService() {
        return FacebookGraphApiBuilder
            .create()
            .apiEndPoint(facebookProperties.getGraphUrl())  // 讀取 application.yml 的 graph url
            .addInterceptor(new RetryInterceptor(2))        // 加入 retry 機制
            .build();
    }
}
