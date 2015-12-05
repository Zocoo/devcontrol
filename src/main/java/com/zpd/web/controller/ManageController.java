/*
 * ManageController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
 */
package com.zpd.web.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zpd.json.JsonMessage;
import com.zpd.pojo.Data;
import com.zpd.pojo.DeviceInfo;
import com.zpd.pojo.Instruction;
import com.zpd.pojo.Message;
import com.zpd.pojo.wrap.ImageUpgrade;
import com.zpd.service.IDeviceInfoService;
import com.zpd.service.IInstructionService;
import com.zpd.utils.Const;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.MD5Msg;
import com.zpd.utils.RedisClient;
import com.zpd.utils.Time;

/**
 * 用于接收请求
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月3日
 *
 */
@Controller
@RequestMapping("/manage")
public class ManageController implements ErrorCode
{
	private IInstructionService	instructionService;
	private IDeviceInfoService	deviceInfoService;

	public void setDeviceInfoService(IDeviceInfoService deviceInfoService)
	{
		this.deviceInfoService = deviceInfoService;
	}

	public void setInstructionService(IInstructionService instructionService)
	{
		this.instructionService = instructionService;
	}

	/**
	 * 设备注册
	 */
	@RequestMapping("/register")
	public String register(ModelMap model,
			@RequestBody(required = true) String data, String sign)
					throws IOException
	{
		JSONObject json = null;
		DeviceInfo di = null;
		int code = FAILED;
		System.out.println("====>" + data);
		if (data != null)
		{
			json = JSON.parseObject(data);
			if (json != null)
				if (json.getString("gw_id") != null)
				{
					di = new DeviceInfo();
					di = this.deviceInfoService
							.getDeviceByEsn(json.getString("gw_id"));
				}
		}
		if (di != null)
			code = SUCCESS;
		Data da = new Data();
		Message msg = new Message();
		da.setCode(code);
		if (code == SUCCESS)
			da.setMessage("success");
		else
			da.setMessage("failed");
		da.setGw_id(json.getString("gw_id"));
		da.setMethod("system");
		da.setType("REQUEST");
		String jsonString = JSON.toJSONString(da);
		msg.setData(jsonString);
		String jsonData = JsonMessage.getJsonMsg(MD5Msg.Md5(jsonString));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/**
	 * 接收设备的心跳
	 */
	@RequestMapping("/ping")
	public String heartbeat(ModelMap model,
			@RequestBody(required = true) String data) throws IOException
	{
		String jsonString = "";
		Data da = null;
		JSONObject json = null;
		System.out.println("====>" + data);
		Message msg = new Message();
		if (data != null)
		{
			json = JSON.parseObject(data);
			if (json != null)
			{
				if (json.getString("gw_id") != null)
				{
					Instruction ins = this.instructionService
							.queryInsByEsn(json.getString("gw_id"));
					da = new Data();
					da.setGw_id(json.getString("gw_id"));
					da.setType("REQUEST");
					if (ins == null)
					{
						da.setOpreation("closeConn");
						da.setCode(0);
						da.setMessage("success");
					} else
					{
						boolean reusltCache = RedisClient.set("wuyize", ins);
						System.out.println(
								"==>" + reusltCache + "===>" + ins.getId());
						da.setTransaction_id(ins.getId());
						// 0重启，1升级，2修改ssid，3配置设备
						if (ins.getType().equals(0))
						{
							da.setOpreation("restart");
							da.setMethod("system");
						} else if (ins.getType().equals(1))
						{
							da.setOpreation("imageUpgrade");
							da.setMethod("system");
							ImageUpgrade valueSet = new ImageUpgrade();
							if (!StringUtils.isEmpty(ins.getUrl()))
								valueSet.setUrl(ins.getUrl());
							if (!StringUtils.isEmpty(ins.getVer()))
								valueSet.setVer(ins.getVer());
							da.setValueSet(valueSet);
						}
						if (ins.getType().equals(2))
						{
							da.setOpreation("setWirelessInfo");
							da.setMethod("wireless");
						}
					}
					jsonString = JSON.toJSONString(da);
				}
			}
		}
		msg.setData(data);
		String jsonData = JsonMessage.getJsonMsg(MD5Msg.Md5(jsonString));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/**
	 * 接收结果
	 */
	@RequestMapping("/result")
	public String result(ModelMap model,
			@RequestBody(required = true) String data) throws IOException
	{

		Instruction wuyize = RedisClient.get("wuyize", Instruction.class);
		if (wuyize != null)
		{
			System.out.println("从缓存中获取的对象，" + wuyize.getId());
		}

		String jsonString = "";
		Data da = null;
		JSONObject json = null;
		System.out.println("====>" + data);
		Message msg = new Message();
		int unixTime = Time.toUnixTime(Time.now());
		if (data != null)
		{
			json = JSON.parseObject(data);
			if (json != null)
			{
				Instruction reins = this.instructionService
						.get(json.getInteger("transaction_id"));
				reins.setEnable(true);
				reins.setUpdatedat(unixTime);
				reins.setNum(reins.getNum() + 1);
				if (json.getInteger("code").equals(0))
					reins.setResult(false);
				else
					reins.setResult(true);
				this.instructionService.update(reins);
				if (json.getString("gw_id") != null)
				{
					Instruction ins = this.instructionService
							.queryInsByEsn(json.getString("gw_id"));
					da = new Data();
					da.setGw_id(json.getString("gw_id"));
					da.setType("REQUEST");
					if (ins == null)
					{
						da.setOpreation("closeConn");
						da.setCode(0);
						da.setMessage("success");
					} else
					{
						da.setTransaction_id(ins.getId());
						// 0重启，1升级，2修改ssid，3配置设备
						if (ins.getType().equals(0))
						{
							da.setOpreation("restart");
							da.setMethod("system");
						} else if (ins.getType().equals(1))
						{
							da.setOpreation("imageUpgrade");
							da.setMethod("system");
							ImageUpgrade valueSet = new ImageUpgrade();
							if (!StringUtils.isEmpty(ins.getUrl()))
								valueSet.setUrl(ins.getUrl());
							if (!StringUtils.isEmpty(ins.getVer()))
								valueSet.setVer(ins.getVer());
							da.setValueSet(valueSet);
						}
						if (ins.getType().equals(2))
						{
							da.setOpreation("setWirelessInfo");
							da.setMethod("wireless");
						}
					}
					jsonString = JSON.toJSONString(da);
				}
			}
		}
		msg.setData(data);
		String jsonData = JsonMessage.getJsonMsg(MD5Msg.Md5(jsonString));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}
}
