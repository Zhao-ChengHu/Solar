package com.sojoline.solar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.sojoline.solar.R;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class PowerCapacityView extends View {
	private  int capacity;
	private Paint mCirclePlant;
	private Paint mArcPaint;
	private Paint mLinePaint;
	private Paint mTextPaint;

	private int mSize;
	private int mCircleColor;
	private int mArcColor;
	private int mTextColor;
	private float smallSize;
	private float bigSize;
	private float textSpace;

	private float x;
	private float y;

	private float mRadius;

	private float mLineLength;
	private float mLineCount;
	private int mLineColor;
	private float mLineOffset;

	/**
	 * 圆弧开始角度
	 */
	private float mStartAngle = 120;
	private float mSweepAngle = 300;

	private float mArcAngle;

	public PowerCapacityView(Context context) {
		this(context,null);
	}

	public PowerCapacityView(Context context, @Nullable AttributeSet attrs) {
		this(context,attrs,0);
	}

	public PowerCapacityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray ta = context.obtainStyledAttributes(attrs,
				com.sojoline.base.R.styleable.PowerCapacityView, defStyleAttr, 0);
		mCircleColor = ta.getColor(R.styleable.PowerCapacityView_circleColor, Color.GREEN);
		mArcColor = ta.getColor(R.styleable.PowerCapacityView_arcColor, Color.YELLOW);
		mTextColor = ta.getColor(R.styleable.PowerCapacityView_textColor, Color.WHITE);
		mLineColor = ta.getColor(R.styleable.PowerCapacityView_lineColor, Color.WHITE);
		smallSize = ta.getDimension(R.styleable.PowerCapacityView_smallTextSize, 0);
		bigSize = ta.getDimension(R.styleable.PowerCapacityView_bigTextSize, 0);
		textSpace = ta.getDimension(R.styleable.PowerCapacityView_textSpace, 35);
		mLineCount = ta.getInteger(R.styleable.PowerCapacityView_lineCount, 50);
		mLineLength = ta.getDimension(R.styleable.PowerCapacityView_lineLength, 0);
		mLineOffset = ta.getDimension(R.styleable.PowerCapacityView_lineOffset, 0);
		ta.recycle();
		init();
	}

	public void setCapacity(int capacity){
		this.capacity = capacity;
		if (this.capacity < 0){
			this.capacity = 0;
		}else if (this.capacity > 100){
			this.capacity = 100;
		}

//		mArcAngle = this.capacity / 100f * mSweepAngle;
		invalidate();
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int defaultSize = 300;
		int width = measureDimension(defaultSize,widthMeasureSpec);
		int height = measureDimension(defaultSize,heightMeasureSpec);
		int len = Math.min(width,height);
		setMeasuredDimension(len,len);
	}

	private int measureDimension(int defaultSize,int measureSpec){
		int result;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);
		if (mode == MeasureSpec.EXACTLY){
			result = size;
		}else {
			result = defaultSize;
			if (mode == MeasureSpec.AT_MOST){
				result = Math.min(result,size);
			}
		}
		return result;
	}

	private void init(){
		initPaint();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		int height = getHeight() - getPaddingTop() - getPaddingBottom();
		int width = getWidth() - getPaddingRight() - getPaddingLeft();
		mSize = Math.min( width, height) - 10;
		if (mLineLength == 0){
			mLineLength = mSize / 10;
		}

		if (mLineOffset == 0){
			mLineOffset = mLineLength / 2;
		}

		if (bigSize == 0){
			bigSize = (int) (mLineLength * 1.5);
		}

		if (smallSize == 0){
			smallSize = bigSize / 2;
		}

	}

	private void initPaint(){
		//圆弧画笔
		mArcPaint = new Paint();
		mArcPaint.setColor(mArcColor);
		mArcPaint.setStrokeWidth(10);
		mArcPaint.setStyle(Paint.Style.STROKE);
		mArcPaint.setAntiAlias(true);

		//线条画笔
		mLinePaint = new Paint();
		mLinePaint.setColor(mLineColor);
		mLinePaint.setStrokeWidth(2);
		mLinePaint.setStyle(Paint.Style.STROKE);
		mLinePaint.setAntiAlias(true);

		//文字画笔
		mTextPaint = new TextPaint();
		mTextPaint.setColor(mTextColor);
		mTextPaint.setStrokeWidth(4);
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
		mTextPaint.setTextSize(144);

		//圆圈画笔
		mCirclePlant = new Paint();
		mCirclePlant.setColor(mCircleColor);
		mCirclePlant.setStrokeWidth(2);
		mCirclePlant.setStyle(Paint.Style.FILL);
		mCirclePlant.setAntiAlias(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mRadius = mSize / 2;
		x = mSize / 2;
		y = mSize / 2;

		drawCircle(canvas);
		canvas.translate(getPaddingLeft() + x, getPaddingTop() + y);
//		drawArcView(canvas);
		drawLine(canvas);
		drawTextView(canvas);
	}

	/**
	 * 画大圆
	 * @param canvas
	 */
	private void drawCircle(Canvas canvas){
		canvas.drawCircle(getPaddingLeft() + x, getPaddingTop() + y, mRadius, mCirclePlant);
	}

	/**
	 * 画刻度线
	 * @param canvas
	 */
	private void drawLine(Canvas canvas){
		//画布操作时需要，保存当前状态
		canvas.save();
		float angle = mSweepAngle / mLineCount;
		canvas.rotate(-270 + mStartAngle);
		float count = capacity / 100f * mLineCount;
		float mStart = -mRadius + mLineOffset;
		for (int i = 0; i <= mLineCount; i++) {
			if (i < count){
				mLinePaint.setColor(mArcColor);
			}else {
				mLinePaint.setColor(mLineColor);
			}
			canvas.drawLine(0, mStart, 0, mStart + mLineLength, mLinePaint);
			//逆时针旋转画布
			canvas.rotate(angle);
		}
		//取出先前的状态
		canvas.restore();
	}

	/**
	 * 画圆弧
	 * @param canvas
	 */
	private void drawArcView(Canvas canvas){
		float mArcRadius = mRadius - mLineOffset - mLineLength / 2 + 2;
		mArcPaint.setStrokeWidth(mLineLength);
		//圆弧的外轮廓
		RectF rectF = new RectF(-mArcRadius, -mArcRadius, mArcRadius, mArcRadius);
		canvas.drawArc(rectF, mStartAngle, mArcAngle, false, mArcPaint);
	}

	/**
	 * 画文字
	 * @param canvas
	 */
	private void drawTextView(Canvas canvas){
		mTextPaint.setTextSize(bigSize);
		canvas.drawText(capacity + "%", 0, getTextPaintOffset(mTextPaint), mTextPaint);

		mTextPaint.setTextSize(smallSize);
		canvas.drawText("发电能力", 0, textSpace + getTextPaintOffset(mTextPaint), mTextPaint);
	}

	/**
	 * 文字的基准线 baseline
	 * @param paint
	 * @return
	 */
	private int getTextPaintOffset(Paint paint){
		Paint.FontMetricsInt f = paint.getFontMetricsInt();
		return -f.descent + (f.bottom - f.top) / 2;
	}

}
