/*
 * DeviceInfoServiceImpl.java
 * Copyright(C) 2013-2015 成都东方瑞呈科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015年11月22日 Created
 */
package com.zpd.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zpd.dao.IDeviceInfoDao;
import com.zpd.dao.IDeviceTypeDao;
import com.zpd.dao.IVerdorDao;
import com.zpd.pojo.DeviceInfo;
import com.zpd.pojo.DeviceType;
import com.zpd.pojo.Vendor;
import com.zpd.pojo.wrap.DeviceMsg;
import com.zpd.service.IDeviceInfoService;
import com.zpd.utils.ErrorCode;
import com.zpd.utils.Paginate;
import com.zpd.utils.Time;

/**
 * 设备信息服务接口实现
 * 
 * @author Jacky @version v1.0.0
 * @date 2015年11月22日
 * 
 */
public class DeviceInfoServiceImpl implements IDeviceInfoService, ErrorCode
{
	private IDeviceInfoDao	deviceInfoDao;
	private IVerdorDao		vendorDao;

	public void setVendorDao(IVerdorDao vendorDao)
	{
		this.vendorDao = vendorDao;
	}

	private IDeviceTypeDao deviceTypeDao;

	public void setDeviceTypeDao(IDeviceTypeDao deviceTypeDao)
	{
		this.deviceTypeDao = deviceTypeDao;
	}

	public void setDeviceInfoDao(IDeviceInfoDao deviceInfoDao)
	{
		this.deviceInfoDao = deviceInfoDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#save(java.lang.Object)
	 */
	@Override
	public int save(DeviceInfo t)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#delete(int)
	 */
	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#update(java.lang.Object)
	 */
	@Override
	public int update(DeviceInfo t)
	{
		return this.deviceInfoDao.update(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#get(int)
	 */
	@Override
	public DeviceInfo get(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#getCount()
	 */
	@Override
	public long getCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#getCount(int, int)
	 */
	@Override
	public long getCount(int key, int value)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#initTop(int)
	 */
	@Override
	public List<DeviceInfo> initTop(int limit)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#paginate(com.zpd.utils.Paginate)
	 */
	@Override
	public List<DeviceInfo> paginate(Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zpd.service.IBaseService#paginate(int, int,
	 * com.zpd.utils.Paginate)
	 */
	@Override
	public List<DeviceInfo> paginate(int key, int value, Paginate pag)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeviceInfo> getDeviceInfoBySn(String[] sn)
	{
		String strsn = "(";
		for (int i = 0; i < sn.length; i++)
		{
			if (i == 0)
				strsn = strsn + "'" + sn[i] + "'";
			else
				strsn = "," + strsn + "'" + sn[i] + "'";
		}
		strsn = strsn + ")";
		return this.deviceInfoDao.queryDeviceInfoBySn(strsn);
	}

	@Override
	public DeviceInfo getDeviceInfoBySn(String sn)
	{
		return this.deviceInfoDao.getDeviceInfoBySn(sn);
	}

	@Override
	public int updateFromMp(DeviceInfo di, DeviceMsg dm)
	{
		int code = -1;
		if (di != null && dm != null)
		{
			int unixTime = Time.toUnixTime(Time.now());
			if (!StringUtils.isEmpty(dm.getModel()))
			{
				DeviceType dt = null;
				dt = this.deviceInfoDao.getDeviceTypeByName(dm.getModel());
				if (dt == null)
				{
					dt = new DeviceType();
					dt.setEnable(true);
					dt.setIntro("");
					if (di.getDeviceCategoryId() != null
							&& !StringUtils.isEmpty((dm.getModel())))
					{
						dt.setName(dm.getModel());
						dt.setDeviceCategoryId(di.getDeviceCategoryId());
						dt.setCreatedAt(unixTime);
						dt.setUpdatedAt(unixTime);
						int result = this.deviceTypeDao.save(dt);
						if (result > 0)
							if (dt.getId() != null)
								di.setDeviceTypeId(dt.getId());
					}
				} else
				{
					di.setDeviceTypeId(dt.getId());
				}
			}
			if (!StringUtils.isEmpty(dm.getVendor()))
			{
				Vendor vd = null;
				DeviceType dt = null;
				if (di.getDeviceTypeId() != null)
				{
					dt = this.deviceTypeDao.get(di.getDeviceTypeId());
					if (dt != null)
					{
						vd = this.deviceInfoDao.getVendorByName(dm.getVendor());
						if (vd == null)
						{
							vd = new Vendor();
							vd.setCreatedAt(unixTime);
							vd.setEnable(true);
							vd.setUpdatedAt(unixTime);
							vd.setIntro("无");
							vd.setName(dm.getVendor());
							int result = this.vendorDao.save(vd);
							if (result > 0)
								if (vd.getId() != null)
									dt.setVendorId(vd.getId());
						} else
						{
							dt.setVendorId(vd.getId());
						}
						this.deviceTypeDao.update(dt);
					}
				}
			}
			if (dm.getStatus() != null)
				di.setNetState(dm.getStatus());
			if (!StringUtils.isEmpty(dm.getFireware()))
				di.setVersion(dm.getFireware());
			code = this.deviceInfoDao.update(di);
		}
		return code > 0 ? SUCCESS : FAILED;
	}

	@Override
	public DeviceInfo getDeviceByEsn(String esn)
	{
		return this.deviceInfoDao.getDeviceByEsn(esn);
	}

	@Override
	public void updateNetStat(String sn)
	{
		this.deviceInfoDao.updateNetStat(sn);
	}
}
