/***************************************************************************************
 *				  		 Jackyyang Personal 版权所有
 *				Copyright (c) 2014 -***.Inc All Rights Reserved
 **************************************************************************************/
package com.zpd.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 日志工厂
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2014-3-18
 * 
 */
public class LogFactory
{
	private static Logger	logger;

	public static Logger getLogger(Class<?> clazz, Level level)
	{
		logger = getLogger(clazz);
		logger.setLevel(level);
		return logger;
	}

	public static Logger getLogger(Class<?> clazz)
	{
		if (logger == null)
		{
			return Logger.getLogger(clazz.getName());
		}
		return logger;
	}

}
