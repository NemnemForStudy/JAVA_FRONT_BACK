package com.nemnem.board.common.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class SampleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("SampleJob excute!!");
    }

    @Bean
    public JobDetail jobdetail(){
        return JobBuilder.newJob().ofType(SampleJob.class)
        .storeDurably()
        .withIdentity("Test SampleJob Detail")
        .withDescription("SampleJob Detail 테스트")
        .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("2 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(jobDetail)
            .withIdentity("Test Sample Trigger")
            .withDescription("Sample Trigger 테스트")
            .withSchedule(cronSchedule)
            .build();
    }
}
