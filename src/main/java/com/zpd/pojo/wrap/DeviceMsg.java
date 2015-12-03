/*
 * ConnController.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月20日 Created
 */
package com.zpd.pojo.wrap;

/**
 * 接送mp发送过来的数据
 * 
 * @author wuyize
 * @version v1.0.0
 * @date 2015年11月20日
 * 
 */
public class DeviceMsg {
	private String sn;
	private Integer status;
	private String vendor;
	private String model;
	private String fireware;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFireware() {
		return fireware;
	}

	public void setFireware(String fireware) {
		this.fireware = fireware;
	}

}
