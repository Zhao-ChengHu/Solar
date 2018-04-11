package com.sojoline.base.widget;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class NameLengthFilter implements InputFilter {
	//最大英文/数字长度 一个汉字算两个字母
	int maxLength;
	String regEx = "[\\u4e00-\\u9fa5]";

	public NameLengthFilter(int maxLength) {
		super();
		this.maxLength = maxLength;
	}

	@Override
	public CharSequence filter(CharSequence source, int start, int end,
							   Spanned dest, int dstart, int dend) {
		int destCount = dest.toString().length()
				+ getChineseCount(dest.toString());
		int sourceCount = source.toString().length()
				+ getChineseCount(source.toString());
		if (destCount + sourceCount > maxLength) {
			int surplusCount = maxLength - destCount;
			String result = "";
			int index = 0;
			while (surplusCount > 0) {
				char c = source.charAt(index);
				if (isChinese(c + "")) {
					if (sourceCount >= 2) {
						result += c;
					}
					surplusCount = surplusCount - 2;
				} else {
					result += c;
					surplusCount = surplusCount - 1;
				}
				index++;
			}
			return result;
		} else {
			return source;
		}
	}

	/**
	 * 获取汉字的数量
	 * @param str
	 * @return
	 */
	private int getChineseCount(String str) {
		int count = 0;
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				count = count + 1;
			}
		}
		return count;
	}

	/**
	 * 判断是否为汉字
	 * @param source
	 * @return
	 */
	private boolean isChinese(String source) {
		return Pattern.matches(regEx, source);
	}
}
