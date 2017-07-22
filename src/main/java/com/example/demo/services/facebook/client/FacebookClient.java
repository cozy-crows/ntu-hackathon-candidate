package com.example.demo.services.facebook.client;

import com.example.demo.dtos.facebook.post.PostInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by jerry on 2017/7/21.
 */
public interface FacebookClient {

    List<PostInfo> getAllPosts(final String pageId,
                               final String accessToken) throws IOException;
}
