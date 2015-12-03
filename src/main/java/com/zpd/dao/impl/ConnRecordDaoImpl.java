/*
 * IConnRecordDaoImpl.java
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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.zpd.dao.IConnRecordDao;
import com.zpd.pojo.ConnRecord;
import com.zpd.utils.LogFactory;
import com.zpd.utils.Paginate;

/**
 * 设备信息接口实现
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月22日
 * 
 */
public class ConnRecordDaoImpl implements IConnRecordDao {
	private final static Logger logger = LogFactory
			.getLogger(ConnRecordDaoImpl.class);

	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * set jdbcTemplate
	 * 
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(ConnRecord t) {
		int result = -1;
		String sql = "INSERT INTO conn_records (\n" + "	sno,\n" + "	mac,\n"
				+ "	`online`,\n" + "	tx_bytes,\n" + "	rx_bytes,\n"
				+ "	created_at,\n" + "	updated_at,\n" + "	`enable`\n" + ")\n"
				+ "VALUES\n" + "	(\n" + "		:sno,\n" + "		:mac,\n"
				+ "		:online,\n" + "		:txBytes,\n" + "		:rxBytes,\n"
				+ "		:createdAt,\n" + "		:updatedAt,\n" + "		:enable\n" + "	)";
		SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
		try {
			result = this.jdbcTemplate.update(sql, sps);
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	@Deprecated
	public int delete(int id) {
		return 0;
	}

	@Override
	@Deprecated
	public int update(ConnRecord t) {
		return 0;
	}

	@Override
	@Deprecated
	public ConnRecord get(int id) {
		return null;
	}

	@Override
	public long getCount() {
		long count = -1;
		String sql = "SELECT\n" + "	count(*)\n" + "FROM\n" + "	conn_records\n"
				+ "WHERE\n" + "	ENABLE = 1";
		try {
			count = this.jdbcTemplate.getJdbcOperations().queryForObject(sql,
					Long.class);
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return count;
	}

	@Override
	public long getCount(int key, int value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ConnRecord> initTop(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConnRecord> paginate(Paginate pag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConnRecord> paginate(int key, int value, Paginate pag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConnRecord> getConnUser(String sn) {
		List<ConnRecord> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	conn_records_temp\n"
				+ "WHERE\n" + "	enable = 1\n" + "AND sno = " + sn;
		try {
			list = this.jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<ConnRecord>(ConnRecord.class));
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public int batchUpdateKeep(List<ConnRecord> keepConnRecord) {
		int result = -1;
		String sql = "UPDATE conn_records_temp\n"
				+ "SET tx_bytes = :txBytes,\n" + " rx_bytes = :rxBytes,\n"
				+ " updated_at = :updatedAt\n" + "WHERE\n" + "	sno = :sno\n"
				+ "AND mac = :mac\n" + "AND `online` = :online";
		BeanPropertySqlParameterSource[] batchArgs = new BeanPropertySqlParameterSource[keepConnRecord
				.size()];
		for (int i = 0; i < keepConnRecord.size(); i++) {
			batchArgs[i] = new BeanPropertySqlParameterSource(
					keepConnRecord.get(i));
		}
		try {
			int[] results = this.jdbcTemplate.batchUpdate(sql, batchArgs);
			result = results.length;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int batchInsertOff(List<ConnRecord> offlineConnRecord) {
		int result = -1;
		String sql = "INSERT INTO conn_records (\n" + "	sno,\n" + "	mac,\n"
				+ "	`online`,\n" + "	offline,\n" + "	tx_bytes,\n"
				+ "	rx_bytes,\n" + "	created_at,\n" + "	updated_at,\n"
				+ "	`enable`\n" + ")\n" + "VALUES\n" + "	(\n" + "		:sno,\n"
				+ "		:mac,\n" + "		:online,\n" + "		:offline,\n"
				+ "		:txBytes,\n" + "		:rxBytes,\n" + "		:createdAt,\n"
				+ "		:updatedAt,\n" + "		:enable\n" + "	)";
		BeanPropertySqlParameterSource[] batchArgs = new BeanPropertySqlParameterSource[offlineConnRecord
				.size()];
		for (int i = 0; i < offlineConnRecord.size(); i++) {
			batchArgs[i] = new BeanPropertySqlParameterSource(
					offlineConnRecord.get(i));
		}
		try {
			int[] results = this.jdbcTemplate.batchUpdate(sql, batchArgs);
			result = results.length;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int batchDeleteOff(List<ConnRecord> offlineConnRecord) {
		int result = -1;
		String sql = "DELETE\n" + "FROM\n" + "	conn_records_temp\n" + "WHERE\n"
				+ "	sno = :sno\n" + "AND mac = :mac\n"
				+ "AND `online` = :online";
		BeanPropertySqlParameterSource[] batchArgs = new BeanPropertySqlParameterSource[offlineConnRecord
				.size()];
		for (int i = 0; i < offlineConnRecord.size(); i++) {
			batchArgs[i] = new BeanPropertySqlParameterSource(
					offlineConnRecord.get(i));
		}
		try {
			int[] results = this.jdbcTemplate.batchUpdate(sql, batchArgs);
			result = results.length;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int batchInsertNew(List<ConnRecord> newConnRecord) {
		int result = -1;
		String sql = "INSERT INTO conn_records_temp (\n" + "	sno,\n"
				+ "	mac,\n" + "	`online`,\n" + "	offline,\n" + "	tx_bytes,\n"
				+ "	rx_bytes,\n" + "	created_at,\n" + "	updated_at,\n"
				+ "	`enable`\n" + ")\n" + "VALUES\n" + "	(\n" + "		:sno,\n"
				+ "		:mac,\n" + "		:online,\n" + "		:offline,\n"
				+ "		:txBytes,\n" + "		:rxBytes,\n" + "		:createdAt,\n"
				+ "		:updatedAt,\n" + "		:enable\n" + "	)";
		BeanPropertySqlParameterSource[] batchArgs = new BeanPropertySqlParameterSource[newConnRecord
				.size()];
		for (int i = 0; i < newConnRecord.size(); i++) {
			batchArgs[i] = new BeanPropertySqlParameterSource(
					newConnRecord.get(i));
		}
		try {
			int[] results = this.jdbcTemplate.batchUpdate(sql, batchArgs);
			result = results.length;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
