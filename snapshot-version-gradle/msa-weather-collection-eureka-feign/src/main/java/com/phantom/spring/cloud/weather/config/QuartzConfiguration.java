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
//���ȹ��������Ҫ�����������������ͨ������������ִ��
@Configuration
public class QuartzConfiguration {
	
	private static final int UPDATE_TIME = 1800;//����Ƶ��Ϊ��Сʱ

	/**
	 * Job(����) Configuration.
	 * JobDetail��Quartz Client��Job����ӵ�Scheduler�������ģ����ʾ���Job
	 */
	@Bean
	public JobDetail weatherDataSyncJobDetail() {
		/**
		 * JobBuilder:��������JobDetail
		 * withIdentity(String name):ΪJob����һ����ʶ
		 * storeDurably():ʹJob��������Trigger��Scheduler���ж�������
		 * build():����JobDetailʵ��
		 */
		return JobBuilder.newJob(WeatherDataSyncJob.class)
			   .withIdentity("weatherDataSyncJobDetail").storeDurably().build();
	}
	
	/**
	 * Trigger(������) Configuration.
	 * Trigger��������������ʽ����Job. SimpleTriggerָ����һ����ʱ����ִ������.
	 */
	@Bean
	public Trigger weatherDataSyncJobTrigger() {
		/**
		 * SchedulerBuidler(������) Configuration
		 * SimpleSchedulerBuilder:��������SchedulerBuidler
		 * withIntervalInSeconds(int intervalInSecond):����ָ��һ����ʱ�����ظ�ִ�����񣬵�λ����
		 * epeatForever():��ʾִ�д�������
		 */
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
		                                     .withIntervalInSeconds(UPDATE_TIME).repeatForever();
		
		/**
		 * TriggerBuidler��������Trigger
		 * forJob():ΪTrigger(������)ָ����Ӧ��Job(��ҵ)
		 * withIdentity(String name):ΪTriggerָ��һ����ʶ
		 * withScheduler(schedBuilder):ΪTrigger(������)ָ��Scheduler(������)
		 * build():����Triggerʵ��
		 */
		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
			   .withIdentity("weatherDataSyncJobTrigger").withSchedule(schedBuilder).build();
	}
}
