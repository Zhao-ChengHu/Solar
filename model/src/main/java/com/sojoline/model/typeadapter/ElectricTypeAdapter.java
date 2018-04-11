package com.sojoline.model.typeadapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sojoline.model.db.DateElectric;
import com.sojoline.model.response.SolarElectricResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/02/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ElectricTypeAdapter implements JsonDeserializer<SolarElectricResponse> {
	private static final String TAG = ElectricTypeAdapter.class.getSimpleName();

	@Override
	public SolarElectricResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		SolarElectricResponse response = new SolarElectricResponse();
		JsonObject object = json.getAsJsonObject();
		response.setCode(object.get("code").getAsInt());
		response.setMsg(object.get("msg").getAsString());
		JsonObject content = object.getAsJsonObject("content");
		JsonArray array = content.getAsJsonArray("pvOperationStatic");
		List<DateElectric> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			DateElectric electric = new DateElectric();
			JsonObject obj = array.get(i).getAsJsonObject();
			if (obj.has("YearMonthElectric")){
				electric.setElectric(obj.get("YearMonthElectric").getAsFloat());
				electric.setNum(obj.get("months").getAsInt());
				list.add(electric);
			} else if (obj.has("MonthDayElectric")){
				electric.setElectric(obj.get("MonthDayElectric").getAsFloat());
				electric.setNum(obj.get("days").getAsInt());
				list.add(electric);
			} else if (obj.has("DayHourElectric")){
				electric.setElectric(obj.get("DayHourElectric").getAsFloat());
				electric.setNum(obj.get("hours").getAsInt());
				list.add(electric);
			}else if (obj.has("YearTotalElectric")){
				electric.setElectric(obj.get("YearTotalElectric").getAsFloat());
				electric.setNum(obj.get("years").getAsInt());
				list.add(electric);
			}
		}
		response.setList(list);
		return response;
	}
}
