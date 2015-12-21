/*
 * InstructionDaoImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
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

import com.zpd.dao.IInstructionDao;
import com.zpd.pojo.Instruction;
import com.zpd.pojo.Version;
import com.zpd.utils.LogFactory;
import com.zpd.utils.Paginate;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月3日
 *
 */
public class InstructionDaoImpl implements IInstructionDao
{
	private final static Logger logger = LogFactory
			.getLogger(InstructionDaoImpl.class);

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
	public int save(Instruction t)
	{
		int result = -1;
		String sql = "INSERT INTO instruction (\n" + "	`md5`,\n"
				+ "	`remark`,\n" + "	`ssid`,\n" + "	`ver`,\n"
				+ "	`esn`,\n" + "	`updatedat`,\n" + "	`type`,\n"
				+ "	`url`,\n" + "	`createdat`,\n" + "	`num`,\n"
				+ "	`enable`\n" + ")\n" + "VALUES\n" + "	(\n"
				+ ":md5 , :remark ,:ssid ,:ver , :esn ,:updatedat ,:type ,:url ,:createdat ,:num ,:enable\n"
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
	public int update(Instruction t)
	{
		int result = -1;
		String sql = "update instruction set `md5`=:md5,`remark`=:remark,`ssid`=:ssid,`result`=:result,`ver`=:ver,`esn`=:esn,`type`=:type,\n"
				+ "`url`=:url,`num`=:num,`enable`=:enable,`createdat`=:createdat,`updatedat`=:updatedat WHERE `id`=:id";
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
	public Instruction get(int id)
	{
		List<Instruction> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	instruction\n"
				+ "WHERE\n" + "id = " + id;
		try
		{
			list = this.jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<Instruction>(Instruction.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
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
	public List<Instruction> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instruction> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instruction> paginate(int key, int value, Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instruction queryInsByEsn(String esn)
	{
		List<Instruction> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	instruction\n"
				+ "WHERE\n" + "	`ENABLE` = 0\n"
				+ "AND esn =:esn and num<500 order by createdat asc limit 1";
		SqlParameterSource sps = new MapSqlParameterSource("esn", esn);
		try
		{
			list = this.jdbcTemplate.query(sql, sps,
					new BeanPropertyRowMapper<Instruction>(Instruction.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

	@Override
	public Version queryVersion(Integer id)
	{
		List<Version> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	firmware_versions\n"
				+ "WHERE\n" + "id = " + id;
		try
		{
			list = this.jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<Version>(Version.class));
		} catch (DataAccessException e)
		{
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

}
