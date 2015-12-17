/*
 * DeviceFirmwareVersionLogsDaoImpl.java
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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.zpd.dao.IDeviceFirmwareVersionLogsDao;
import com.zpd.pojo.DeviceFirmwareVersionLogs;
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
public class DeviceFirmwareVersionLogsDaoImpl
		implements IDeviceFirmwareVersionLogsDao
{
	private final static Logger logger = LogFactory
			.getLogger(DeviceFirmwareVersionLogsDaoImpl.class);

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
	public int save(DeviceFirmwareVersionLogs t)
	{
		int result = -1;
		String sql = "INSERT INTO device_firmware_version_logs (\n"
				+ "	`created_at`,\n" + "	`updated_at`,\n" + "	`enable`,\n"
				+ "	`version`,\n" + "	`version_prev`,\n" + "	`status`,\n"
				+ "	`remark`,\n" + "	`url`,\n" + "	`device_id`\n" + ")\n"
				+ "VALUES\n" + "	(\n"
				+ "	:createdAt ,:updatedAt ,:enable ,:version ,:versionPrev ,:status, :remark, :url,:deviceId\n"
				+ "	)";
		SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
		KeyHolder key = new GeneratedKeyHolder();
		try
		{
			result = this.jdbcTemplate.update(sql, sps, key);
			t.setId(key.getKey().intValue());
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DeviceFirmwareVersionLogs t)
	{
		int result = -1;
		String sql = "update device_firmware_version_logs set `created_at`=:createdAt,`updated_at`=:updatedAt,`enable`=:enable,`version`=:version,\n"
				+ "`version_prev`=:versionPrev,`status`=:status,`remark`=:remark,`url`=:url,`device_id`=:deviceId WHERE `id`=:id";
		SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
		try
		{
			result = this.jdbcTemplate.update(sql, sps);
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public DeviceFirmwareVersionLogs get(int id)
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
	public List<DeviceFirmwareVersionLogs> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceFirmwareVersionLogs> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceFirmwareVersionLogs> paginate(int key, int value,
			Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceFirmwareVersionLogs querydfvlbydid(Integer deviceId,
			Integer status)
	{
		List<DeviceFirmwareVersionLogs> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n"
				+ "	device_firmware_version_logs\n" + "WHERE\n"
				+ "	ENABLE = 1\n" + "AND device_id = " + deviceId
				+ "\n and status =" + status;
		try
		{
			list = this.jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<DeviceFirmwareVersionLogs>(
							DeviceFirmwareVersionLogs.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

}
