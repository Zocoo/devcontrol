/*
 * InstructionServiceImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
 */
package com.zpd.service.impl;

import java.util.List;

import com.zpd.dao.IInstructionDao;
import com.zpd.pojo.Instruction;
import com.zpd.service.IInstructionService;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.Paginate;

/**
 * TODO
 * 
 * @author wuyize @version v1.0.0
 * @date 2015年12月3日
 *
 */
public class InstructionServiceImpl implements IInstructionService, ErrorCode
{
	private IInstructionDao instructionDao;

	public void setInstructionDao(IInstructionDao instructionDao)
	{
		this.instructionDao = instructionDao;
	}

	@Override
	public int save(Instruction t)
	{
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Instruction get(int id)
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
	public Integer dealData(String str)
	{
		int result = -1;
		Instruction ins = new Instruction();
		result = this.instructionDao.save(ins);
		return result > 0 ? SUCCESS : FAILED;
	}

	@Override
	public Instruction queryInsByEsn(String esn)
	{
		return this.instructionDao.queryInsByEsn(esn);
	}

}
