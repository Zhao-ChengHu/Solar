package com.sojoline.basiclib.picker.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class AbstractWheelAdapter implements WheelViewAdapter {
	private List<DataSetObserver> observers;

	@Override
	public View getEmptyItem(View convertView, ViewGroup parent) {
		return null;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		if (observers == null) {
			observers = new LinkedList<>();
		}
		observers.add(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (observers != null) {
			observers.remove(observer);
		}
	}

	/**
	 * Notifies observers about data changing
	 */
	protected void notifyDataChangedEvent() {
		if (observers != null) {
			for (DataSetObserver observer : observers) {
				observer.onChanged();
			}
		}
	}

	/**
	 * Notifies observers about invalidating data
	 */
	protected void notifyDataInvalidatedEvent() {
		if (observers != null) {
			for (DataSetObserver observer : observers) {
				observer.onInvalidated();
			}
		}
	}
}
