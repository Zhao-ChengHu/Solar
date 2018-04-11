package com.sojoline.model.dagger;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sojoline.basiclib.DebugLog;
import com.sojoline.basiclib.event.UnauthorizedEvent;
import com.sojoline.basiclib.rx.RxBus;
import com.sojoline.model.api.CommonService;
import com.sojoline.model.api.MonitorService;
import com.sojoline.model.api.SolarService;
import com.sojoline.model.response.SolarElectricResponse;
import com.sojoline.model.storage.AppInfoPreferences;
import com.sojoline.model.typeadapter.ElectricTypeAdapter;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/19
 *     desc   : 网络配置
 *     version: 1.0
 * </pre>
 */
@Module
public class ApiModule {
	public static final String UPDATE_URL = "http://117.78.40.137/cs/v1/app/version/update";
	//本地服务器地址
//	private static final String API_SERVER = "192.168.58.95";
//	private static final String API_TYPE = "cs";
//	private static final String API_PORT = "8080";

	//云服务器地址
	private static final String API_SERVER = "117.78.40.137";
	private static final String API_TYPE = "cs-pv";
	private static final String API_PORT = "";

	private static final int HTTP_TIME_OUT = 15;

	@Named("forApp")
	@Singleton
	@Provides
	public OkHttpClient provideOkHttpClick(){
		Interceptor headerInterceptor = new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request()
						.newBuilder()
						.addHeader("StationStatus-Type", "application/json")
						.addHeader("Authorization",
								AppInfoPreferences.get().getToken() == null ? "" : AppInfoPreferences.get().getToken())
						.build();

				Response response = chain.proceed(request);
				if (response.code() == 401){
					RxBus.getInstance().postEvent(new UnauthorizedEvent());
					return new Response.Builder()
							.request(response.request())
							.protocol(response.protocol())
							.code(response.code())
							.body(null)
							.build();
				}else {
					return response;
				}
				//处理：当"content":{}时改变成"content":[]
//				ResponseBody rb = response.body();
//				String responseBody = rb.string();
//
//				Gson gson = new Gson();
//				CommonResponse commonResponse = gson.fromJson(responseBody, CommonResponse.class);
//
//				ResponseBody body;
//				if (commonResponse.isSuccess()) {
//					body = ResponseBody.create(rb.contentType(), responseBody);
//				} else {
//					commonResponse.content = null;
//					body = ResponseBody.create(rb.contentType(), gson.toJson(commonResponse));
//				}
//
//				return new Response.Builder()
//						.request(response.request())
//						.protocol(response.protocol())
//						.code(response.code())
//						.body(body)
//						.build();

			}
		};

		//打印日志
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
			@Override
			public void log(String message) {
				DebugLog.log(message);
			}
		});
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		return new OkHttpClient.Builder()
				.connectTimeout(HTTP_TIME_OUT, TimeUnit.SECONDS)
				.writeTimeout(HTTP_TIME_OUT, TimeUnit.SECONDS)
				.readTimeout(HTTP_TIME_OUT, TimeUnit.SECONDS)
				.addInterceptor(headerInterceptor)
				.addInterceptor(loggingInterceptor)
				.build();
	}

	@Singleton
	@Provides
	public SolarService provideSolarService(@Named("forApp") OkHttpClient okHttpClient){
		Retrofit retrofit = getRetrofit(okHttpClient);
		return retrofit.create(SolarService.class);
	}

	@Singleton
	@Provides
	public MonitorService provideMonitorService(@Named("forApp") OkHttpClient okHttpClient){
		Retrofit retrofit = getRetrofit(okHttpClient);
		return retrofit.create(MonitorService.class);
	}

	@Singleton
	@Provides
	public CommonService provideCommonService(@Named("forApp") OkHttpClient okHttpClient){
		Retrofit retrofit = getRetrofit(okHttpClient);
		return retrofit.create(CommonService.class);
	}

	private Retrofit getRetrofit(OkHttpClient okHttpClient){
		//定义json解析
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(SolarElectricResponse.class, new ElectricTypeAdapter())
				.create();

		String url;
		if (TextUtils.isEmpty(API_PORT)){
			url = String.format(Locale.getDefault(), "http://%s/%s/", API_SERVER, API_TYPE);
		}else {
			url = String.format(Locale.getDefault(), "http://%s:%s/%s/", API_SERVER, API_PORT, API_TYPE);
		}

		return new Retrofit.Builder()
				.baseUrl(url)
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
	}

}
