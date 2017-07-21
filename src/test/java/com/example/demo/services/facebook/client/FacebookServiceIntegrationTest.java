package com.example.demo.services.facebook.client;

import com.example.demo.pojos.FacebookUser;
import com.example.demo.pojos.PageInfo;
import com.example.demo.properties.FacebookProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Response;

import java.io.IOException;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/21.
 * 驗證 Facebook 端點都正常回傳資料
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookServiceIntegrationTest {

    @Inject
    private FacebookProperties facebookProperties;

    @Inject
    private FacebookService facebookService;

    @Test
    public void assertFacebookServiceNotNull() {
        Assert.assertNotNull(facebookService);
    }

    @Test
    public void assertGetUserInfoSuccess() throws IOException {
        // 丁小宇 Test
        String userId = "100340840340866";
        String accessToken = facebookProperties.getAccessToken();
        Response<FacebookUser> fbUser = facebookService.getUser(userId, accessToken).execute();
        Assert.assertEquals(200, fbUser.code());
    }

    @Test
    public void assertGetPagePostsSuccess() throws IOException {
        // 還我票票花
        String pageId = "312727242083229";
        String accessToken = facebookProperties.getAccessToken();

        Response<PageInfo> pageInfo = facebookService.getPagePosts(pageId, accessToken).execute();
        Assert.assertEquals(200, pageInfo.code());
    }
}
