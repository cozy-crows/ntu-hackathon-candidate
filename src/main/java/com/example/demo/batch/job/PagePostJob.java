package com.example.demo.batch.job;

import com.example.demo.batch.processors.GetPagePostsProcessor;
import com.example.demo.batch.readers.FbPageReader;
import com.example.demo.batch.writers.PagePostsWriter;
import com.example.demo.dtos.facebook.post.PostInfo;
import com.example.demo.entities.FbPage;
import com.example.demo.properties.JobProperties;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDetail;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/22.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(JobProperties.class)
@ConditionalOnProperty(name = "quartz.enabled")
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PagePostJob extends UradJob {

    private static final String JOB_NAME = "PAGE_POST_JOB";
    private static final String TRIGGER_NAME = "PAGE_POST_JOB_TRIGGER";
    private static final String STEP_NAME = "PAGE_POST_JOB_STEP";

    @Value("${job.page-post-job-cron}")
    private String cronExpress;

    @Value("${job.time-zone}")
    private String timeZone;

    @Inject
    private FbPageReader fbPageReader;

    @Inject
    private GetPagePostsProcessor getPagePostsProcessor;

    @Inject
    private PagePostsWriter pagePostsWriter;

    @Override
    @Bean(name = JOB_NAME)
    protected JobDetailFactoryBean jobDetailFactoryBean() {
        return QuartzHelper.createJob(PagePostJob.class, Maps.newHashMap());
    }

    @Override
    @Bean(name = TRIGGER_NAME)
    protected CronTriggerFactoryBean cronTriggerFactoryBean(@Qualifier(JOB_NAME) JobDetail jobDetail) {
        return QuartzHelper.createCronTrigger(jobDetail, TRIGGER_NAME, cronExpress, timeZone);
    }

    @Override
    protected Job getJob() {
        return jobBuilderFactory
                .get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(protocolListener)
                .start(pageJobStep())
                .build();
    }

    private Step pageJobStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .allowStartIfComplete(true)     // 如果 step 已經完成, 可以重新執行
                .<FbPage, Map<FbPage, List<PostInfo>>>chunk(1)
                .reader(fbPageReader)
                .processor(getPagePostsProcessor)
                .writer(pagePostsWriter)
                .taskExecutor(getTaskExecutor())
                .build();
    }

    public Job getPagePostJob() {
        return getJob();
    }
}
