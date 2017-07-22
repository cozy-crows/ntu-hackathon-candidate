package com.example.demo.properties;

import lombok.Data;

/**
 * Created by jerry on 2017/7/22.
 * 用來讀取外部 application.yml 的 job.cron-setting 的設定
 */
@Data
public class JobProperties {

    private String timeZone;
    private String pagePostJobCron;
}
