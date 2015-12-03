/*
 * ConnController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月20日 Created
 */
package com.zpd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpd.json.JsonMessage;
import com.zpd.utils.Const;
import com.zpd.utils.Msg;

/**
 * 设备配置控制器
 * 
 * @author Jacky
 * @version v1.0.0
 * @date 2015年11月20日
 * 
 */
@Controller
@RequestMapping("/inner/config")
public class InnerConfigController
{
	public String setConfig(ModelMap model,String sn)
	{
		int code = 1;
		// TODO
		String jsonData = JsonMessage.getJsonMsg(new Msg(code));
		model.addAttribute("data", jsonData);
		return Const.VIEW_JSON;
	}
}
