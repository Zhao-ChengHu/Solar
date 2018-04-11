package com.sojoline.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sojoline.base.R;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class LoadingDialog {
	public static Dialog createDialog(Context context){
		View view = View.inflate(context, R.layout.view_progress, null);
		ImageView iv = (ImageView) view.findViewById(R.id.iv_animation);
		AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
		animationDrawable.start();
		Dialog dialog = new Dialog(context, R.style.Loading_Dialog_Theme);
		dialog.setCancelable(false);
		dialog.addContentView(view, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
		));
		return dialog;
	}
}
