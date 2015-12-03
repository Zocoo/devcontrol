/*
 * ConnRecord.java
 * Copyright(C) 2013-2015 成都PLZT科技有限公司
 * All rights reserved.
 * -----------------------------------------------
 * 2015-11-24 Created
 */
package com.zpd.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户连接记录表
 * 
 * @author Jacky
 * @version 1.0
 * @date 2015年11月24日
 */
public class ConnRecord implements Serializable {

    //设备序列号
    private String sno;
    //用户设备mac
    private String mac;
    //上线时间
    private Integer online;
    private Integer offline;
    //发送字节数
    private BigDecimal txBytes;
    //接收字节数
    private BigDecimal rxBytes;
    //发送包数
    private Long txPackages;
    //接收包数
    private Long rxPackages;
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
    public Integer getOnline() {
        return online;
    }
    public void setOnline(Integer online) {
        this.online = online;
    }
    public Integer getOffline() {
        return offline;
    }
    public void setOffline(Integer offline) {
        this.offline = offline;
    }
    public BigDecimal getTxBytes() {
        return txBytes;
    }
    public void setTxBytes(BigDecimal txBytes) {
        this.txBytes = txBytes;
    }
    public BigDecimal getRxBytes() {
        return rxBytes;
    }
    public void setRxBytes(BigDecimal rxBytes) {
        this.rxBytes = rxBytes;
    }
    public Long getTxPackages() {
        return txPackages;
    }
    public void setTxPackages(Long txPackages) {
        this.txPackages = txPackages;
    }
    public Long getRxPackages() {
        return rxPackages;
    }
    public void setRxPackages(Long rxPackages) {
        this.rxPackages = rxPackages;
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
        ConnRecord other = (ConnRecord) that;
        return (this.getSno() == null ? other.getSno() == null : this.getSno().equals(other.getSno()))
            && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()))
            && (this.getOnline() == null ? other.getOnline() == null : this.getOnline().equals(other.getOnline()))
            && (this.getOffline() == null ? other.getOffline() == null : this.getOffline().equals(other.getOffline()))
            && (this.getTxBytes() == null ? other.getTxBytes() == null : this.getTxBytes().equals(other.getTxBytes()))
            && (this.getRxBytes() == null ? other.getRxBytes() == null : this.getRxBytes().equals(other.getRxBytes()))
            && (this.getTxPackages() == null ? other.getTxPackages() == null : this.getTxPackages().equals(other.getTxPackages()))
            && (this.getRxPackages() == null ? other.getRxPackages() == null : this.getRxPackages().equals(other.getRxPackages()))
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
        result = prime * result + ((getOnline() == null) ? 0 : getOnline().hashCode());
        result = prime * result + ((getOffline() == null) ? 0 : getOffline().hashCode());
        result = prime * result + ((getTxBytes() == null) ? 0 : getTxBytes().hashCode());
        result = prime * result + ((getRxBytes() == null) ? 0 : getRxBytes().hashCode());
        result = prime * result + ((getTxPackages() == null) ? 0 : getTxPackages().hashCode());
        result = prime * result + ((getRxPackages() == null) ? 0 : getRxPackages().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        return result;
    }
}