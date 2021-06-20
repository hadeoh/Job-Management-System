package com.payoneer.jobmanagementsystem.components;

import com.payoneer.jobmanagementsystem.services.CronService;
import com.payoneer.jobmanagementsystem.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executor;

@Component
@Slf4j
@RequiredArgsConstructor
public class DynamicScheduler implements SchedulingConfigurer {

    private final Executor executor;
    private final CronService cronService;
    private final JobService jobService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(executor);
        scheduledTaskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        log.info("The job has started running");
                        jobService.processBatch();
                    }
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext context) {
                        return new CronTrigger(cronService.getCronExpression().getCronExpression()).nextExecutionTime(context);
                    }
                }
        );
    }
}
