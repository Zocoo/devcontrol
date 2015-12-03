/**
 * 
 */
package com.zpd.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.zpd.dao.IVerdorDao;
import com.zpd.pojo.Vendor;
import com.zpd.utils.LogFactory;
import com.zpd.utils.Paginate;

/**
 * @author Administrator
 *
 */
public class VendorDaoImpl implements IVerdorDao {
	private final static Logger logger = LogFactory
			.getLogger(VendorDaoImpl.class);

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
	public int save(Vendor t) {
		int result = -1;
		String sql = "INSERT INTO vendors (\n" + "	name,\n" + "	intro,\n"
				+ "	created_at,\n" + "	updated_at,\n" + "	`enable`\n" + ")\n"
				+ "VALUES\n" + "	(\n" + "		:name,\n" + "		:intro,\n"
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
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Vendor t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vendor get(int id) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Vendor> initTop(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vendor> paginate(Paginate pag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vendor> paginate(int key, int value, Paginate pag) {
		// TODO Auto-generated method stub
		return null;
	}

}
