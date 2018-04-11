package com.sojoline.presenter.solar.data;

import com.sojoline.basiclib.DebugLog;
import com.sojoline.basiclib.rx.SimpleSubscriber;
import com.sojoline.basiclib.rx.transform.SchedulersCompat;
import com.sojoline.model.dagger.ApiComponentHolder;
import com.sojoline.model.db.DateElectric;
import com.sojoline.model.db.gen.DBElectric;
import com.sojoline.model.db.gen.DBElectricDao;
import com.sojoline.model.db.gen.DateElectricDao;
import com.sojoline.model.request.SolarEnergyRequest;
import com.sojoline.model.response.DayElectricResponse;
import com.sojoline.model.response.MonthElectricResponse;
import com.sojoline.model.response.SolarElectricResponse;
import com.sojoline.model.response.TotalElectricResponse;
import com.sojoline.model.response.YearElectricResponse;
import com.sojoline.model.storage.DaoSessionHolder;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SolarEnergyPresenter extends SolarEnergyContract.Presenter {

	/**
	 * 先查询数据库，如果数据库没有，再进行网络查询
	 * @param request 请求参数
	 */
	@Override
	public void getSolarEnergy(final SolarEnergyRequest request) {
		getEnergyFromServer(request);
//		List<DBElectric> list1 = queryDBElectricList(request);
//		if (list1 != null && list1.size() > 0) {
//			List<DateElectric> list2 = queryDateElectricList(list1.get(0).getId() + "");
//			if (getView() != null){
//				getView().energySuccess(list2);
//			}
//		}else {
//			getEnergyFromServer(request);
//		}
	}

	@Deprecated
	@Override
	public void getDayEnergy(final SolarEnergyRequest request) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getDayEnergy(request)
				.take(1)
				.compose(SchedulersCompat.<DayElectricResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<DayElectricResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(DayElectricResponse response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							getView().energySuccess(response.content.getList());
						}else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						super.onStart();
						if (getView() != null){
							getView().showLoading(null);
						}
					}
				});
	}

	@Deprecated
	@Override
	public void getMonthEnergy(SolarEnergyRequest request) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getMonthEnergy(request)
				.take(1)
				.compose(SchedulersCompat.<MonthElectricResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<MonthElectricResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(MonthElectricResponse response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							getView().energySuccess(response.content.getList());
						}else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						super.onStart();
						if (getView() != null){
							getView().showLoading(null);
						}
					}
				});
	}

	@Deprecated
	@Override
	public void getYearEnergy(SolarEnergyRequest request) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getYearEnergy(request)
				.take(1)
				.compose(SchedulersCompat.<YearElectricResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<YearElectricResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(YearElectricResponse  response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							getView().energySuccess(response.content.getList());
						}else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						super.onStart();
						if (getView() != null){
							getView().showLoading(null);
						}
					}
				});
	}

	@Deprecated
	@Override
	public void getTotalEnergy(SolarEnergyRequest request) {
		ApiComponentHolder.apiComponent
				.solarService()
				.getTotalEnergy(request)
				.take(1)
				.compose(SchedulersCompat.<TotalElectricResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<TotalElectricResponse >() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(TotalElectricResponse  response) {
						super.onNext(response);
						getView().showNormal();
						if (response.isSuccess()){
							getView().energySuccess(response.content.getList());
						}else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						super.onStart();
						if (getView() != null){
							getView().showLoading(null);
						}
					}
				});
	}

	private void getEnergyFromServer(final SolarEnergyRequest request){
		ApiComponentHolder.apiComponent
				.solarService()
				.getSolarEnergy(request)
				.take(1)
				.compose(SchedulersCompat.<SolarElectricResponse>applyNewSchedulers())
				.subscribe(new SimpleSubscriber<SolarElectricResponse>() {
					@Override
					public void onError(Throwable e) {
						super.onError(e);
						e.printStackTrace();
						if (getView() != null){
							getView().showNormal();
							getView().requestFailed(null);
						}
					}

					@Override
					public void onNext(SolarElectricResponse response) {
						getView().showNormal();
						if (response.isSuccess()){
							getView().energySuccess(response.getList());
//							saveData(request, response.getList());
						}else {
							getView().requestFailed(response.getMsg());
						}
					}

					@Override
					public void onStart() {
						if (getView() != null){
							getView().showLoading(null);
						}
					}
				});
	}
	/**
	 * 保存发电量数据到数据库
	 * @param request
	 * @param list
	 */
	public void saveData(SolarEnergyRequest request, List<DateElectric> list){
		DebugLog.log("数据库保存数据");
		DBElectric dbElectric = new DBElectric();
		dbElectric.setStationId(request.getDPStationID());
		dbElectric.setDate(request.getQueryTime());
		dbElectric.setType(request.getQueryType());
		DaoSessionHolder.daoSession.getDBElectricDao().insert(dbElectric);
		for (DateElectric electric : list){
			electric.setElectricId(dbElectric.getId());
			DaoSessionHolder.daoSession.getDateElectricDao().insert(electric);
		}
	}

	/**
	 * 查询数据
	 * @param request 发电量请求参数
	 * @return
	 */
	public List<DBElectric> queryDBElectricList(SolarEnergyRequest request){
		DebugLog.log("数据库查询数据");
		WhereCondition stationId = DBElectricDao.Properties.StationId.eq(request.getDPStationID());
		WhereCondition date = DBElectricDao.Properties.Date.eq(request.getQueryTime());
		WhereCondition type = DBElectricDao.Properties.Type.eq(request.getQueryType());
		List<DBElectric> list = DaoSessionHolder.daoSession.getDBElectricDao()
				.queryBuilder()
				.where(stationId, date, type)
				.list();
		return list;
	}

	/**
	 * 查询数据
	 * @param electricId
	 * @return
	 */
	public List<DateElectric> queryDateElectricList(String electricId){
		DebugLog.log(electricId);
		List<DateElectric> list = DaoSessionHolder.daoSession.getDateElectricDao()
				.queryBuilder()
				.where(DateElectricDao.Properties.ElectricId.eq(electricId))
				.list();
		return list;
	}
}
