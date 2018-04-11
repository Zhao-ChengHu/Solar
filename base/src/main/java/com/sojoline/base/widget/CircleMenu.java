package com.sojoline.base.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/01/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CircleMenu extends View{
	private Context context;
	private Paint paint;
	private Paint textPaint;
	private Paint backgroundPaint;
	private float radius;
	/**
	 * 中心坐标
	 */
	private int centerX;
	private int centerY;

	/**
	 * 圆到中心的长度
	 */
	private float length;

	/**
	 * 变化的长度
	 */
	private float changeLength;

	/**
	 * 变化的角度
	 */
	private float changeAngle;

	/**
	 * 上圆范围
	 */
	private RectF topRect;
	/**
	 * 左圆范围
	 */
	private RectF leftRect;
	/**
	 * 右圆范围
	 */
	private RectF rightRect;

	private List<String> list;
	private OnCircleClickListener listener;
	private boolean first;

	/**
	 * 是否能点击
	 */
	private boolean click;

	public CircleMenu(Context context) {
		this(context, null);
	}

	public CircleMenu(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		init();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		centerX = getScreenWidth() / 2;
		centerY = getScreenHeight() / 2;
		length = centerX * 0.6f;
		radius = centerX * 0.15f;
		topRect = new RectF(centerX - radius, centerY - length - radius,
				centerX + radius, centerY -length + radius);
		float rightX = (float) (length * Math.cos(Math.PI * 30 / 180) + centerX);
		float leftX = (float) (- length * Math.cos(Math.PI * 30 / 180) + centerX);
		float y = (float) (length * Math.sin(Math.PI * 30 / 180) + centerY);
		leftRect = new RectF(leftX - radius, y - rightX, leftX + radius, y + radius);
		rightRect = new RectF(rightX - radius, y - rightX, rightX + radius, y + radius);
	}

	private void init(){
		first = true;
		click = false;

		list = new ArrayList<>();
		list.add(" ");
		list.add(" ");
		list.add(" ");
		changeLength = 0;
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		paint.setStyle(Paint.Style.STROKE);

		textPaint = new Paint();
		textPaint.setTextAlign(Paint.Align.CENTER);
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(dp2px(18));

		backgroundPaint = new Paint();
		backgroundPaint.setAntiAlias(true);
		backgroundPaint.setStyle(Paint.Style.FILL);
		backgroundPaint.setColor(Color.WHITE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (changeLength == 0){
			canvas.drawCircle(centerX, centerY, radius, paint);
		}else {
			drawView(canvas);
		}
		if (first){
			first = false;
			//距离变化
			ValueAnimator animator1 = ValueAnimator.ofFloat(0, length);
			animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					changeLength = (float) animation.getAnimatedValue();
					invalidate();
				}
			});
			//角度变化
			ValueAnimator animator2 = ValueAnimator.ofFloat(0, 360);
			animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					changeAngle = (float) animation.getAnimatedValue() + 30;
				}
			});

			AnimatorSet set = new AnimatorSet();
			set.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					click = true;
				}
			});
			set.playTogether(animator1, animator2);
			set.setDuration(2000);
			set.start();
		}

	}

//	private void drawView(Canvas canvas){
//		float rightX = (float) (changeLength * Math.cos(Math.PI * 30 / 180) + centerX);
//		float leftX = (float) (- changeLength * Math.cos(Math.PI * 30 / 180) + centerX);
//		float y = (float) (changeLength * Math.sin(Math.PI * 30 / 180) + centerY);
//		//上面的圆
//		canvas.drawCircle(centerX, centerY - changeLength, radius, paint);
//		canvas.drawText(list.get(0), centerX, centerY - changeLength + getTextPaintOffset(textPaint), textPaint);
//		//左面的圆
//		canvas.drawCircle(leftX, y, radius, paint);
//		canvas.drawText(list.get(1), leftX, y + getTextPaintOffset(textPaint), textPaint);
//		//右面的圆
//		canvas.drawCircle(rightX, y, radius, paint);
//		canvas.drawText(list.get(2), rightX, y + getTextPaintOffset(textPaint), textPaint);
//	}

	/**
	 * 画圆和文字
	 * 一个是三个圆，每个圆相差120
	 * @param canvas
	 */
	private void drawView(Canvas canvas){
		float rightX = (float) (changeLength * Math.cos(Math.PI * changeAngle / 180) + centerX);
		float rightY = (float) (changeLength * Math.sin(Math.PI * changeAngle / 180) + centerY);
		float a = changeAngle + 120;
		float leftX = (float) (changeLength * Math.cos(Math.PI * a / 180) + centerX);
		float leftY = (float) (changeLength * Math.sin(Math.PI * a / 180) + centerY);

		float b = changeAngle + 240;
		float topX = (float) (changeLength * Math.cos(Math.PI * b / 180) + centerX);
		float topY = (float) (changeLength * Math.sin(Math.PI * b / 180) + centerY);
		//上面的圆
		canvas.drawCircle(topX, topY, radius, paint);
		canvas.drawCircle(topX, topY, radius - 5, backgroundPaint);
		canvas.drawText(list.get(0), topX, topY + getTextPaintOffset(textPaint), textPaint);
		//左面的圆
		canvas.drawCircle(leftX, leftY, radius, paint);
		canvas.drawCircle(leftX, leftY, radius - 5, backgroundPaint);
		canvas.drawText(list.get(1), leftX, leftY + getTextPaintOffset(textPaint), textPaint);
		//右面的圆
		canvas.drawCircle(rightX, rightY, radius, paint);
		canvas.drawCircle(rightX, rightY, radius - 5, backgroundPaint);
		canvas.drawText(list.get(2), rightX, rightY + getTextPaintOffset(textPaint), textPaint);
	}

	/**
	 * Text的baseline
	 * @param paint
	 * @return
	 */
	private int getTextPaintOffset(Paint paint){
		Paint.FontMetricsInt f = paint.getFontMetricsInt();
		return -f.descent + (f.bottom - f.top) / 2;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				float x = event.getX();
				float y = event.getY();
				if (click){
					if (topRect.contains(x, y)){
						if (listener != null){
							listener.onClick(list.get(0));
						}
					}else if (leftRect.contains(x, y)){
						if (listener != null){
							listener.onClick(list.get(1));
						}
					}else if (rightRect.contains(x, y)){
						if (listener != null){
							listener.onClick(list.get(2));
						}
					}else {
						Log.i("TAG", "onTouchEvent: 点击了其他");
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				Log.i("TAG", "onTouchEvent: up");
				break;
			default:
				break;
		}
		return true;
	}

	/**
	 * 设置显示的文字
	 * @param list 字符串数组
	 */
	public void setContent(List<String> list){
		if (list != null || list.size() > 2){
			this.list = list;
		}
	}

	public void setListener(OnCircleClickListener listener) {
		this.listener = listener;
	}

	/**
	 * 获取屏幕的宽度（单位：px）
	 *
	 * @return 屏幕宽px
	 */
	private int getScreenWidth() {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
		windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕的高度（单位：px）
	 *
	 * @return 屏幕高px
	 */
	private int getScreenHeight() {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
		windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
		return dm.heightPixels;
	}

	/**
	 * dp转px
	 *
	 * @param dpValue dp值
	 * @return px值
	 */
	private int dp2px(float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public interface OnCircleClickListener{
		void onClick(String content);
	}
}
