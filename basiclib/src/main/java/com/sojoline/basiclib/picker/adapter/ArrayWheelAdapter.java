package com.sojoline.basiclib.picker.adapter;

import android.content.Context;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter{
	private T[] items;

	/**
	 * Constructor
	 * @param context the current context
	 * @param items the items
	 */
	public ArrayWheelAdapter(Context context, T[] items) {
		super(context);
		//setEmptyItemResource(TEXT_VIEW_ITEM_RESOURCE);
		this.items = items;
	}

	public void setItems(T[] items){
		this.items = items;
	}

	@Override
	public CharSequence getItemText(int index) {
		if (index >= 0 && index < items.length) {
			T item = items[index];
			if (item instanceof CharSequence) {
				return (CharSequence) item;
			}
			return item.toString();
		}
		return null;
	}

	@Override
	public int getItemsCount() {
		return items.length;
	}
}
