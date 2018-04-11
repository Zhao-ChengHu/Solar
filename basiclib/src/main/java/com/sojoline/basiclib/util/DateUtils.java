package com.sojoline.basiclib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/31
 *     desc   : 日期工具
 *     version: 1.0
 * </pre>
 */

public class DateUtils {

	public static String getStringDay(){
		String str = "yyyy-MM-dd";
		return formatDate(str);
	}

	public static String getStringMonth(){
		String str = "yyyy-MM";
		return formatDate(str);
	}

	public static String formatDate(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 获取当前的天
	 *
	 * @return 天
	 */
	public static int getDay(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前的月
	 *
	 * @return 月
	 */
	public static int getMonth(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前的年
	 *
	 * @return 年
	 */
	public static int getYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取指定日期的下一月
	 *
	 * @param year 指定年
	 * @return year
	 */
	public static int nextYear(int year){
		return year + 1;
	}

	/**
	 * 获取指定日期的上一月
	 *
	 * @param year 指定年
	 * @return year
	 */
	public static int lastYear(int year){
		return  year - 1;
	}

	/**
	 * 获取指定日期的下一月
	 *
	 * @param year 指定年
	 * @param month 指定月
	 * @return 日期数组｛year,month｝
	 */
	public static int[] nextMonth(int year, int month){
		if (month == 12){
			month = 1;
			year += 1;
		}else {
			month += 1;
		}
		return new int[]{year, month};
	}

	/**
	 * 获取指定日期的上一月
	 *
	 * @param year 指定年
	 * @param month 指定月
	 * @return 日期数组｛year,month｝
	 */
	public static int[] lastMonth(int year, int month){
		if (month == 1){
			month = 12;
			year -= 1;
		}else {
			month -= 1;
		}
		return new int[]{year, month};
	}

	/**
	 * 获取指定日期的下一天
	 * @param year
	 * 			指定年
	 *
	 * @param month
	 * 			指定月
	 *
	 * @param day
	 * 			指定天
	 *
	 * @return 日期数组｛year,month,day｝
	 */
	public static int[] nextDay(int year, int month, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		int temp = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, temp + 1);
		return new int[]{calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE)};
	}

	/**
	 * 获取指定日期的上一天
	 *
	 * @param year
	 * 			指定年
	 *
	 * @param month
	 * 			指定月
	 *
	 * @param day
	 * 			指定天
	 *
	 * @return 日期数组｛year,month,day｝
	 */
	public static int[] lastDay(int year, int month, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		int temp = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, temp - 1);
		return new int[]{calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE)};
	}

	/**
	 * 获取某月的天数
	 *
	 * @param year
	 * 			指定年
	 *
	 * @param month
	 * 			指定月
	 *
	 * @return 天数
	 */
	public static int getDaysOfMonth(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.get(Calendar.DATE);

	}

	/**
	 * 把日期字符串转化为数组
	 *
	 * @param source
	 * 			日期字符串
	 * @param pattern
	 * 			日期格式
	 *
	 * @return int[]｛
	 * 			year, month, day, hour, minute, second｝
	 *
	 * @throws ParseException
	 * 			格式不匹配
	 */
	public static int[] dateToArray(String source, String pattern) throws ParseException {
		Calendar calendar = getCalendar(source, pattern);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		return new int[]{year, month, day, hour, minute, second};
	}

	private static Calendar getCalendar(String source, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(source);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 判断指定日期是否在系统日期之前
	 * 系统日期 2017-11-03
	 * 如果指定日期2017-10-02，返回 false
	 * 如果指定日期2017-12-04，返回 true
	 * @param year
	 * 			指定年
	 *
	 * @param month
	 * 			指定月
	 *
	 * @param day
	 * 			指定天
	 * @return true or false
	 */
	public static boolean isBefore(int year, int month, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return Calendar.getInstance().before(calendar);
	}

	/**
	 * 判断指定日期是否在系统日期之后
	 * 系统日期 2017-11-03
	 * 如果指定日期2017-10-02，返回 true
	 * 如果指定日期2017-12-04，返回 false
	 * @param year
	 * 			指定年
	 *
	 * @param month
	 * 			指定月
	 *
	 * @param day
	 * 			指定天
	 * @return true or false
	 */
	public static boolean isAfter(int year, int month, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return Calendar.getInstance().after(calendar);
	}

	/**
	 * 判断两个日期的前后关系
	 * source日期 2017-11-03
	 * 如果指定日期2017-10-02，返回 false
	 * 如果指定日期2017-12-04，返回 true
	 * @param source
	 * 			字符串日期（如：2017-01-01）
	 * @param year
	 * 			指定年
	 *
	 * @param month
	 * 			指定月
	 *
	 * @param day
	 * 			指定天
	 * @return true or false
	 */
	public static boolean isBefore(String source, int year, int month, int day) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		calendar.add(Calendar.DATE, -1);
		return getCalendar(source, "yyyy-MM-dd").before(calendar);
	}

	public static boolean isBefore(String source, int year, int month) throws ParseException {
		int[] data = dateToArray(source, "yyyy-MM-dd");
		if (year > data[0]){
			return true;
		}else if (year == data[0]){
			if (month > data[1]){
				return true;
			}
		}
		return false;
	}

	public static boolean isBefore(String source, int year) throws ParseException {
		int[] data = dateToArray(source, "yyyy-MM-dd");

		if (year > data[0]){
			return true;
		}
		return false;
	}

	/**
	 * 判断两个日期的前后关系
	 * source 2017-11-03
	 * 如果指定日期2017-10-02，返回 true
	 * 如果指定日期2017-12-04，返回 false
	 * @param source
	 * 			字符串日期（如：2017-01-01）
	 * @param year
	 * 			指定年
	 *
	 * @param month
	 * 			指定月
	 *
	 * @param day
	 * 			指定天
	 * @return true or false
	 */
	public static boolean isAfter(String source, int year, int month, int day) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return getCalendar(source, "yyyy-MM-dd").after(calendar);
	}
}
