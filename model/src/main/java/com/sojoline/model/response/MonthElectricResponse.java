package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.MonthElectric;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MonthElectricResponse extends BaseResponse {
	public Content content;

	public class Content {
		@SerializedName("pvOperationStatic")
		private List<MonthElectric > list;

		public List<MonthElectric > getList() {
			return list;
		}

		public void setList(List<MonthElectric > list) {
			this.list = list;
		}
	}
}
