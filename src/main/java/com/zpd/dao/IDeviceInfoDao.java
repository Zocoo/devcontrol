/*
 * IDeviceInfoDao.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月22日 Created
 */
package com.zpd.dao;

import java.util.List;

import com.zpd.pojo.DeviceInfo;
import com.zpd.pojo.DeviceType;
import com.zpd.pojo.Vendor;

/**
 * 设备信息接口
 * 
 * @author Jacky @version v1.0.0
 * @date 2015年11月22日
 * 
 */
public interface IDeviceInfoDao extends IBaseDao<DeviceInfo>
{
	/**
	 * 批量更新设备状态
	 */
	void updateNetStat(String sn);

	/**
	 * 通过sn查询设备集合
	 * 
	 * @param sn
	 *            sn
	 * @return 设备集合
	 */
	List<DeviceInfo> queryDeviceInfoBySn(String sn);

	/**
	 * 通过sn查询设备信息
	 * 
	 * @param sn
	 *            设备sn
	 * @return DeviceInfo
	 */
	DeviceInfo getDeviceInfoBySn(String sn);

	/**
	 * 通过ID查询设备类型
	 * 
	 * @param id
	 *            设备ID
	 * @return DeviceType
	 */
	DeviceType getDeviceTypeById(String id);

	/**
	 * 通过名称查询设备类型
	 * 
	 * @param name
	 *            设备名称
	 * @return DeviceType
	 */
	DeviceType getDeviceTypeByName(String name, Integer cid, Integer vid);

	/**
	 * 插入DeviceType
	 * 
	 * @param dt
	 *            DeviceType对象
	 * @return 是否成功
	 */
	int insertDeviceType(DeviceType dt);

	/**
	 * 通过名称查询供应商
	 * 
	 * @param name
	 *            供应商ID
	 * @return Vendor
	 */
	Vendor getVendorByName(String name);

	/**
	 * 通过ESN查询设备信息
	 * 
	 * @param esn
	 *            设备esn
	 * @return 设备信息
	 */
	DeviceInfo getDeviceByEsn(String esn);
}
