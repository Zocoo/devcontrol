/*
 * IDeviceFirmwareVersionLogsService.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月17日 Created
 */
package com.zpd.service;

import com.zpd.pojo.DeviceFirmwareVersionLogs;

/**
 * TODO
 * 
 * @author Administrator @version v1.0.0
 * @date 2015年12月17日
 *
 */
public interface IDeviceFirmwareVersionLogsService
		extends IBaseService<DeviceFirmwareVersionLogs>
{
	/**
	 * 通过设备的ID和状态查询升级记录
	 * 
	 * @param deviceId
	 *            设备ID
	 * @param status
	 *            升级记录状态
	 * @return DeviceFirmwareVersionLogs
	 */
	DeviceFirmwareVersionLogs querydfvlbydid(Integer deviceId, Integer status);
}
