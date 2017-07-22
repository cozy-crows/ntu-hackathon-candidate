package com.example.demo.batch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by jerry on 2017/7/22.
 */
@Slf4j
@Component
@JobScope
public class ProtocolListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        StringBuilder protocol = new StringBuilder();
        protocol.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        protocol.append("Protocol for " + jobExecution.getJobInstance().getJobName() + " \n");
        protocol.append("  Created     : " + jobExecution.getCreateTime() + "\n");
        protocol.append("  Started    : " + jobExecution.getStartTime() + "\n");
        protocol.append("  Status      : " + jobExecution.getStatus() + "\n");
        protocol.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

        protocol.append("Job-Parameter: \n");
        JobParameters jp = jobExecution.getJobParameters();
        for (Iterator<Map.Entry<String, JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<String, JobParameter> entry = iter.next();
            protocol.append("  " + entry.getKey() + "=" + entry.getValue() + "\n");
        }
        protocol.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

        for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
            protocol.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
            protocol.append("Step for " + stepExecution.getStepName() + " \n");
            protocol.append("  Started     : " + stepExecution.getStartTime() + " \n");
            protocol.append("  Status      : " + stepExecution.getStatus() + " \n");
            protocol.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        }
        log.info(protocol.toString());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        StringBuilder protocol = new StringBuilder();
        protocol.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        protocol.append("Protocol for " + jobExecution.getJobInstance().getJobName() + " \n");
        protocol.append("  Started     : " + jobExecution.getStartTime() + "\n");
        protocol.append("  Finished    : " + jobExecution.getEndTime() + "\n");
        protocol.append("  Exit-Code   : " + jobExecution.getExitStatus().getExitCode() + "\n");
        protocol.append("  Exit-Descr. : " + jobExecution.getExitStatus().getExitDescription() + "\n");
        protocol.append("  Status      : " + jobExecution.getStatus() + "\n");
        protocol.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

        protocol.append("Job-Parameter: \n");
        JobParameters jp = jobExecution.getJobParameters();
        for (Iterator<Map.Entry<String, JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<String, JobParameter> entry = iter.next();
            protocol.append("  " + entry.getKey() + "=" + entry.getValue() + "\n");
        }
        protocol.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

        for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
            protocol.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
            protocol.append("Step for " + stepExecution.getStepName() + " \n");
            protocol.append("  Started     : " + stepExecution.getStartTime() + " \n");
            protocol.append("  Finished    : " + stepExecution.getEndTime() + " \n");
            protocol.append("  Status      : " + stepExecution.getStatus() + " \n");
            protocol.append("  WriteCount  : " + stepExecution.getWriteCount() + "\n");
            protocol.append("  Commits     : " + stepExecution.getCommitCount() + "\n");
            protocol.append("  SkipCount   : " + stepExecution.getSkipCount() + "\n");
            protocol.append("  Rollbacks   : " + stepExecution.getRollbackCount() + "\n");
            protocol.append("  Filter      : " + stepExecution.getFilterCount() + "\n");
            protocol.append("  Exit-Code   : " + stepExecution.getExitStatus().getExitCode() + "\n");
            protocol.append("  Exit-Descr. : " + stepExecution.getExitStatus().getExitDescription() + "\n");
            protocol.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        }
        log.info(protocol.toString());
    }
}
