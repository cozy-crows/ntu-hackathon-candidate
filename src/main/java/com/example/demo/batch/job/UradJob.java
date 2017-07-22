package com.example.demo.batch.job;

import com.example.demo.batch.listeners.ProtocolListener;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Date;
import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/7.
 * http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-02.html#TutorialLesson2-JobsAndTriggers
 * <p>
 * 此抽象 class 實作 Job Interface, 並 override execute 方法
 * 當其他 Job 繼承此 class 時, 須依照其規格描述
 * JobDetailFactoryBean 與 CronTriggerFactoryBean
 * JobDetailFactoryBean 用來描述與定義 Job instance 的細節
 * CronTriggerFactoryBean 用來描述與定義 CronTrigger instance 的細節
 * <p>
 * 解釋有誤, 請協助修正
 */
@Slf4j
public abstract class UradJob implements Job {

    @Inject
    protected JobBuilderFactory jobBuilderFactory;

    @Inject
    protected StepBuilderFactory stepBuilderFactory;

    @Inject
    protected JobLauncher jobLauncher;

    @Inject
    protected PlatformTransactionManager platformTransactionManager;

    @Inject
    protected ProtocolListener protocolListener;

    /**
     * 實際執行 Job 的地方
     *
     * @param executionContext
     */
    @Override
    public void execute(JobExecutionContext executionContext) {
//        JobDetail jobDetail = executionContext.getJobDetail();
//        JobDataMap jobDataMap = jobDetail.getJobDataMap();

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("run.time", new Date())
                .toJobParameters();

        try {
            JobExecution jobExecution = jobLauncher.run(getJob(), jobParameters);
            executionContext.setResult(jobExecution.getStatus().getBatchStatus());

        } catch (JobExecutionAlreadyRunningException e) {
            log.warn("Job Execution Already Running Exception: {}", e.getMessage());

        } catch (JobRestartException e) {
            log.warn("Job Restart Exception: {}", e.getMessage());

        } catch (JobInstanceAlreadyCompleteException e) {
            log.warn("Job Instance Already Complete Exception: {}", e.getMessage());

        } catch (JobParametersInvalidException e) {
            log.warn("Job Parameters Invalid Exception: {}", e.getMessage());

        }
    }

    /**
     * Generate JobDetailFactoryBean
     * 用來定義並建立 Job 的 instance
     * http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-02.html
     *
     * @return {@link JobDetailFactoryBean}
     */
    protected abstract JobDetailFactoryBean jobDetailFactoryBean();

    /**
     * Generate CronTriggerFactoryBean
     * 用來定義並建立 CronTrigger 的 instance
     * http://www.quartz-scheduler.org/documentation/quartz-2.2.x/tutorials/tutorial-lesson-06.html
     *
     * @param jobDetail JobDetail Instance
     * @return {@link CronTriggerFactoryBean}
     */
    protected abstract CronTriggerFactoryBean cronTriggerFactoryBean(JobDetail jobDetail);

    /**
     * 取得 Job 的 instance
     *
     * @return {@link org.springframework.batch.core.Job}
     */
    protected abstract org.springframework.batch.core.Job getJob();

    /**
     * Threads Pool for Step
     * 提供 Multi-threads 給 Step 使用
     *
     * @return
     */
    protected TaskExecutor getTaskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Step-pool-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.afterPropertiesSet();
        return executor;
    }
}
