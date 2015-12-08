/*
 * InitialContextConfig.java
 * Copyright(C) 2013-2015 东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年5月25日 Created
 */
package com.zpd.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.zpd.dao.IDeviceInfoDao;
import com.zpd.utils.MyJob;
import com.zpd.utils.Quartz;

/**
 * 初始化上下文配置
 * 
 * @author Jacky @version v1.0
 * @date 2015年9月11日
 * 
 */
public class InitialConfigListener implements ServletContextAware
{
	private  IDeviceInfoDao deviceInfoDao;
	
	public void setDeviceInfoDao(IDeviceInfoDao deviceInfoDao)
	{
		this.deviceInfoDao = deviceInfoDao;
	}

	@Override
	public void setServletContext(ServletContext servletContext)
	{
		try
		{
			MyJob.setDeviceInfoDao(deviceInfoDao);
			Quartz.chackOnline();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
