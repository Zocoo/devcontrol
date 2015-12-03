/*
 * ConnRecordDayCountDaoImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月24日 Created
 */
package com.zpd.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.zpd.dao.IConnRecordDayCountDao;
import com.zpd.pojo.ConnRecordDayCount;
import com.zpd.pojo.wrap.DayConns;
import com.zpd.utils.LogFactory;
import com.zpd.utils.Paginate;

/**
 * 用户连接记录按天统计接口实现
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月24日
 * 
 */
public class ConnRecordDayCountDaoImpl implements IConnRecordDayCountDao
{
	private final static Logger			logger	= LogFactory
														.getLogger(ConnRecordDaoImpl.class);
	private NamedParameterJdbcTemplate	jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.dao.IBaseDao#save(java.lang.Object)
	 */
	@Override
	public int save(ConnRecordDayCount t)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.dao.IBaseDao#delete(int)
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
	 * @see com.zpd.dao.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(ConnRecordDayCount t)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.dao.IBaseDao#get(int)
	 */
	@Override
	public ConnRecordDayCount get(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.dao.IBaseDao#getCount()
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
	 * @see com.zpd.dao.IBaseDao#getCount(int, int)
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
	 * @see com.zpd.dao.IBaseDao#initTop(int)
	 */
	@Override
	public List<ConnRecordDayCount> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.dao.IBaseDao#paginate(com.zpd.utils.Paginate)
	 */
	@Override
	public List<ConnRecordDayCount> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.dao.IBaseDao#paginate(int, int, com.zpd.utils.Paginate)
	 */
	@Override
	public List<ConnRecordDayCount> paginate(int key, int value, Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int batchInsertOrUpdateConnRecordDayCount(
			List<ConnRecordDayCount> list)
	{
		int result = -1;
		String sql = "INSERT INTO conn_records_day_count (\n" + "	sno,\n"
				+ "	mac,\n" + "	date,\n" + "	num,\n" + "	created_at,\n"
				+ "	updated_at,\n" + "	`enable`\n" + ")\n" + "VALUES\n"
				+ "	(\n" + "		:sno,\n" + "		:mac,\n" + "		:date,\n"
				+ "		:num,\n" + "		:createdAt,\n" + "		:updatedAt,\n"
				+ "		:enable\n" + "	) ON DUPLICATE KEY UPDATE num = num + 1,\n"
				+ "	updated_at =\n" + "VALUES\n" + "	(updated_at)";
		BeanPropertySqlParameterSource[] batchArgs = new BeanPropertySqlParameterSource[list
				.size()];
		for (int i = 0; i < list.size(); i++)
		{
			batchArgs[i] = new BeanPropertySqlParameterSource(list.get(i));
		}
		try
		{
			int[] results = this.jdbcTemplate.batchUpdate(sql, batchArgs);
			result = results.length;
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<DayConns> countConnRecordDayConns(String[] snArr, int start,
			int end)
	{
		List<DayConns> list = null;
		String sql = "SELECT\n" + "	date,\n" + "	sum(num) AS conns\n"
				+ "FROM\n" + "	conn_records_day_count\n" + "WHERE\n"
				+ "	sno IN (:snArr)\n" + "AND date BETWEEN " + start + "\n"
				+ "AND " + end + "\n" + "AND `enable` = 1\n" + "GROUP BY\n"
				+ "	date";
		SqlParameterSource sps = new MapSqlParameterSource("snArr",
				Arrays.asList(snArr));
		try
		{
			list = this.jdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<DayConns>(DayConns.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}

}
