package com.al.o2o.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * @author Xiahuicheng
 * @PackageName:com.al.o2o.quartz
 * @ClassName:SchedulerUtil
 * @Description
 * @date2021/8/18 19:48
 */
public class SchedulerUtil {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        JobDetail jobDetail = JobBuilder.newJob(HelloQuartz.class)
                .withIdentity("myJob","group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","group1")
                .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
                .repeatForever()).build();

        //创建一个Scheduler工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        //获取一个Scheduler
        Scheduler scheduler = schedulerFactory.getScheduler();
        //立即开始
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);

    }
}
