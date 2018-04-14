package com.sojoline.base.util;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.R;
import com.sojoline.basiclib.DebugLog;
import com.sojoline.model.dagger.ApiModule;
import com.sojoline.model.storage.AppInfoPreferences;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class AppUtils {

	public static final int WRITE_EXTERNAL_CODE = 1;
	public static final int CAMERA_CODE = 2;
	/**
	 * 判断用户是否登录成功
	 * @return
	 */
	public static boolean isLogin(){
		return !TextUtils.isEmpty(AppInfoPreferences.get().getToken());
	}

	/**
	 * 通过路径跳转到相应的activity
	 * @param path 路径
	 */
	public static void startActivity(String path){
		ARouter.getInstance().build(path).navigation();
	}

	/**
	 * 获取手机android系统版本号
	 * @return
	 */
	public static int getSDKInt(){
		return Build.VERSION.SDK_INT;
	}

	/**
	 * 获取图片保存路径
	 * @return
	 */
	public static String getCommonImageSavePath(){
		File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() +
				"/sojo/images");
		if (!file.exists()){
			boolean success = file.mkdirs();
			if (!success){
				DebugLog.log("创建失败");
			}
		}
		DebugLog.log(file.getAbsolutePath());
		return file.getAbsolutePath();
	}

	/**
	 * 清除用户数据
	 */
	public static void clearUserData(){
//		AppInfoPreferences.get().setPsd(null);
//		AppInfoPreferences.get().setUsername(null);
//		AppInfoPreferences.get().setUserId(null);
		AppInfoPreferences.get().setToken(null);
		AppInfoPreferences.get().setStationId(null);
		AppInfoPreferences.get().setModule(-1);
		AppInfoPreferences.get().setCreateTime(null);
		AppInfoPreferences.get().setSecretKey(null);
		// 清除用户信息
		AppInfoPreferences.get().setUser(null);
	}

	/*public static void appUpdate(final Activity activity){
		String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/sojo";
		Map<String, String> params = new HashMap<>();
		params.put("appType", "pv");
		new UpdateAppManager
				.Builder()
				.setActivity(activity)
				.setHttpManager(new OkGoUpdateHttpUtils())
				.setUpdateUrl(ApiModule.UPDATE_URL)
				.setParams(params)
				.setTargetPath(path)
				.setTopPic(R.drawable.ic_update_top)
				.build()
				.checkNewApp(new UpdateCallback(){
					@Override
					protected UpdateAppBean parseJson(String json) {
						UpdateAppBean bean = new UpdateAppBean();
						try {
							JSONObject object = new JSONObject(json);
							if (object.getInt("code") == 200){
								JSONObject content = object.getJSONObject("content");
								String newVersionName = content.getString("versionName");
								if (!TextUtils.isEmpty(newVersionName)){
									String[] newArrays = newVersionName.split("\\.");
									PackageManager pm = activity.getPackageManager();
									PackageInfo info = pm.getPackageInfo(activity.getPackageName(), 0);
									String versionName = info.versionName;
									String[] arrays = versionName.split("\\.");
									for (int i = 0; i < newArrays.length; i++) {
										if (Integer.parseInt(newArrays[i]) > Integer.parseInt(arrays[i])){
											bean.setUpdate("Yes");
											break;
										}
									}
									bean.setNewVersion(newVersionName);
									bean.setApkFileUrl(content.getString("updateUrl"));
									bean.setTargetSize(content.getString("versionSize") + "M");
									bean.setUpdateLog( "更新日志：\n" + content.getString("updateLog"));
								}
							}

						} catch (JSONException e) {
							e.printStackTrace();
						} catch (PackageManager.NameNotFoundException e) {
							e.printStackTrace();
						}
						return bean;
					}

					@Override
					protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
						DebugLog.log("app has update");
						updateAppManager.showDialogFragment();
					}

					@Override
					protected void noNewApp() {
						DebugLog.log("app no update");
					}
				});
	}*/
}
