package com.sojoline.model.storage;

import android.content.Context;

import com.sojoline.annotation.ISpfEncryption;
import com.sojoline.annotation.SpfParser;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class AptPreferencesManager {
	private static Context sContext;

	private static SpfParser sAptParser;

	private static ISpfEncryption spfEncryption;

	private static String sUserInfo;

	public static void init(Context context, SpfParser aptParser, ISpfEncryption spfEncryption) {
		sContext = context;
		sAptParser = aptParser;
		AptPreferencesManager.spfEncryption = spfEncryption;
	}

	public static Context getContext() {
		return sContext;
	}

	public static SpfParser getAptParser() {
		return sAptParser;
	}

	public static ISpfEncryption getSpfEncryption() {
		return spfEncryption;
	}

	public static void setUserInfo(String userInfo) {
		sUserInfo = userInfo;
	}

	public static String getUserInfo() {
		return sUserInfo;
	}
}
