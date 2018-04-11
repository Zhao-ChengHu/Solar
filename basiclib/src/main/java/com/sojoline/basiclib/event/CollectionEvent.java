package com.sojoline.basiclib.event;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CollectionEvent {
	private boolean isFavor;
	private int position;

	public CollectionEvent(boolean isFavor, int position) {
		this.isFavor = isFavor;
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isFavor() {
		return isFavor;
	}

	public void setFavor(boolean favor) {
		isFavor = favor;
	}
}
