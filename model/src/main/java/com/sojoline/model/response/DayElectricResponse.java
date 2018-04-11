package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.DayElectric;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DayElectricResponse extends BaseResponse {
	public Content content;

	public class Content {
		@SerializedName("pvOperationStatic")
		private List<DayElectric> list;

		public List<DayElectric> getList() {
			return list;
		}

		public void setList(List<DayElectric> list) {
			this.list = list;
		}
	}
}
