package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.YearElectric;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class YearElectricResponse extends BaseResponse {
	public Content content;

	public class Content {
		@SerializedName("pvOperationStatic")
		private List<YearElectric > list;

		public List<YearElectric > getList() {
			return list;
		}

		public void setList(List<YearElectric > list) {
			this.list = list;
		}
	}
}
