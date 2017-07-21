package com.example.demo.services.facebook.client;

import com.example.demo.dtos.facebook.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/21.
 */
@Slf4j
@Service
public class FacebookClientIml implements FacebookClient {

    private final static String DEFAULT_LIMIT = "10";

    @Inject
    private FacebookService facebookService;

    @Override
    public List<PageInfo> getAllPageInfo(String pageId, String accessToken) throws IOException {
        return null;
    }
}
