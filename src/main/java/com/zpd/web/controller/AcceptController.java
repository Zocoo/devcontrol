/*
 * AcceptController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月4日 Created
 */
package com.zpd.web.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpd.json.JsonMessage;
import com.zpd.pojo.DeviceStatus;
import com.zpd.pojo.wrap.Conns;
import com.zpd.service.IInstructionService;
import com.zpd.utils.Const;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.Msg;
import com.zpd.utils.MsgData;
import com.zpd.utils.RedisClient;

/**
 * TODO
 * 
 * @author wuyize
 * 
 * @version v1.0.0
 * @date 2015年12月4日
 *
 */
@Controller
@RequestMapping("/accept")
public class AcceptController implements ErrorCode
{
	private IInstructionService instructionService;

	public void setInstructionService(IInstructionService instructionService)
	{
		this.instructionService = instructionService;
	}

	/**
	 * 接受上层发过来控制设备的请求
	 */
	@RequestMapping("/ins")
	public String ins(ModelMap model, @RequestBody(required = true) String data)
			throws IOException
	{
		int code = FAILED;
		System.out.println("====>" + data);
		code = this.instructionService.dealData(data);
		Msg msg = new Msg(code);
		String jsonData = JsonMessage.getJsonMsg(msg);
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/**
	 * 接受上层发过来控制设备的请求
	 */
	@RequestMapping("/count")
	public String queryCount(ModelMap model, String sn) throws IOException
	{
		MsgData msg = null;
		int code = FAILED;
		if (!StringUtils.isEmpty(sn))
		{
			Conns cs = null;
			String name = "heartbeat" + sn;
			DeviceStatus ins = RedisClient.get(name, DeviceStatus.class);
			if (ins != null)
			{
				code = SUCCESS;
				System.out.println("====>" + sn);
				cs = new Conns();
				cs.setConns(ins.getClientcount());
			}
			msg = new MsgData(code);
			if (cs != null)
				msg.setData(cs);
		}
		String jsonData = JsonMessage.getJsonMsg(msg);
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}
}
