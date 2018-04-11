package com.sojoline.base.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.jaiky.imagespickers.ImageLoader;
import com.sojoline.base.R;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class GlideLoader implements ImageLoader {
	private static final long serialVersionUID = 1L;

	private static final GlideLoader INSTANCE = new GlideLoader();

	public static GlideLoader getInstance() {
		return INSTANCE;
	}

	private GlideLoader() {
	}

	@Override
	public void displayImage(Context context, String path, ImageView imageView) {
		Glide.with(context)
				.load(path)
				.into(imageView);
	}

	public void displayCircleImage(Context context, String path, ImageView imageView){
		RequestOptions options = new RequestOptions()
				.placeholder(R.mipmap.ic_photo_default)
				.error(R.mipmap.ic_photo_default)
				.centerCrop();
		Glide.with(context)
				.load(path)
				.apply(options)
				.apply(RequestOptions.bitmapTransform(new CircleCrop()))
				.into(imageView);
	}
}
