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
import org.quartz.JobExecutionException;

import com.zpd.dao.IDeviceInfoDao;
import com.zpd.pojo.DeviceStatus;

import redis.clients.jedis.Jedis;

public class MyJob
{
	private static IDeviceInfoDao	deviceInfoDao;
	private static Integer			dtime	= 0;
	private static Jedis			jedis	= null;

	static
	{
		jedis = RedisClient.getJedis();
	}

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

	public void startWork() throws JobExecutionException
	{
		int unixTime = Time.toUnixTime(Time.now());
		System.out.println("3----------------------->" + unixTime);
		Set<String> keys = jedis.keys("heartbeat*");
		Iterator<String> it = keys.iterator();
		String sn = "('heartbeat'";
		while (it.hasNext())
		{
			String key = it.next();
			DeviceStatus ds = new DeviceStatus();
			ds = RedisClient.get(key, DeviceStatus.class);
			// if (ds.isOnline())
			// {
			boolean c = false;
			if (ds != null)
			{
				if (ds.isOnline() || unixTime - dtime > 3000)
				{
					c = true;
					dtime = unixTime;
				}
				if (ds != null && c)
					if (ds.getPingtime() != null)
						if (unixTime - ds.getPingtime() > 240)
						{
							ds.setClientcount(0);
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
			// }
			System.out.println("1----------------------->" + key);
			System.out.println("2----------------------->" + ds.getPingtime()
					+ "===" + ds.isOnline() + "==="
					+ (unixTime - ds.getPingtime()));
		}
		sn = sn + ")";
		if (!sn.equals("('heartbeat')"))
		{
			deviceInfoDao.updateNetStat(sn);
		}
	}

	public static void main(String[] args)
	{
		int unixTime = Time.toUnixTime(Time.now());
		Set<String> keys = jedis.keys("h*");
		int i = 0;
		Iterator<String> it = keys.iterator();
		while (it.hasNext())
		{
			i++;
			String key = it.next();
			// RedisClient.del(key);
			DeviceStatus ds = new DeviceStatus();
			ds = RedisClient.get(key, DeviceStatus.class);
			if (unixTime - ds.getPingtime() > 360)
			{
				System.out.println(i + "----------------------->" + ds.getEsn()
						+ "====" + ds.getPingtime() + "===" + ds.isOnline()
						+ "===" + (unixTime - ds.getPingtime()));
			}
		}
	}
}
