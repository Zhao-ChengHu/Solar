package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.CombinerData;
import com.sojoline.model.bean.solar.MonitorDate;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonitorDataResponse extends BaseResponse{
	@SerializedName("content")
	private MonitorDate data;

	public MonitorDate getData() {
		return data;
	}

	public void setData(MonitorDate data) {
		this.data = data;
	}
}
