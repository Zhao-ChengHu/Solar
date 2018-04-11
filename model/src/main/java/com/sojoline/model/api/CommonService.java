package com.sojoline.model.api;

import com.sojoline.model.request.CaptchaRequest;
import com.sojoline.model.request.LoginRequest;
import com.sojoline.model.request.RegisterRequest;
import com.sojoline.model.response.BaseResponse;
import com.sojoline.model.response.LoginResponse;
import com.sojoline.model.response.RegisterResponse;
import com.sojoline.model.response.UploadFileResponse;
import com.sojoline.model.response.UserInfoResponse;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/20
 *     desc   : 基础接口
 *     version: 1.0
 * </pre>
 */

public interface CommonService {
	/**
	 * 登录
	 * @param request 请求参数
	 * @return
	 */
	@POST("v1/login")
	Observable<LoginResponse> login(@Body LoginRequest request);

	/**
	 * 注册
	 * @param request 请求参数
	 * @return
	 */
	@POST("v1/register")
	Observable<RegisterResponse> register(@Body RegisterRequest request);

	/**
	 * 忘记密码
	 * @param map
	 * @return
	 */
	@POST("v1/account/password/forget")
	Observable<BaseResponse> forgetPsd(@Body HashMap<String, String> map);

	/**
	 * 修改密码
	 * @param map
	 * @return
	 */
	@POST("v1/account/password/reset")
	Observable<BaseResponse> resetPsd(@Body HashMap<String, String> map);

	/**
	 * 获取用户信息
	 * @return
	 */
	@GET("v1/profile")
	Observable<UserInfoResponse> getUserInfo();

	/**
	 * 修改用户头像
	 * @return
	 */
	@POST("v1/my/headerimg/upload")
	Observable<BaseResponse> updatedUserHeader(@Body HashMap<String, String> map);

	/**
	 * 修改用户昵称
	 * @param map
	 * @return
	 */
	@POST("v1/nickname/change")
	Observable<BaseResponse> updateNickname(@Body HashMap<String, String> map);

	/**
	 * 修改用户信息
	 * @param map
	 * @return
	 */
	@POST("v1/profile/update")
	Observable<BaseResponse> updateUserInfo(@Body HashMap<String, String> map);

	/**
	 * 发送短信验证码
	 * @param request
	 * @return
	 */
	@POST("v1/captcha")
	Observable<BaseResponse> sendCaptcha(@Body CaptchaRequest request);

	/**
	 * 上传图片
	 * @param request
	 * @return
	 */
	@POST("fileupload")
	Observable<UploadFileResponse> uploadImage(@Body RequestBody request);

	/**
	 * 意见反馈
	 * @param map
	 * @return
	 */
	@POST("v1/leavemsg/submit")
	Observable<BaseResponse> suggestion(@Body HashMap<String, Object> map);
}
