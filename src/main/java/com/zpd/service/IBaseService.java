/*
 * Article.java
 * Copyright(C) 2013-2015 东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-05-20 Created
 */
package com.zpd.service;

import java.util.List;

import com.zpd.utils.Paginate;

/**
 * 基础接口
 * 
 * @author Jacky
 * @version v1.0
 * @date 2015年9月11日
 * 
 * @param <T>
 */
public interface IBaseService<T>
{
	/**
	 * 保存对象 操作insert
	 * 
	 * @param t
	 * @return 影响的数据条数
	 */
	int save(T t);

	/**
	 * 删除指定的对象
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);

	/**
	 * 修改对象 操作update
	 * 
	 * @param t
	 */
	int update(T t);

	/**
	 * 查询指定的对象
	 * 
	 * @param id
	 * @return
	 */
	T get(int id);

	/**
	 * 查询数据条数
	 * 
	 * @return 总条数
	 */
	long getCount();

	/**
	 * 查询指定外键值的数据总条数
	 * 
	 * @param key
	 *            外键
	 * @param value
	 *            外键值
	 * @return 总条数
	 */
	long getCount(int key, int value);

	/**
	 * 查询指定条数的数据
	 * 
	 * @param limit
	 *            前几条
	 */
	List<T> initTop(int limit);

	/**
	 * 分页获取数据集
	 * 
	 * @param pag
	 *            分页参数
	 * @return
	 */
	List<T> paginate(Paginate pag);

	/**
	 * 分页获取指定外键的数据集
	 * 
	 * @param key
	 *            外键名
	 * @param value
	 *            外键值
	 * @param pag
	 *            分页参数
	 * @return
	 */
	List<T> paginate(int key, int value, Paginate pag);

}
