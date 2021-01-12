package com.his.config.etc;

import com.his.task.ReturnVehicleReminderSMS;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * Description: Quartz配置类
 * Date: 20-12-22
 *
 * @author yh
 */
//@Configuration
//@EnableScheduling
public class QuartzConfig {

    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetail(ReturnVehicleReminderSMS task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         * 是否并发执行
         * 例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         * 如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);
        //为需要执行的实体类对应的对象
        jobDetail.setTargetObject(task);
        /*
         * sayHello为需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法
         */
        jobDetail.setTargetMethod("sendSMS");
        return jobDetail;
    }

    @Bean
    public CronTriggerFactoryBean jobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setCronExpression("0 10 1 * * ?");
        return trigger;
    }


    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        //延时启动，应用启动1秒后
        factoryBean.setStartupDelay(1);
        //注册触发器
        factoryBean.setTriggers(trigger);
        return factoryBean;
    }
}