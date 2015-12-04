/*
 * IInstructionService.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
 */
package com.zpd.service;

import com.zpd.pojo.Instruction;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月3日
 *
 */
public interface IInstructionService extends IBaseService<Instruction>
{
	/**
	 * 处理上层发过来的数据
	 * 
	 * @param str
	 *            上层发过来的数据
	 * @return 处理是否成功
	 */
	Integer dealData(String str);

	/**
	 * 通过ESN查询没有执行的命令
	 * 
	 * @param esn
	 *            设备的ESN嘛
	 * @return 命名
	 */
	Instruction queryInsByEsn(String esn);
}
