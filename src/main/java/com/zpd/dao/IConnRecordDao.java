/*
 * IConnDao.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月20日 Created
 */
package com.zpd.dao;

import java.util.List;

import com.zpd.pojo.ConnRecord;

/**
 * 用户连接记录接口
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月20日
 * 
 */
public interface IConnRecordDao extends IBaseDao<ConnRecord>
{
	/**
	 * 获取指定设备当前在线连接用户列表
	 * 
	 * @param sn
	 *            设备sn
	 * @return 用户连接集合
	 */
	List<ConnRecord> getConnUser(String sn);

	/**
	 * 批量更新临时表保持用户
	 * 
	 * @param keepConnRecord
	 * @return
	 */
	int batchUpdateKeep(List<ConnRecord> keepConnRecord);

	/**
	 * 批量插入离线用户到持久表
	 * 
	 * @param offlineConnRecord
	 * @return
	 */
	int batchInsertOff(List<ConnRecord> offlineConnRecord);

	/**
	 * 批量删除临时表中的离线用户
	 * 
	 * @param offlineConnRecord
	 * @return
	 */
	int batchDeleteOff(List<ConnRecord> offlineConnRecord);

	/**
	 * 批量插入新用户到临时表
	 * 
	 * @param newConnRecord
	 * @return
	 */
	int batchInsertNew(List<ConnRecord> newConnRecord);

}
