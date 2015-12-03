/*
 * ManageController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年12月3日 Created
 */
package com.zpd.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zpd.json.JsonMessage;
import com.zpd.pojo.Message;
import com.zpd.service.IInstructionService;
import com.zpd.utils.Const;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.MD5Msg;
import com.zpd.utils.Msg;

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
	private IInstructionService instructionService;

	public void setInstructionService(IInstructionService instructionService)
	{
		this.instructionService = instructionService;
	}

	/**
	 * 接收设备的心跳
	 */
	@RequestMapping("/heartbeat")
	public String heartbeat(ModelMap model, HttpServletRequest request,
			@RequestBody(required = true) String data, String sign)
					throws IOException
	{
		System.out.println("====>" + data);
		Message msg = new Message();
		msg.setJson(data);
		String jsonData = JsonMessage.getJsonMsg(MD5Msg.Md5(data));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}

	/**
	 * 接受上层发过来控制设备的请求
	 */
	@RequestMapping("/ins")
	public String ins(ModelMap model, HttpServletRequest request,
			@RequestBody(required = true) String data) throws IOException
	{
		int code = FAILED;
		System.out.println("====>" + data);
		code = this.instructionService.dealData(data);
		Msg msg = new Msg(code);
		String jsonData = JsonMessage.getJsonMsg(msg);
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}
}
