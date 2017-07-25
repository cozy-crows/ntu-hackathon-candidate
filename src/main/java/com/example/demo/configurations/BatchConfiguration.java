package com.example.demo.configurations;

import liquibase.integration.spring.SpringLiquibase;
import org.quartz.CronTrigger;
import org.quartz.spi.JobFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Created by kuowenhao on 2017/6/22.
 */

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
@EnableBatchProcessing
public class BatchConfiguration {

    @Inject
    private ResourceLoader resourceLoader;

    @Inject
    private TaskExecutor taskExecutor;

    @Bean
    public JobFactory jobFactory(
        ApplicationContext applicationContext,
        // injecting SpringLiquibase to ensure liquibase is already initialized and created the quartz tables
        SpringLiquibase springLiquibase
    ) {

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(
        DataSource dataSource,
        PlatformTransactionManager transactionManager,
        JobFactory jobFactory,
        CronTrigger[] triggers) throws IOException {

        CleanableSchedulerFactoryBean factory = new CleanableSchedulerFactoryBean();
        factory.setCleanupQuartzDbTables(true);
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setTransactionManager(transactionManager);
        factory.setConfigLocation(resourceLoader.getResource("classpath:/config/quartz.properties"));
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setTaskExecutor(taskExecutor);
        factory.setTriggers(triggers);
        return factory;
    }

}
