package com.example.demo.configurations;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Objects;

/**
 * Created by kuowenhao on 2017/6/22.
 */

@Slf4j
public class CleanableSchedulerFactoryBean extends SchedulerFactoryBean {

    private boolean cleanupQuartzDbTables;
    private PlatformTransactionManager transactionManager;

    @Required
    public void setCleanupQuartzDbTables(boolean cleanupQuartzDbTables) {
        this.cleanupQuartzDbTables = cleanupQuartzDbTables;
    }

    @Override
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        super.setTransactionManager(transactionManager);
        this.transactionManager = transactionManager;
    }

    @Override
    protected Scheduler createScheduler(SchedulerFactory schedulerFactory, String schedulerName) throws SchedulerException {
        Scheduler scheduler = super.createScheduler(schedulerFactory, schedulerName);
        if (Objects.nonNull(scheduler) && cleanupQuartzDbTables) {
            cleanUpSchedulerInTransaction(scheduler);
        }
        return scheduler;
    }

    private void cleanUpSchedulerInTransaction(Scheduler scheduler) throws SchedulerException {
        TransactionStatus transactionStatus = null;
        if (Objects.nonNull(transactionManager)) {
            transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        }

        try {
            scheduler.clear();

        } catch (Throwable e) {
            if (Objects.nonNull(transactionStatus)) {
                try {
                    transactionManager.rollback(transactionStatus);
                } catch (TransactionException tex) {
                    log.error("Job registration exception overridden by rollback exception", tex);
                }
            }
            if (e instanceof SchedulerException) {
                throw (SchedulerException) e;
            }
            if (e instanceof Exception) {
                throw new SchedulerException("Registration of jobs and triggers failed: " + e.getMessage(), e);
            }
            throw new SchedulerException("Registration of jobs and triggers failed: " + e.getMessage());
        }

        if (Objects.nonNull(transactionStatus)) {
            transactionManager.commit(transactionStatus);
        }
    }
}
