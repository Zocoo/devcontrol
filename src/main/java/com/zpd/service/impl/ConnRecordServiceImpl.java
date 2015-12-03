/*
 * ConnRecordServiceImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月22日 Created
 */
package com.zpd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpd.dao.IConnRecordDao;
import com.zpd.dao.IConnRecordDayCountDao;
import com.zpd.pojo.ConnRecord;
import com.zpd.pojo.ConnRecordDayCount;
import com.zpd.pojo.wrap.DayConns;
import com.zpd.service.IConnRecordService;
import com.zpd.utils.Paginate;
import com.zpd.utils.Time;

/**
 * 用户连接记录服务接口实现
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月22日
 * 
 */
public class ConnRecordServiceImpl implements IConnRecordService
{
	private IConnRecordDao			connRecordDao;
	private IConnRecordDayCountDao	connRecordDayCountDao;

	public void setConnRecordDao(IConnRecordDao connRecordDao)
	{
		this.connRecordDao = connRecordDao;
	}

	public void setConnRecordDayCountDao(
			IConnRecordDayCountDao connRecordDayCountDao)
	{
		this.connRecordDayCountDao = connRecordDayCountDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#save(java.lang.Object)
	 */
	@Override
	public int save(ConnRecord t)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#delete(int)
	 */
	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#update(java.lang.Object)
	 */
	@Override
	public int update(ConnRecord t)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#get(int)
	 */
	@Override
	public ConnRecord get(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#getCount()
	 */
	@Override
	public long getCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#getCount(int, int)
	 */
	@Override
	public long getCount(int key, int value)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#initTop(int)
	 */
	@Override
	public List<ConnRecord> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#paginate(com.zpd.utils.Paginate)
	 */
	@Override
	public List<ConnRecord> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#paginate(int, int,
	 * com.zpd.utils.Paginate)
	 */
	@Override
	public List<ConnRecord> paginate(int key, int value, Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConnRecord> getConnUser(String sn)
	{
		return this.connRecordDao.getConnUser(sn);
	}

	@Override
	public int save(String sn, JSONArray ja)
	{
		// 当前设备保持连接用户
		List<ConnRecord> keepConnRecord = null;
		// 当前设备新连接用户
		List<ConnRecord> newConnRecord = null;
		// 当前设备连接记录按天统计
		List<ConnRecordDayCount> newConnRecordDayCount = null;
		// 当前设备下线用户
		List<ConnRecord> offlineConnRecord = null;
		// 当前设备上次连接用户
		List<ConnRecord> lastConnRecord = this.connRecordDao.getConnUser(sn);
		Map<String, ConnRecord> lastConnRecordMap = new HashMap<String, ConnRecord>(
				lastConnRecord.size());
		// 转map
		for (ConnRecord cr : lastConnRecord)
		{
			lastConnRecordMap.put(cr.getMac(), cr);
		}
		Integer currentTime = Time.toUnixTime(Time.now());
		Iterator<Object> iterator = ja.iterator();
		while (iterator.hasNext())
		{
			Object obj = iterator.next();
			if (obj instanceof JSONObject)
			{
				JSONObject jo = (JSONObject) obj;
				String mac = jo.getString("mac");
				if (!StringUtils.isEmpty(mac))
				{
					// 保持连接用户,修改流量增量
					if (lastConnRecordMap.containsKey(mac))
					{
						if (keepConnRecord == null)
						{
							keepConnRecord = new ArrayList<ConnRecord>(0);
						}
						ConnRecord lastCr = lastConnRecordMap.get(mac);
						lastCr.setTxBytes(lastCr.getTxBytes().add(
								jo.getBigDecimal("tx_bytes")));
						lastCr.setRxBytes(lastCr.getRxBytes().add(
								jo.getBigDecimal("rx_bytes")));
						lastCr.setUpdatedAt(currentTime);
						keepConnRecord.add(lastCr);
						// 移除保持，剩下新连接用户
						iterator.remove();
						// 移除保持，剩下下线用户
						lastConnRecordMap.remove(mac);
					}
				} else
				{
					// 移除错误数据
					iterator.remove();
				}
			} else
			{
				// 移除错误数据
				iterator.remove();
			}
		}
		// 封装新连接用户
		if (!ja.isEmpty())
		{
			newConnRecord = new ArrayList<ConnRecord>(ja.size());
			newConnRecordDayCount = new ArrayList<ConnRecordDayCount>(ja.size());
			buildNewConnRecord(sn, ja, newConnRecord, newConnRecordDayCount,
					currentTime);
		}
		// 封装下线用户
		if (!lastConnRecord.isEmpty())
		{
			offlineConnRecord = new ArrayList<ConnRecord>(lastConnRecord.size());
			buildOfflineConnRecord(lastConnRecord, offlineConnRecord,
					currentTime);
		}
		int update = -1;
		int move = -1;
		int insert = -1;
		// 批量更新临时表保持用户
		if (keepConnRecord != null && keepConnRecord.size() > 0)
		{
			update = this.connRecordDao.batchUpdateKeep(keepConnRecord);
		}
		// 迁移离线用户

		if (offlineConnRecord != null && offlineConnRecord.size() > 0)
		{
			// 批量插入离线用户到持久表
			move = this.connRecordDao.batchInsertOff(offlineConnRecord);
			if (move > 0)
			{
				// 批量删除临时表中的离线用户
				move = this.connRecordDao.batchDeleteOff(offlineConnRecord);
			}
		}

		if (newConnRecord != null && newConnRecord.size() > 0)
		{
			// 批量插入新用户到临时表
			insert = this.connRecordDao.batchInsertNew(newConnRecord);
			if (insert > 0)
			{
				insert = this.connRecordDayCountDao
						.batchInsertOrUpdateConnRecordDayCount(newConnRecordDayCount);
			}
		}
		return (update >= 0 && move >= 0 && insert >= 0) ? 0 : -1;
	}

	/**
	 * 封装离线用户
	 * 
	 * @param lastConnRecord
	 * @param offlineConnRecord
	 * @param currentTime
	 */
	private void buildOfflineConnRecord(List<ConnRecord> lastConnRecord,
			List<ConnRecord> offlineConnRecord, Integer currentTime)
	{
		// 设当前时间为下线时间
		for (ConnRecord connRecord : lastConnRecord)
		{
			connRecord.setOffline(currentTime);
			connRecord.setUpdatedAt(currentTime);
			offlineConnRecord.add(connRecord);
		}
	}

	/**
	 * 封装新用户
	 * 
	 * @param sn
	 * @param ja
	 * @param newConnRecord
	 * @param newConnRecordDayCount
	 * @param currentTime
	 */
	private void buildNewConnRecord(String sn, JSONArray ja,
			List<ConnRecord> newConnRecord,
			List<ConnRecordDayCount> newConnRecordDayCount, int currentTime)
	{
		ConnRecord cr = null;
		ConnRecordDayCount crdc = null;
		Iterator<Object> iterator = ja.iterator();
		while (iterator.hasNext())
		{
			JSONObject jo = (JSONObject) iterator.next();
			cr = new ConnRecord();
			cr.setSno(sn);
			String mac = jo.getString("mac");
			cr.setMac(mac);
			cr.setOnline(currentTime);
			cr.setTxBytes(jo.getBigDecimal("tx_bytes"));
			cr.setRxBytes(jo.getBigDecimal("rx_bytes"));
			cr.setCreatedAt(currentTime);
			cr.setUpdatedAt(currentTime);
			cr.setEnable(true);
			newConnRecord.add(cr);

			crdc = new ConnRecordDayCount();
			crdc.setSno(sn);
			crdc.setMac(mac);
			crdc.setDate(Time.day());
			crdc.setNum(1);
			crdc.setCreatedAt(currentTime);
			crdc.setUpdatedAt(currentTime);
			crdc.setEnable(true);
			newConnRecordDayCount.add(crdc);
		}
	}

	@Override
	public List<DayConns> queryCouns(String[] snArr, Integer start, Integer end)
	{
		return this.connRecordDayCountDao.countConnRecordDayConns(snArr, start,
				end);
	}

}
