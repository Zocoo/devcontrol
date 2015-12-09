/*
 * Quartz.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月8日 Created
 */
package com.zpd.utils;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * TODO
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月8日
 *
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class Quartz
{
	public void go() throws Exception
	{
		// 首先，必需要取得一个Scheduler的引用
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		// jobs可以在scheduled的sched.start()方法前被调用

		JobDetail job = newJob(MyJob.class).withIdentity("job1", "group1")
				.build();
		CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(cronSchedule("0 0/2 * * * ?")).build();
		// CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
		// .withSchedule(cronSchedule("0/10 * * * * ?")).build();
		Date ft = sched.scheduleJob(job, trigger);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft)
				+ "，并且以如下重复规则重复执行: " + trigger.getCronExpression());
		sched.start();
	}

	// public static void main(String[] args) throws Exception
	// {
	// Quartz test = new Quartz();
	// test.go();
	// }

	public static void chackOnline() throws Exception
	{
		Quartz test = new Quartz();
		test.go();
	}

}
