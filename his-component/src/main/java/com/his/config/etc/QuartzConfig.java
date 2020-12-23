package com.his.config.etc;

import com.his.MyJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/**
 * Description: Quartz配置类
 * Date: 20-12-22
 *
 * @author yh
 */
//@Configuration
public class QuartzConfig {


    /*@Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        // 关联需要执行的Job类
          factory.setJobClass(MyJob.class);
        return factory;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(jobDetailFactoryBean().getObject());
        factory.setCronExpression("10/5 * * * * ?");
        return factory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 关联trigger
         factory.setTriggers(cronTriggerFactoryBean().getObject());
        return factory;
    }*/
}