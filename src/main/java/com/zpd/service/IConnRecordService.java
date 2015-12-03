/*
 * IConnService.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月20日 Created
 */
package com.zpd.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.zpd.pojo.ConnRecord;
import com.zpd.pojo.wrap.DayConns;

/**
 * 用户连接记录服务接口
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月20日
 * 
 */
public interface IConnRecordService extends IBaseService<ConnRecord> {

	/**
	 * 获取指定设备当前在线连接用户列表
	 * 
	 * @param sn
	 *            设备sn
	 * @return 用户连接集合
	 */
	List<ConnRecord> getConnUser(String sn);

	/**
	 * 保存用户连接记录
	 * 
	 * @param sn
	 *            设备序列号
	 * @param ja
	 *            用户连接json数组集合
	 * @return
	 */
	int save(String sn, JSONArray ja);

	/**
	 * 通过时间去查询当天的连接量
	 * 
	 * @param sn
	 *            设备sn数组
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return DayCouns集合
	 */
	List<DayConns> queryCouns(String[] sn, Integer start, Integer end);

}
