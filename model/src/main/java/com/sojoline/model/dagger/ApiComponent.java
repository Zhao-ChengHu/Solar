package com.sojoline.model.dagger;

import com.sojoline.model.api.CommonService;
import com.sojoline.model.api.MonitorService;
import com.sojoline.model.api.SolarService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Component(modules = {ApiModule.class})
@Singleton
public interface ApiComponent {
	MonitorService monitorService();
	SolarService solarService();
	CommonService commonService();
}
