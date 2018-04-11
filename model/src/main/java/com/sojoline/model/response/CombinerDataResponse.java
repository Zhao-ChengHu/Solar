package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.CombinerData;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CombinerDataResponse extends BaseResponse{
	@SerializedName("content")
	private CombinerData data;

	public CombinerData getData() {
		return data;
	}

	public void setData(CombinerData data) {
		this.data = data;
	}
}
