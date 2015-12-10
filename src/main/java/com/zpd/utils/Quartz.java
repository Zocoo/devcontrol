/*
 * Quartz.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月8日 Created
 */
package com.zpd.utils;

public class Quartz
{

	public void go() throws Exception
	{
		// // 首先，必需要取得一个Scheduler的引用
		// SchedulerFactory sf = new StdSchedulerFactory();
		// Scheduler sched = sf.getScheduler();
		// // jobs可以在scheduled的sched.start()方法前被调用
		//
		// JobDetail job = newJob(MyJob.class).withIdentity("job1", "group1")
		// .build();
		//
		// // CronTrigger trigger = newTrigger().withIdentity("trigger1",
		// "group1")
		// // .withSchedule(cronSchedule("0 0/3 * * * ?")).build();
		// CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
		// .withSchedule(cronSchedule("0/10 * * * * ?")).build();
		// Date ft = sched.scheduleJob(job, trigger);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss
		// SSS");
		// System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft)
		// + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());
		// sched.start();
		// int unixTime = Time.toUnixTime(Time.now());
		// if (unixTime - code > 160)
		// {
		// code = unixTime;
		// } else
		// {
		// sched.shutdown(true);
		// }
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
