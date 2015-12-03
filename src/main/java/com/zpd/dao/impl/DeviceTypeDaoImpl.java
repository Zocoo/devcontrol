/**
 * 
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

import com.zpd.dao.IDeviceTypeDao;
import com.zpd.pojo.DeviceType;
import com.zpd.utils.LogFactory;
import com.zpd.utils.Paginate;

/**
 * @author wuyize
 *
 */
public class DeviceTypeDaoImpl implements IDeviceTypeDao {
	private final static Logger logger = LogFactory
			.getLogger(DeviceTypeDaoImpl.class);

	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * set jdbc Template
	 * 
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(DeviceType t) {
		int result = -1;
		String sql = "INSERT INTO device_types (\n"
				+ "	`device_category_id`,\n"
				+ "	`name`,\n"
				+ "	`intro`,\n"
				+ "	`created_at`,\n"
				+ "	`updated_at`,\n"
				+ "	`enable`\n"
				+ ")\n"
				+ "VALUES\n"
				+ "	(\n"
				+ "	:deviceCategoryId ,:name ,:intro ,:createdAt ,:updatedAt ,:enable\n"
				+ "	)";
		SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
		KeyHolder key = new GeneratedKeyHolder();
		try {
			result = this.jdbcTemplate.update(sql, sps, key);
			t.setId(key.getKey().intValue());
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DeviceType t) {
		int result = -1;
		String sql = "update device_types set `device_category_id`=:deviceCategoryId,\n"
				+ "`created_at`=:createdAt,`updated_at`=:updatedAt,\n"
				+ "`vendor_id`=:vendorId,`name`=:name,`intro`=:intro ,`vendor_id`=:vendorId WHERE `id`=:id";
		SqlParameterSource sps = new BeanPropertySqlParameterSource(t);
		try {
			result = this.jdbcTemplate.update(sql, sps);
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public DeviceType get(int id) {
		List<DeviceType> list = null;
		String sql = "SELECT\n" + "	*\n" + "FROM\n" + "	device_types\n"
				+ "WHERE\n" + "	ENABLE = 1\n" + "AND id = " + id;
		try {
			list = this.jdbcTemplate.query(sql,
					new BeanPropertyRowMapper<DeviceType>(DeviceType.class));
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
		}
		return (list != null && list.size() == 1) ? list.get(0) : null;
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCount(int key, int value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DeviceType> initTop(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceType> paginate(Paginate pag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceType> paginate(int key, int value, Paginate pag) {
		// TODO Auto-generated method stub
		return null;
	}

}
