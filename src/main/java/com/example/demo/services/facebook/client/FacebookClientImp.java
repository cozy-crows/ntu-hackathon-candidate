package com.example.demo.services.facebook.client;

import com.example.demo.dtos.facebook.post.PostInfo;
import com.example.demo.dtos.facebook.unit.NodeList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/21.
 */
@Slf4j
@Service
public class FacebookClientImp implements FacebookClient {

    private final static String DEFAULT_LIMIT = "10";

    @Inject
    private FacebookService facebookService;

    @Override
    public List<PostInfo> getAllPosts(String pageId, String accessToken) throws IOException {
        NodeList<PostInfo> postInfoNodeList;
        String nextPage = null;
        List<PostInfo> postInfos = new ArrayList<>();

        do {
            Response<NodeList<PostInfo>> response
                    = facebookService.getPagePosts(pageId, accessToken, DEFAULT_LIMIT, nextPage, null).execute();

            if (!response.isSuccessful() && Objects.nonNull(response.body())) {
                log.warn("Request {}'s Page posts Fail.", pageId);

                if (Objects.nonNull(response.errorBody())) {
                    log.warn("Facebook Error Message: {}", response.errorBody().toString());
                }
            }

            postInfoNodeList = response.body();

            if (Objects.nonNull(postInfoNodeList.getAfterPageHash())) {
                nextPage = postInfoNodeList.getAfterPageHash();
            }

            postInfos.addAll(postInfoNodeList.getData());

        } while (postInfoNodeList.hasNextPage());

        return postInfos;
    }
}
