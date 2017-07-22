package com.example.demo.services.facebook.client;

import com.example.demo.dtos.facebook.post.PostInfo;
import com.example.demo.properties.FacebookProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookClientIntegrationTest {

    @Inject
    private FacebookProperties facebookProperties;

    @Inject
    private FacebookClient facebookClient;

    @Test
    public void assertGetAllPostSuccess() throws IOException {
        String pageId = "312727242083229";
        String accessToken = facebookProperties.getAccessToken();
        List<PostInfo> postInfoList = facebookClient.getAllPosts(pageId, accessToken);

        Assert.assertTrue(1 < postInfoList.size());
    }
}
