package com.sojoline.model.response;

import com.google.gson.annotations.SerializedName;
import com.sojoline.model.bean.common.User;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class UserInfoResponse extends BaseResponse{
	@SerializedName("content")
	public User user;
}
