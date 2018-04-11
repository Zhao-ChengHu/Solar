package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class LoginResponse extends BaseResponse{

	@SerializedName("content")
	public Content content;

	public class Content{
		public String username;
		public String token;
		public String user_id;
		public String CompanyCode;
		public String CompanyName;
		public String userType;
	}
}
