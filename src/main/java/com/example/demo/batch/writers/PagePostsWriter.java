package com.example.demo.batch.writers;

import com.example.demo.dtos.facebook.post.PostInfo;
import com.example.demo.entities.FbPage;
import com.example.demo.entities.PagePost;
import com.example.demo.repositories.PagePostRepository;
import com.example.demo.services.FileIOService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/22.
 */
@Slf4j
@Component
@StepScope
public class PagePostsWriter implements ItemWriter<Map<FbPage, List<PostInfo>>> {

    @Inject
    private PagePostRepository pagePostRepository;

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private FileIOService fileIOService;

    @Override
    public void write(List<? extends Map<FbPage, List<PostInfo>>> postMapList) throws Exception {
        postMapList.parallelStream()
                .forEach(postMap -> {
                    updatePagePost(postMap);

                    writeToJsonFile(postMap);
                });
    }

    private void writeToJsonFile(Map<FbPage, List<PostInfo>> postMap) {
        postMap.entrySet()
                .stream()
                .forEach(entrySet -> {
                    FbPage fbPage = entrySet.getKey();
                    List<PostInfo> postInfoList = entrySet.getValue();

                    // 建立資料夾
                    String filePath = fileIOService.timeStampToFilePath(fbPage.getPageId());
                    File file = fileIOService.checkOrCreateFile(filePath);

                    // 產生 json
                    postInfoList.stream()
                            .forEach(postInfo -> {
                                try {
                                    String json = objectMapper.writeValueAsString(postInfo);
                                    fileIOService.appendToFile(file, json);

                                } catch (JsonProcessingException e) {
                                    log.error("PostInfo[{}] Json Processing Exception: {}",
                                            postInfo.getId(), e.getMessage());
                                }
                            });
                });

        log.info("write page posts to file success.");

    }

    private void updatePagePost(Map<FbPage, List<PostInfo>> postMap) {
        postMap.entrySet()
                .stream()
                .forEach(entrySet -> {
                    List<PostInfo> postInfoList = entrySet.getValue();

                    // 轉為 dao
                    List<PagePost> pagePostList = postInfoList.stream()
                        .map(postInfo -> {
                            PagePost pagePost = new PagePost();

                            pagePost.setPostId(postInfo.getId());
                            pagePost.setCreatedTime(postInfo.getCreatedTime());
                            pagePost.setMessage(postInfo.getMessage());

                            if (Objects.nonNull(postInfo.getStory())) {
                                pagePost.setStory(postInfo.getStory());
                            }
                            return pagePost;
                        }).collect(Collectors.toList());

                    pagePostRepository.save(pagePostList);
                });

        log.info("update page posts to success.");

    }


}
