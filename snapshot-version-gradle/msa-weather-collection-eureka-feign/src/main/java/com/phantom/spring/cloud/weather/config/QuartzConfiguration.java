package com.phantom.spring.cloud.weather.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.phantom.spring.cloud.weather.job.WeatherDataSyncJob;

/**
 * Quartz Configuration.
 * @author Administrator
 *
 */
//调度工作完成需要配置任务给触发器，通过调度器集中执行
@Configuration
public class QuartzConfiguration {
	
	private static final int UPDATE_TIME = 1800;//更新频率为半小时

	/**
	 * Job(任务) Configuration.
	 * JobDetail是Quartz Client在Job被添加到Scheduler被创建的，本质就是Job
	 */
	@Bean
	public JobDetail weatherDataSyncJobDetail() {
		/**
		 * JobBuilder:用来创建JobDetail
		 * withIdentity(String name):为Job设置一个标识
		 * storeDurably():使Job不依赖于Trigger在Scheduler当中独立存在
		 * build():创建JobDetail实例
		 */
		return JobBuilder.newJob(WeatherDataSyncJob.class)
			   .withIdentity("weatherDataSyncJobDetail").storeDurably().build();
	}
	
	/**
	 * Trigger(触发器) Configuration.
	 * Trigger决定以怎样的形式触发Job. SimpleTrigger指定以一定的时间间隔执行任务.
	 */
	@Bean
	public Trigger weatherDataSyncJobTrigger() {
		/**
		 * SchedulerBuidler(调度器) Configuration
		 * SimpleSchedulerBuilder:用来创建SchedulerBuidler
		 * withIntervalInSeconds(int intervalInSecond):用来指定一定的时间间隔重复执行任务，单位是秒
		 * epeatForever():表示执行次数不限
		 */
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
		                                     .withIntervalInSeconds(UPDATE_TIME).repeatForever();
		
		/**
		 * TriggerBuidler用来创建Trigger
		 * forJob():为Trigger(触发器)指定相应的Job(作业)
		 * withIdentity(String name):为Trigger指定一个标识
		 * withScheduler(schedBuilder):为Trigger(触发器)指定Scheduler(调度器)
		 * build():创建Trigger实例
		 */
		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
			   .withIdentity("weatherDataSyncJobTrigger").withSchedule(schedBuilder).build();
	}
}
