/*
 * IConnRecordDayCountDao.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月24日 Created
 */
package com.zpd.dao;

import java.util.List;

import com.zpd.pojo.ConnRecordDayCount;
import com.zpd.pojo.wrap.DayConns;

/**
 * 用户连接记录按天统计接口
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月24日
 * 
 */
public interface IConnRecordDayCountDao extends IBaseDao<ConnRecordDayCount>
{
	/**
	 * 批量插入或修改用户连接记录表
	 * 
	 * @param list
	 * @return
	 */
	int batchInsertOrUpdateConnRecordDayCount(List<ConnRecordDayCount> list);

	/**
	 * 按天统计所有设备的连接数
	 * 
	 * @param snArr
	 *            设备序列号数组集
	 * @param start
	 *            起始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	List<DayConns> countConnRecordDayConns(String[] snArr, int start, int end);
}
