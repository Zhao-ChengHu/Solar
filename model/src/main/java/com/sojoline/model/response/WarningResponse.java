package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.solar.WarningInfo;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class WarningResponse extends BaseResponse{
	private Content content;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public class Content{
		private int per_page;
		private int total;
		private int page;

		@SerializedName("items")
		private List<WarningInfo> list;

		public int getPer_page() {
			return per_page;
		}

		public void setPer_page(int per_page) {
			this.per_page = per_page;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public List<WarningInfo> getList() {
			return list;
		}

		public void setList(List<WarningInfo> list) {
			this.list = list;
		}
	}
}
