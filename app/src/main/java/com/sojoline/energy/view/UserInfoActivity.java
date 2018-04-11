package com.sojoline.energy.view;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsNonRationale;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.sojoline.base.util.AppUtils;
import com.sojoline.base.util.FileUtils;
import com.sojoline.base.util.GlideLoader;
import com.sojoline.base.util.ValidateUtils;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.base.widget.InputDialog;
import com.sojoline.base.widget.NameLengthFilter;
import com.sojoline.basiclib.DebugLog;
import com.sojoline.energy.R;
import com.sojoline.model.bean.common.User;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.presenter.common.uploadFile.UploadFileContract;
import com.sojoline.presenter.common.uploadFile.UploadFilePresenter;
import com.sojoline.presenter.common.user.UserContract;
import com.sojoline.presenter.common.user.UserPresenter;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/15
 *     desc   : 用户信息界面
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/login/user_info")
@PermissionsRequestSync(permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
							value = {AppUtils.WRITE_EXTERNAL_CODE, AppUtils.CAMERA_CODE})
public class UserInfoActivity extends BaseCompatActivity implements UserContract.View,UploadFileContract.View{

	@BindView(R.id.iv_photo)
	ImageView ivPhoto;
	@BindView(R.id.iv_change)
	ImageView ivChange;
	@BindView(R.id.tv_name)
	TextView tvName;
	@BindView(R.id.tv_gender)
	TextView tvGender;
	@BindView(R.id.tv_mobile)
	TextView tvMobile;
	@BindView(R.id.tv_email)
	TextView tvEmail;
	@BindView(R.id.tv_address)
	TextView tvAddress;

	private PopupWindow 	popWindow;
	private String 			photoCropPath;
	private Uri 			imageUri;

	private String nick;
	private String gender;
	private String email;
	private String address;
	private String phone;
	private String imgPath;

	private static final int REQUEST_ALBUM = 50;
	private static final int REQUEST_CAMERA = 51;
	private static final int REQUEST_CROP = 52;
	private int current = 0;
	private UserPresenter presenter;
	private UploadFilePresenter filePresenter;
	private File file;
	private User user;

	public static void navigation() {
		ARouter.getInstance().build("/app/login/user_info").navigation();
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new UserPresenter();
		presenter.attachView(this);
		filePresenter = new UploadFilePresenter();
		filePresenter.attachView(this);
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_user_info);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("个人信息");
		user = AppInfoPreferences.get().getUser();
		if (user == null){
			showToast("用户信息错误");
		}else {
			nick = user.getNickname();
			gender = user.getGender();
			email = user.getEmail();
			address = user.getAddress();
			phone = user.getTelephone();
			imgPath = user.getHeader();
		}

		if (TextUtils.isEmpty(nick)){
			tvName.setText(user.getUsername());
		}else {
			tvName.setText(nick);
		}

		if (TextUtils.isEmpty(gender)){
			tvGender.setText("未设置");
		}else {
			tvGender.setText(gender);
		}

		if (TextUtils.isEmpty(phone)){
			tvMobile.setText(user.getUsername());
		}else {
			tvMobile.setText(phone);
		}

		if (TextUtils.isEmpty(email)){
			tvEmail.setText("未设置");
		}else {
			tvEmail.setText(email);
		}

		if (TextUtils.isEmpty(address)){
			tvAddress.setText("未设置");
		}else {
			tvAddress.setText(address);
		}

		if (!TextUtils.isEmpty(imgPath)){
			GlideLoader.getInstance().displayCircleImage(this, imgPath, ivPhoto);
		}
	}

	/**
	 * 选择性别
	 */
	private void selectGender(){
		final String str = tvGender.getText().toString();
		View popView = LayoutInflater.from(this).inflate(R.layout.view_popup, null);
		final TextView tvOne = (TextView) popView.findViewById(R.id.tv_one);
		tvOne.setText("男");
		final TextView tvTwo = (TextView) popView.findViewById(R.id.tv_two);
		tvTwo.setText("女");
		TextView tvCancel = (TextView) popView.findViewById(R.id.tv_cancel);
		View.OnClickListener popListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == tvOne){
					tvGender.setText("男");
				}else if (v == tvTwo){
					tvGender.setText("女");
				}
				//性别改变了
				if (!str.equals(tvGender.getText().toString())){
					updateInfo();
				}
				popWindow.dismiss();
			}
		};
		tvCancel.setOnClickListener(popListener);
		tvOne.setOnClickListener(popListener);
		tvTwo.setOnClickListener(popListener);
		popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		popWindow.setFocusable(true);
		popWindow.setTouchable(true);
		popWindow.setOutsideTouchable(false);
		popWindow.setAnimationStyle(R.style.AnimBottom);
		popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				setBackgroundAlpha(1);
			}
		});
		popWindow.showAtLocation(popWindow.getContentView(), Gravity.BOTTOM, 0, 0);
		setBackgroundAlpha(0.7f);
	}

	/**
	 * 选择图片
	 */
	private void selectPhoto(){
		View popView = LayoutInflater.from(this).inflate(R.layout.view_popup, null);
		final TextView tvOne = (TextView) popView.findViewById(R.id.tv_one);
		tvOne.setText("拍照");
		final TextView tvTwo = (TextView) popView.findViewById(R.id.tv_two);
		tvTwo.setText("选择图片");
		TextView tvCancel = (TextView) popView.findViewById(R.id.tv_cancel);
		View.OnClickListener popListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == tvOne){
					//获取写和照相机权限
					current = 1;
					Permissions4M.get(UserInfoActivity.this)
							.requestUnderM(true).
							requestSync();
				}else if (v == tvTwo){
					//获取写权限
					current = 2;
					Permissions4M.get(UserInfoActivity.this)
							.requestUnderM(true)
							.requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
							.requestCodes(AppUtils.WRITE_EXTERNAL_CODE)
							.requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
							.request();
				}
				popWindow.dismiss();
			}
		};
		tvCancel.setOnClickListener(popListener);
		tvOne.setOnClickListener(popListener);
		tvTwo.setOnClickListener(popListener);
		popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		popWindow.setFocusable(true);
		popWindow.setTouchable(true);
		popWindow.setOutsideTouchable(false);
		popWindow.setAnimationStyle(R.style.AnimBottom);
		popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				setBackgroundAlpha(1);
			}
		});
		popWindow.showAtLocation(popWindow.getContentView(), Gravity.BOTTOM, 0, 0);
		setBackgroundAlpha(0.7f);
	}

	/**
	 * dialog
	 * @param textView	显示的TextView
	 * @param title		标题
	 * @param filter 	过滤器
	 * @param prompt	提示信息
	 */
	private void showInputDialog(final TextView textView, String title, InputFilter filter, String prompt){
		InputDialog dialog = new InputDialog(this);
		dialog.setContent(textView.getText().toString());
		dialog.setTitle(title);
		dialog.setFilter(filter);
		dialog.setPrompt(prompt);
		dialog.setOnContentChangedListener(new InputDialog.OnContentChangedListener() {
			@Override
			public void contentChanged(String content) {
				if (!TextUtils.isEmpty(content.trim())){
					//手机号
					if (textView == tvMobile){
						if (ValidateUtils.validatePhone(content)){
							tvMobile.setText(content);
							updateInfo();
						}else {
							showToast("手机号不正确");
						}
					}else if (textView == tvEmail){
						//邮箱
						if (ValidateUtils.validateEmail(content)){
							tvEmail.setText(content);
							updateInfo();
						}else {
							showToast("邮箱不正确");
						}
					}else if (textView == tvAddress){
						//地址
						tvAddress.setText(content);
						updateInfo();
					}else if (textView == tvName){
						//昵称
						tvName.setText(content);
						HashMap<String, String> map = new HashMap<>();
						map.put("nickname", content);
						presenter.changeNick(map);
					}
				}

			}
		});
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	@OnClick({R.id.iv_photo, R.id.tv_name, R.id.tv_gender, R.id.tv_mobile, R.id.tv_email,
			R.id.tv_address, R.id.iv_change})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.iv_photo:
				selectPhoto();
				break;
			case R.id.tv_name:
			case R.id.iv_change:
				NameLengthFilter filter = new NameLengthFilter(15);
				showInputDialog(tvName, "请输入昵称", filter, null);
				break;
			case R.id.tv_gender:
				selectGender();
				break;
			case R.id.tv_mobile:
				InputFilter.LengthFilter phoneFilter = new InputFilter.LengthFilter(11);
				showInputDialog(tvMobile, "请输入手机号",phoneFilter, null);
				break;
			case R.id.tv_email:
				InputFilter.LengthFilter emailFilter = new InputFilter.LengthFilter(25);
				showInputDialog(tvEmail, "请输入邮箱",emailFilter, null);
				break;
			case R.id.tv_address:
				InputFilter.LengthFilter addressFilter = new InputFilter.LengthFilter(30);
				showInputDialog(tvAddress, "请输入地址",addressFilter, null);
				break;
			default:
				break;
		}
	}

	/**
	 * 设置背景透明度
	 * @param alpha 透明度
	 */
	private void setBackgroundAlpha(float alpha){
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.alpha = alpha;
		getWindow().setAttributes(params);
	}

	/**
	 * 打开图片库
	 */
	public void openAlbumWithPermission(){
		photoCropPath = AppUtils.getCommonImageSavePath() + System.currentTimeMillis() + ".jpg";
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		startActivityForResult(intent, REQUEST_ALBUM);
	}

	/**
	 * 打开相机
	 */
	public void openCameraWithPermission(){
		photoCropPath = AppUtils.getCommonImageSavePath() + System.currentTimeMillis() + ".jpg";
		File temp = new File(AppUtils.getCommonImageSavePath() + "/head.jpg");
		imageUri = Uri.fromFile(temp);

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		ContentValues values = new ContentValues();
		imageUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(intent, REQUEST_CAMERA);
	}

	@PermissionsGranted({AppUtils.WRITE_EXTERNAL_CODE, AppUtils.CAMERA_CODE})
	public void granted(int code){
		if (code == AppUtils.WRITE_EXTERNAL_CODE && current == 2){
			openAlbumWithPermission();
		}
		if (code == AppUtils.CAMERA_CODE && current == 1){
			openCameraWithPermission();
		}
	}

	/**
	 * 剪切图片
	 */
	private void cropPhoto() {
		int imageSize = 600;
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(imageUri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// 去黑边
		intent.putExtra("scale", true);
		// 去黑边
		intent.putExtra("scaleUpIfNeeded", true);
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", imageSize);
		intent.putExtra("outputY", imageSize);
		////设置为不返回数据
		intent.putExtra("return-data", false);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true);

		File temp = new File(photoCropPath);
		temp.deleteOnExit();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(temp));
		intent.putExtra("scale", true);
		startActivityForResult(intent, REQUEST_CROP);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		current = 0;
		switch (requestCode) {
			case REQUEST_ALBUM:
				if (data != null) {
					imageUri = data.getData();
					cropPhoto();
				}
				break;
			case REQUEST_CAMERA:
				if (resultCode == RESULT_OK) {
					cropPhoto();
				}
				break;
			case REQUEST_CROP:
				if (data != null) {
					file = new File(photoCropPath);
					if (file.exists()) {
						GlideLoader.getInstance().displayCircleImage(this, file.getAbsolutePath(), ivPhoto);
						//上传头像图片
						String fileMD5 = FileUtils.getFileMd5_16(file);
//						String fileMD5 = FileUtils.getFileMD5_32(file);
						DebugLog.log("fileMD5:" + fileMD5);
						MultipartBody body = new MultipartBody.Builder()
								.setType(MultipartBody.FORM)
								.addFormDataPart("md5orsha1", fileMD5 == null ? "" : fileMD5)
								.addFormDataPart("upfile", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
								.build();
						filePresenter.uploadImage(body);
					}
				}
				break;
			default:
				break;
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		Permissions4M.onRequestPermissionsResult(this,requestCode, grantResults);
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

	@PermissionsDenied({AppUtils.WRITE_EXTERNAL_CODE, AppUtils.CAMERA_CODE})
	public void denied(int code){
		if (code == AppUtils.WRITE_EXTERNAL_CODE){
			showToast("获取写权限失败，无法更换头像");
		}
		if (code == AppUtils.CAMERA_CODE){
			showToast("获取摄像头权限失败，无法更换头像");
		}
	}

	@PermissionsNonRationale({AppUtils.WRITE_EXTERNAL_CODE, AppUtils.CAMERA_CODE})
	public void nonRationale(int code, final Intent intent){
		if (code == AppUtils.WRITE_EXTERNAL_CODE){
			new AlertDialog.Builder(this)
					.setMessage("我们需要访问存储空间的权限来临时保存头像信息，否则无法更换头像")
					.setNegativeButton("取消", null)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							startActivity(intent);
						}
					})
					.setCancelable(false)
					.show();
		}
		if (code == AppUtils.CAMERA_CODE){
			new AlertDialog.Builder(this)
					.setMessage("我们需要摄像头权限来拍照，否则无法更换头像")
					.setNegativeButton("取消", null)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							startActivity(intent);
						}
					})
					.setCancelable(false)
					.show();
		}
	}

	/**
	 * 更新用户信息
	 */
	private void updateInfo(){
		HashMap<String, String> map = new HashMap<>();
		if ("未设置".equals(tvGender.getText().toString())){
			map.put("gender", "");
		}else {
			map.put("gender", tvGender.getText().toString());
			user.setGender(tvGender.getText().toString());
		}

		map.put("mobileNum", tvMobile.getText().toString());
		user.setTelephone(tvMobile.getText().toString());

		if ("未设置".equals(tvEmail.getText().toString())){
			map.put("email", "");
		}else {
			map.put("email", tvEmail.getText().toString());
			user.setEmail(tvEmail.getText().toString());
		}

		if ("未设置".equals(tvAddress.getText().toString())){
			map.put("address", "");
		}else {
			map.put("address", tvAddress.getText().toString());
			user.setAddress(tvAddress.getText().toString());
		}

		presenter.changeUserInfo(map);
	}

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void showNormal() {

	}

	@Override
	public void requestFailed(String msg) {
		if (TextUtils.isEmpty(msg)){
			showToast(R.string.network_not_available);
		}else {
			showToast(msg);
		}
	}

	@Override
	public void uploadFileSuccess(String url) {
		if (!TextUtils.isEmpty(url)){
			//修改头像url
			HashMap<String, String> map = new HashMap<>();
			map.put("ImgUrl", url);
			user.setHeader(url);
			presenter.changeUserHeader(map);
		}
	}

	@Override
	public void uploadFileFailed() {
		showToast("文件上传失败，请重试");
	}

	@Override
	public void changeHeaderSuccess() {
		AppInfoPreferences.get().setUser(user);
		if (file != null) {
			file.deleteOnExit();
		}
	}

	@Override
	public void changeInfoSuccess() {
		AppInfoPreferences.get().setUser(user);
	}

	@Override
	public void changeNickSuccess() {
		user.setNickname(tvName.getText().toString());
		AppInfoPreferences.get().setUser(user);
	}

	@Override
	public void success(User user) {
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
		filePresenter.detachView();
	}

}
