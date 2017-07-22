package com.example.demo.batch.readers;

import com.example.demo.entities.FbPage;
import com.example.demo.repositories.FbPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;
import javax.inject.Inject;


/**
 * Created by jerry on 2017/7/22.
 */
@Slf4j
@Component
@StepScope
public class FbPageReader implements ItemReader<FbPage> {

    @Inject
    private FbPageRepository fbPageRepository;

    private List<FbPage> pageList;

    @BeforeStep
    public void init() {
        pageList = fbPageRepository.findAll();
    }

    @Override
    public FbPage read() throws Exception {
        if (!pageList.isEmpty()) {
            log.info("Facebook Page size: {}", pageList.size());
            return pageList.remove(0);
        }
        return null;
    }
}
