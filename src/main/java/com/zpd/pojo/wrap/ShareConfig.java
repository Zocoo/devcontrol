/**
 * 
 */
package com.zpd.pojo.wrap;

import java.util.List;

/**
 * @author wuyize
 *
 */
public class ShareConfig {
	private String shareConfig;
	private List<DevConfig> devConfig;

	public String getShareConfig() {
		return shareConfig;
	}

	public void setShareConfig(String shareConfig) {
		this.shareConfig = shareConfig;
	}

	public List<DevConfig> getDevConfig() {
		return devConfig;
	}

	public void setDevConfig(List<DevConfig> devConfig) {
		this.devConfig = devConfig;
	}

}
