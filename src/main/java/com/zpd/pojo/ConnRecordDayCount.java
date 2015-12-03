/*
 * ConnRecordDayCount.java
 * Copyright(C) 2013-2015 成都PLZT科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-24 Created
 */
package com.zpd.pojo;

import java.io.Serializable;

/**
 * 用户连接记录按天统计表
 * 
 * @author Jacky
 * @version 1.0
 * @date 2015年11月24日
 */
public class ConnRecordDayCount implements Serializable {

    //设备序列号
    private String sno;
    //用户设备mac
    private String mac;
    //年月日凌晨时间
    private Integer date;
    //上线时间
    private Integer num;
    //创建时间
    private Integer createdAt;
    //修改时间
    private Integer updatedAt;
    //是否可用
    private Boolean enable;
    private static final long serialVersionUID = 1L;

    public String getSno() {
        return sno;
    }
    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }
    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }
    public Integer getDate() {
        return date;
    }
    public void setDate(Integer date) {
        this.date = date;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Integer getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
    public Integer getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Boolean getEnable() {
        return enable;
    }
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ConnRecordDayCount other = (ConnRecordDayCount) that;
        return (this.getSno() == null ? other.getSno() == null : this.getSno().equals(other.getSno()))
            && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSno() == null) ? 0 : getSno().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        return result;
    }
}