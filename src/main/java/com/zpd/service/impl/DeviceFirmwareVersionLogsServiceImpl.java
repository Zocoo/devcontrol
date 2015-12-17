/*
 * DeviceFirmwareVersionLogsServiceImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月17日 Created
 */
package com.zpd.service.impl;

import java.util.List;

import com.zpd.dao.IDeviceFirmwareVersionLogsDao;
import com.zpd.pojo.DeviceFirmwareVersionLogs;
import com.zpd.service.IDeviceFirmwareVersionLogsService;
import com.zpd.utils.Paginate;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月17日
 *
 */
public class DeviceFirmwareVersionLogsServiceImpl
		implements IDeviceFirmwareVersionLogsService
{
	private IDeviceFirmwareVersionLogsDao deviceFirmwareVersionLogsDao;

	public void setDeviceFirmwareVersionLogsDao(
			IDeviceFirmwareVersionLogsDao deviceFirmwareVersionLogsDao)
	{
		this.deviceFirmwareVersionLogsDao = deviceFirmwareVersionLogsDao;
	}

	@Override
	public int save(DeviceFirmwareVersionLogs t)
	{
		return this.deviceFirmwareVersionLogsDao.save(t);
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DeviceFirmwareVersionLogs t)
	{
		return this.deviceFirmwareVersionLogsDao.update(t);
	}

	@Override
	public DeviceFirmwareVersionLogs get(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCount(int key, int value)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DeviceFirmwareVersionLogs> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceFirmwareVersionLogs> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceFirmwareVersionLogs> paginate(int key, int value,
			Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceFirmwareVersionLogs querydfvlbydid(Integer deviceId,
			Integer status)
	{
		return this.deviceFirmwareVersionLogsDao.querydfvlbydid(deviceId,
				status);
	}

}
