/***************************************************************************************
 *				  		 JackyYang Personal 版权所有
 *				Copyright (c) 2015 - 2020.Inc All Rights Reserved
 **************************************************************************************/
package com.zpd.utils;

/**
 * 错误常量
 * 
 * @author Jacky @version v1.0.0
 * @date 2015年5月18日
 * 
 */
public interface ErrorCode
{
	int			SUCCESS		= 0;
	int			FAILED		= 1;
	int			UPING		= 2;
	String[]	errorMsg	= { "SUCCESS", "FAILED",
			"设备还有没有执行完的升级命令，不能下发升级命名" };
}
