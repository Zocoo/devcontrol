/*
 * IDeviceCategoriesDao.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月17日 Created
 */
package com.zpd.dao;

import com.zpd.pojo.DeviceCategories;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月17日
 *
 */
public interface IDeviceCategoriesDao extends IBaseDao<DeviceCategories>
{
	/**
	 * 通过名称查询DeviceCategories
	 * 
	 * @param name
	 *            名称
	 * @return DeviceCategories
	 */
	DeviceCategories queryDeviceC(String name);
}
