/*
 * DeviceInfoDaoImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月22日 Created
 */
package com.zpd.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.zpd.dao.IDeviceInfoDao;
import com.zpd.pojo.DeviceInfo;
import com.zpd.pojo.DeviceType;
import com.zpd.pojo.Vendor;
import com.zpd.utils.LogFactory;
import com.zpd.utils.Paginate;

/**
 * 设备信息接口实现
 * 
 * @author Jacky @version v1.0.0
 * @date 2015年11月22日
 * 
 */
public class DeviceInfoDaoImpl implements IDeviceInfoDao
{
	private final static Logger logger = LogFactory
			.getLogger(ConnRecordDaoImpl.class);

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
	public int save(DeviceInfo t)
	{
		int result = -1;
		String sql = "INSERT INTO device_details (\n" + "	`order_id`,\n"
				+ "	`device_category_id`,\n" + "	`device_type_id`,\n"
				+ "	`unique_code`,\n" + "	`sno`,\n" + "	`esn`,\n"
				+ "	`mac`,\n" + "	`status`,\n" + "	`type`,\n"
				+ "	`open_at`,\n" + "	`close_at`,\n" + "	`main`,\n"
				+ "	`created_at`,\n" + "	`updated_at`,\n" + "	`enable`,\n"
				+ "	`scene_id`,\n" + "	`net_state`,\n" + "	`wlan_ip`,\n"
				+ "	`version`,\n" + "	`vendor`,\n" + "	`apk_version`,\n"
				+ "	`apk_server_version`,\n" + "	`monitor_sno`,\n"
				+ "	`monitor_status`,\n" + "	`device_type`,\n"
				+ "	`ssid`,\n" + "	`close`,\n" + "	`open`,\n" + "	`h_px`,\n"
				+ "	`w_px`,\n" + "	`screen_type`,\n" + "	`vendor_id`\n"
				+ ")\n" + "VALUES\n" + "	(\n"
				+ "	:orderId , :deviceCategoryId ,:deviceTypeId ,:uniqueCode ,:sno ,:esn ,:mac,"
				+ " :status , :type ,:openAt ,:closeAt ,"
				+ " :main , :createdAt ,:updatedAt ,:enable ,"
				+ " :sceneId , :netState ,:wlanIp ,:version ,"
				+ " :vendor , :apkVersion ,:apkServerVersion ,:monitorSno ,"
				+ " :monitorStatus , :deviceType ,:ssid ,:close ,"
				+ " :open , :hPx ,:wPx ,:screenType , :vendorId" + "\n" + "	)";
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
		int result = -1;
		String sql = "UPDATE device_details\n" + "SET `enable` = 0\n"
				+ "WHERE\n" + "	id = " + id;
		try
		{
			result = this.jdbcTemplate.getJdbcOperations().update(sql);
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int update(DeviceInfo t)
	{
		int result = -1;
		String sql = "update device_details set `vendor_id`=:vendorId, `ssid`=:ssid,`order_id`=:orderId,`device_category_id`=:deviceCategoryId,`device_type_id`=:deviceTypeId,\n"
				+ "`unique_code`=:uniqueCode,`sno`=:sno,`enable`=:enable,`esn`=:esn,`created_at`=:createdAt,`updated_at`=:updatedAt,\n"
				+ "`mac`=:mac,`status`=:status,`type`=:type,`open_at`=:openAt,`close_at`=:closeAt,`main`=:main,`scene_id`=:sceneId,\n"
				+ "`net_state`=:netState,`wlan_ip`=:wlanIp,`version`=:version WHERE `id`=:id";
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
	public DeviceInfo get(int id)
	{
		List<DeviceInfo> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	device_details\n"
				+ "WHERE\n" + "	ENABLE = 1\n" + "AND id = " + id;
		try
		{
			list = this.jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<DeviceInfo>(DeviceInfo.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

	@Override
	public long getCount()
	{
		long count = -1;
		String sql = "SELECT\n" + "	count(*)\n" + "FROM\n"
				+ "	device_details\n" + "WHERE\n" + "	ENABLE = 1";
		try
		{
			count = this.jdbcTemplate.getJdbcOperations().queryForObject(sql,
					Long.class);
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return count;
	}

	@Override
	public long getCount(int key, int value)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DeviceInfo> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceInfo> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceInfo> paginate(int key, int value, Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceInfo> queryDeviceInfoBySn(String sn)
	{
		List<DeviceInfo> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	device_details\n"
				+ "WHERE\n" + "	ENABLE = 1\n" + "AND sn in :sn";
		SqlParameterSource sps = new MapSqlParameterSource("sn", sn);
		try
		{
			list = this.jdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<DeviceInfo>(DeviceInfo.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public DeviceInfo getDeviceInfoBySn(String sn)
	{
		List<DeviceInfo> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	device_details\n"
				+ "WHERE\n" + "	ENABLE = 1\n" + "AND esn =:sn ";
		SqlParameterSource sps = new MapSqlParameterSource("sn", sn);
		try
		{
			list = this.jdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<DeviceInfo>(DeviceInfo.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

	@Override
	public DeviceType getDeviceTypeById(String id)
	{
		List<DeviceType> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	device_details\n"
				+ "WHERE\n" + "	ENABLE = 1\n" + "AND id =" + id;
		try
		{
			list = this.jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<DeviceType>(DeviceType.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

	@Override
	public DeviceType getDeviceTypeByName(String name, Integer cid, Integer vid)
	{
		List<DeviceType> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	device_types\n"
				+ "WHERE\n" + "	ENABLE = 1\n"
				+ "AND name =:name and device_category_id=" + cid
				+ "\n and vendor_id=" + vid;
		SqlParameterSource sps = new MapSqlParameterSource("name", name);
		try
		{
			list = this.jdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<DeviceType>(DeviceType.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

	@Override
	public int insertDeviceType(DeviceType t)
	{
		int result = -1;
		String sql = "INSERT INTO device_types (\n" + "	`device_category_id`,\n"
				+ "	`name`,\n" + "	`intro`,\n" + "	`created_at`,\n"
				+ "	`updated_at`,\n" + "	`enable`\n" + ")\n" + "VALUES\n"
				+ "	(\n"
				+ "	:deviceCategoryId ,:name ,:intro ,:createdAt ,:updatedAt ,:enable\n"
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
	public Vendor getVendorByName(String name)
	{
		List<Vendor> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	vendors\n" + "WHERE\n"
				+ "	ENABLE = 1\n" + "AND name =:name ";
		SqlParameterSource sps = new MapSqlParameterSource("name", name);
		try
		{
			list = this.jdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<Vendor>(Vendor.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

	@Override
	public DeviceInfo getDeviceByEsn(String esn)
	{
		List<DeviceInfo> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	device_details\n"
				+ "WHERE\n" + "	ENABLE = 1\n" + "AND esn =:esn ";
		SqlParameterSource sps = new MapSqlParameterSource("esn", esn);
		try
		{
			list = this.jdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<DeviceInfo>(DeviceInfo.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

	@Override
	public void updateNetStat(String sn)
	{
		String sql = "update device_details set net_state=2 where esn in " + sn;
		SqlParameterSource sps = new MapSqlParameterSource("esn", sn);
		try
		{
			this.jdbcTemplate.update(sql, sps);
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
	}

}
