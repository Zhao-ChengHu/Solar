package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.TransformerData;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class TransformerDataResponse extends BaseResponse{
	@SerializedName("content")
	private TransformerData data;

	public TransformerData getData() {
		return data;
	}

	public void setData(TransformerData data) {
		this.data = data;
	}
}
