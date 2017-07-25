package com.example.demo.web;

import com.example.demo.batch.job.PagePostJob;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by jerry on 2017/7/22.
 */
@RestController
@RequestMapping("/api/job/trigger")
public class TriggerJobResource {

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private PagePostJob pagePostJob;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void trigger() throws
            JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("user_id", "100340840340866")
                .addLong("times", System.currentTimeMillis())
                .toJobParameters();

        Job job = pagePostJob.getPagePostJob();
        JobExecution execution = jobLauncher.run(job, jobParameters);
    }
}
