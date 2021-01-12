package com.his.service;

import com.his.mapper.ReturnVehicleSmsMapper;
import com.his.pojo.ReturnVehicleSms;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * Description: TO DO
 * Date: 21-1-4
 *
 * @author yh
 */
//@Component
public class ReturnVehicleSmsScheduleRefreshService {

    private final Logger log = LoggerFactory.getLogger(ReturnVehicleSmsScheduleRefreshService.class);

    @Autowired
    private ReturnVehicleSmsMapper returnVehicleSmsMapper;

    @Autowired
    private CronTrigger cronTrigger;

    @Autowired
    private Scheduler scheduler;

    /**
     * 功能：每隔10s查库，并根据查询结果决定是否重新设置定时任务
     */
    @Scheduled(fixedRate = 5000)
    public void scheduleUpdateCronTrigger() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        //当前Trigger使用的
        String currentCron = trigger.getCronExpression();
        //从数据库查询出来的
        ReturnVehicleSms returnVehicleSms = returnVehicleSmsMapper.selectRecent();
        System.out.println(returnVehicleSms);
        // 获取需要发送的时间
        LocalDateTime nowTime = LocalDateTime.now().plusHours(1);
        if (returnVehicleSms == null
                || StringUtils.isEmpty(returnVehicleSms.getCron())
                || currentCron.equals(returnVehicleSms.getCron())) {
            // 如果当前使用的cron表达式和从数据库中查询出来的cron表达式一致，则不刷新任务
        } else {
            System.out.println(123);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(returnVehicleSms.getCron());
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey()).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
        }





        
    }
}