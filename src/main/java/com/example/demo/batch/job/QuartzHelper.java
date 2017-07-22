package com.example.demo.batch.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by jerry on 2017/7/11.
 */
@Slf4j
public class QuartzHelper {

    public static final String JOB_GROUP_NAME = "JOB_GROUP_NAME";
    public static final String TRIGGER_GROUP_NAME = "TRIGGER_GROUP_NAME";

    public static JobDetailFactoryBean createJob(String jobGroup,
                                                 String jobName,
                                                 Class clazz,
                                                 Map<String, ?> param) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setGroup(jobGroup);
        jobDetailFactoryBean.setName(jobName);
        jobDetailFactoryBean.setJobClass(clazz);
        jobDetailFactoryBean.setJobDataAsMap(param);

        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);

        return jobDetailFactoryBean;
    }

    public static JobDetailFactoryBean createJob(Class clazz, Map<String, ?> param) {
        return createJob(JOB_GROUP_NAME, clazz.getName(), clazz, param);
    }

    public static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail,
                                                           String triggerGroup,
                                                           String triggerName,
                                                           String cronExpress,
                                                           String timeZone) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setGroup(triggerGroup);
        cronTriggerFactoryBean.setName(triggerName);
        cronTriggerFactoryBean.setCronExpression(cronExpress);
        cronTriggerFactoryBean.setJobDetail(jobDetail);

        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
        log.info("{}", calendar.getTime());
        cronTriggerFactoryBean.setStartTime(calendar.getTime());
        cronTriggerFactoryBean.setTimeZone(TimeZone.getTimeZone(timeZone));
        return cronTriggerFactoryBean;
    }

    public static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail,
                                                           String triggerName,
                                                           String cronExpress,
                                                           String timeZone) {
        return createCronTrigger(jobDetail, TRIGGER_GROUP_NAME, triggerName, cronExpress, timeZone);
    }

}
