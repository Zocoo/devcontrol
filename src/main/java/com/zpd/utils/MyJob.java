/*
 * MyJob.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月8日 Created
 */
package com.zpd.utils;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zpd.dao.IDeviceInfoDao;
import com.zpd.pojo.DeviceStatus;

import redis.clients.jedis.Jedis;

public class MyJob implements Job
{
	private static IDeviceInfoDao deviceInfoDao;

	public static void setDeviceInfoDao(IDeviceInfoDao deviceInfoDao)
	{
		MyJob.deviceInfoDao = deviceInfoDao;
	}
	// private static SqlMapClient sqlmap;
	//
	// static
	// {
	// SqlMapUtil smu = new SqlMapUtil();
	// sqlmap = smu.getInstance();
	// }

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException
	{
		int unixTime = Time.toUnixTime(Time.now());
		Jedis jedis = RedisClient.getJedis();
		Set<String> keys = jedis.keys("heartbeat*");
		Iterator<String> it = keys.iterator();
		String sn = "('heartbeat'";
		while (it.hasNext())
		{
			String key = it.next();
			DeviceStatus ds = new DeviceStatus();
			ds = RedisClient.get(key, DeviceStatus.class);
			if (ds.isOnline())
			{
				if (ds.getPingtime() != null)
					if (unixTime - ds.getPingtime() > 120)
					{
						ds.setOnline(false);
						RedisClient.del(key);
						RedisClient.set(key, ds);
						if (ds != null)
						{
							if (!StringUtils.isEmpty(ds.getEsn()))
							{
								sn = sn + ",'" + ds.getEsn() + "'";
								// try
								// {
								// .update("updateDevice", sn);
								// } catch (SQLException e)
								// {
								// // TODO Auto-generated catch block
								// e.printStackTrace();
								// }
							}
						}
					}
			}
			System.out.println("1----------------------->" + key);
			System.out.println("2----------------------->" + ds.getPingtime());
			System.out.println("3----------------------->" + unixTime);
		}
		sn = sn + ")";
		deviceInfoDao.updateNetStat(sn);
	}
}
