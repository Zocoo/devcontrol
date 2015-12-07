/*
 * InstructionServiceImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
 */
package com.zpd.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpd.dao.IInstructionDao;
import com.zpd.pojo.Instruction;
import com.zpd.pojo.Version;
import com.zpd.service.IInstructionService;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.Paginate;
import com.zpd.utils.Time;

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
		return this.instructionDao.update(t);
	}

	@Override
	public Instruction get(int id)
	{
		return this.instructionDao.get(id);
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

		JSONObject json = null;
		if (str != null)
		{
			json = JSON.parseObject(str);
			if (json != null)
				if (json.getString("operation").equals("restart"))
				{
					if (json.getJSONArray("sn") != null)
					{
						JSONArray ja = json.getJSONArray("sn");
						if (ja != null)
						{
							int unixTime = Time.toUnixTime(Time.now());
							for (int i = 0; i < ja.size(); i++)
							{
								Instruction ins = new Instruction();
								ins.setEsn(ja.getString(i));
								ins.setType(0);
								ins.setCreatedat(unixTime);
								ins.setUpdatedat(unixTime);
								ins.setEnable(false);
								ins.setResult(false);
								ins.setNum(0);
								try
								{
									result = this.instructionDao.save(ins);
								} catch (Exception e)
								{
									System.out.println("插入数据错误：" + i + "<===>"
											+ ja.getString(i));
								}
							}
						}
					}
				} else if (json.getString("operation").equals("imageUpgrade"))
				{
					if (json.getJSONArray("sn") != null)
					{
						JSONArray ja = json.getJSONArray("sn");
						if (ja != null)
						{
							int unixTime = Time.toUnixTime(Time.now());
							for (int i = 0; i < ja.size(); i++)
							{
								Instruction ins = new Instruction();
								ins.setEsn(ja.getString(i));
								ins.setType(1);
								Version version = new Version();
								if (json.getInteger("version") != null)
								{
									version = this.instructionDao.queryVersion(
											json.getInteger("version"));
									if (version != null)
									{
										if (!StringUtils
												.isEmpty(version.getUrl()))
											ins.setUrl(version.getUrl());
										if (!StringUtils
												.isEmpty(version.getVersion()))
											ins.setVer(version.getVersion());
									}
								}
								ins.setCreatedat(unixTime);
								ins.setUpdatedat(unixTime);
								ins.setEnable(false);
								ins.setResult(false);
								ins.setNum(0);
								try
								{
									result = this.instructionDao.save(ins);
								} catch (Exception e)
								{
									System.out.println("插入数据错误：" + i + "<===>"
											+ ja.getString(i));
								}
							}
						}
					}
				} else if (json.getString("operation").equals("setSsid"))
				{
					if (json.getJSONArray("sn") != null)
					{
						JSONArray ja = json.getJSONArray("sn");
						String ssid = json.getString("ssid");
						if (ja != null && ssid != null)
						{
							int unixTime = Time.toUnixTime(Time.now());
							for (int i = 0; i < ja.size(); i++)
							{
								Instruction ins = new Instruction();
								ins.setEsn(ja.getString(i));
								ins.setType(2);
								ins.setSsid(ssid);
								ins.setCreatedat(unixTime);
								ins.setUpdatedat(unixTime);
								ins.setEnable(false);
								ins.setResult(false);
								ins.setNum(0);
								try
								{
									result = this.instructionDao.save(ins);
								} catch (Exception e)
								{
									System.out.println("插入数据错误：" + i + "<===>"
											+ ja.getString(i));
								}
							}
						}
					}
				}
		}
		return result > 0 ? SUCCESS : FAILED;
	}

	@Override
	public Instruction queryInsByEsn(String esn)
	{
		return this.instructionDao.queryInsByEsn(esn);
	}

}
