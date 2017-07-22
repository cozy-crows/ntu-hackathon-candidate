package com.example.demo.batch.writers;

import com.example.demo.dtos.facebook.PageInfo;
import com.example.demo.entities.PagePost;
import com.example.demo.repositories.PagePostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/22.
 */
@Slf4j
@Component
@StepScope
public class PagePostsWriter implements ItemWriter<List<PageInfo>> {

    @Inject
    private PagePostRepository pagePostRepository;

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public void write(List<? extends List<PageInfo>> pageInfoLists) throws Exception {
        pageInfoLists.parallelStream()
                .map(pageInfoList -> {
                  return null;
                });
    }

    private void updatePagePost(List<PageInfo> pageInfoList) {

    }

    public void writeToJsonFile(List<PageInfo> pageInfoList) {

    }
}
