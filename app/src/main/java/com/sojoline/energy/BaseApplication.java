package com.sojoline.energy;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.sojoline.annotation.SpfParser;
import com.sojoline.base.util.SpfEncryption;
import com.sojoline.base.util.Utils;
import com.sojoline.basiclib.DebugLog;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.dagger.ApiModule;
import com.sojoline.model.dagger.DaggerApiComponent;
import com.sojoline.model.db.gen.DaoMaster;
import com.sojoline.model.storage.AptPreferencesManager;
import com.sojoline.model.storage.DaoMasterHolder;
import com.sojoline.model.storage.DaoSessionHolder;
import com.squareup.leakcanary.LeakCanary;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BaseApplication extends Application{

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Utils.init(this);
		initSharedPreference();
		initDagger();
		initARouter();
		initGreenDao();
		initDebug(BuildConfig.DEBUG);
	}

	private void initDagger(){
		ApiComponentHolder.apiComponent = DaggerApiComponent.builder().apiModule(new ApiModule()).build();
	}

	private void initSharedPreference(){
		AptPreferencesManager.init(this, new SpfParser() {
			@Override
			public Object deserialize(Class clazz, String text) {
				Gson gson = new Gson();
				return gson.fromJson(text, clazz);
			}

			@Override
			public String serialize(Object object) {
				Gson gson = new Gson();
				return gson.toJson(object);
			}
		}, new SpfEncryption());
	}

	private void initARouter(){
		ARouter.init(this);
		ARouter.openDebug(); // 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
	}

	private void initDebug(boolean isDebug){
		DebugLog.debugable(isDebug);
		initLeakCanary();
	}

	private void initLeakCanary(){
		if (LeakCanary.isInAnalyzerProcess(this)){
			return;
		}
		LeakCanary.install(this);
	}

	private void initGreenDao(){
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"energy.db");
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster master = new DaoMaster(db);
		DaoMasterHolder.daoMaster = master;
		DaoSessionHolder.daoSession = master.newSession();
	}
}
