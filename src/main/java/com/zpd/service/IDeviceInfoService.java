/*
 * IDeviceInfoService.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月22日 Created
 */
package com.zpd.service;

import java.util.List;

import com.zpd.pojo.DeviceInfo;
import com.zpd.pojo.wrap.DeviceMsg;

/**
 * 设备信息服务接口
 * 
 * @author Jacky @version v1.0.0
 * @date 2015年11月22日
 * 
 */
public interface IDeviceInfoService extends IBaseService<DeviceInfo>
{
	/**
	 * 通过SN查询设备详细信息
	 * 
	 * @param sn
	 *            设备数组
	 * @return DeviceInfo集合
	 */
	List<DeviceInfo> getDeviceInfoBySn(String[] sn);

	/**
	 * 通过sn查询设备信息
	 * 
	 * @param sn
	 *            设备sn
	 * @return DeviceInfo
	 */
	DeviceInfo getDeviceInfoBySn(String sn);

	/**
	 * 通过迈普提交的数据更新设备信息
	 * 
	 * @param di
	 *            原来设备的信息
	 * @param dm
	 *            现在设备的信息
	 * @return 是否成功
	 */
	int updateFromMp(DeviceInfo di, DeviceMsg dm);

	/**
	 * 通过ESN查询设备信息
	 * 
	 * @param esn
	 *            设备esn
	 * @return 设备信息 
	 */
	DeviceInfo getDeviceByEsn(String esn);
}
