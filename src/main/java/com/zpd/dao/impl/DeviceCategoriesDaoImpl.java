/*
 * DeviceCategoriesDaoImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月17日 Created
 */
package com.zpd.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.zpd.dao.IDeviceCategoriesDao;
import com.zpd.pojo.DeviceCategories;
import com.zpd.utils.LogFactory;
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
public class DeviceCategoriesDaoImpl implements IDeviceCategoriesDao
{
	private final static Logger logger = LogFactory
			.getLogger(DeviceCategoriesDaoImpl.class);

	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * set jdbc Template
	 * 
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(DeviceCategories t)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DeviceCategories t)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeviceCategories get(int id)
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
	public List<DeviceCategories> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceCategories> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceCategories> paginate(int key, int value, Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceCategories queryDeviceC(String name)
	{
		List<DeviceCategories> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	device_categories\n"
				+ "WHERE\n" + "	ENABLE = 1\n" + "AND name = :name";
		SqlParameterSource sps = new MapSqlParameterSource("name", name);
		try
		{
			list = this.jdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<DeviceCategories>(
							DeviceCategories.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

}
