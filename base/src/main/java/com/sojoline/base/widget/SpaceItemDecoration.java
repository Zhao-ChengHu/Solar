package com.sojoline.base.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sojoline.base.util.ScreenUtils;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
	private int mSpace;

	public SpaceItemDecoration(int dp) {
		this.mSpace = ScreenUtils.dp2px(dp);
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.bottom = mSpace;
		if (parent.getChildAdapterPosition(view) == 0) {
			outRect.top = mSpace;
		}
	}
}
