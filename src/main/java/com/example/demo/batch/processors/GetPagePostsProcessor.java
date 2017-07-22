package com.example.demo.batch.processors;

import com.example.demo.dtos.facebook.PageInfo;
import com.example.demo.entities.FbPage;
import com.example.demo.entities.PagePost;
import com.example.demo.services.facebook.client.FacebookClient;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/22.
 */
@Slf4j
@Component
@StepScope
public class GetPagePostsProcessor implements ItemProcessor<FbPage, List<PageInfo>> {

    @Value("${facebook.access-token}")
    private String accessToken;

    @Inject
    private FacebookClient facebookClient;

    @Override
    public List<PageInfo> process(FbPage page) throws Exception {
        log.info("GetPagePostsProcessor process: {}", page.getName());
        return null;
//        return facebookClient.getAllPageInfo(page.getPageId(), accessToken);
    }
}
