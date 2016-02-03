package com.letter.filter.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.letter.filter.R;

/**
 * 
 * ============================================================
 * 
 * project name : 字母索引查找列表
 * 
 * copyright ZENG HUI (c) 2015
 * 
 * author : HUI
 * 
 * QQ : 240336124
 * 
 * version : 1.0
 * 
 * date created : On July, 2015
 * 
 * description :
 * 
 * revision history :
 * 
 * ============================================================
 * 
 */
public class LetterSideBar extends View {
	// 触摸事件
	private OnTouchingLetterListener mTouchingLetterListener;
	// 26个字母
	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	private String mCureentChooseStr;

	// 默认，和选中的画笔
	private Paint mDefaultPaint, mChoosePaint;
	// 宽度和高度
	private int mViewWidth = 0, mViewHeight = 0;

	// 当前手指是否正在触摸
	private boolean mCurrentIsTouch = false;

	public LetterSideBar(Context context) {
		this(context, null);
	}

	public LetterSideBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LetterSideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaint();
	}

	/**
	 * 初始化画笔(这些参数都可以放到自定义属性中)
	 */
	private void initPaint() {
		mDefaultPaint = new Paint();
		mDefaultPaint.setColor(Color.rgb(33, 65, 98));
		mDefaultPaint.setTypeface(Typeface.DEFAULT_BOLD);
		mDefaultPaint.setAntiAlias(true);
		mDefaultPaint.setTextSize(24);

		mChoosePaint = new Paint();
		mChoosePaint.setColor(Color.parseColor("#3399ff"));
		mChoosePaint.setTypeface(Typeface.DEFAULT_BOLD);
		mChoosePaint.setAntiAlias(true);
		mChoosePaint.setTextSize(24);
		mChoosePaint.setFakeBoldText(true);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
		if (mViewHeight == 0) {
			// 不等于0时不管，防止键盘弹出时改变原来的高度（键盘弹出时会调用onMeasure方法）
			mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
		}
		// 拿到宽度的测量模式
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		
		if (widthMode == MeasureSpec.AT_MOST
				|| widthMode == MeasureSpec.UNSPECIFIED) {
			// 如果是wrap_content 和 match_parent,计算宽度
			mViewWidth = (int) mDefaultPaint.measureText(b[1])
					+ getPaddingLeft() + getPaddingRight();
		}
		setMeasuredDimension(mViewWidth, mViewHeight);
	}

	/**
	 * 重新绘制当前选中状态
	 */
	public void drawCureentLetter(String letter) {
		if (!letter.equals(mCureentChooseStr)) {
			// 重新绘制
			this.mCureentChooseStr = letter;
			invalidate();
		}
	}

	/**
	 * 重写这个方法
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int singleHeight = mViewHeight / b.length;// 获取每一个字母的高度
		
		for (int i = 0; i < b.length; i++) {
			float measureTextWidth =  mDefaultPaint.measureText(b[i]);
			// x坐标等于中间-字符串宽度的一半.
			float xPos = mViewWidth / 2 -  measureTextWidth/ 2;
			float yPos = singleHeight * i + singleHeight- measureTextWidth/ 2;

			// 画默认状态
			canvas.drawText(b[i], xPos, yPos, mDefaultPaint);

			// 选中的状态
			if (b[i].equals(mCureentChooseStr)) {
				canvas.drawText(mCureentChooseStr, xPos, yPos, mChoosePaint);
			}
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();// 手指y坐标
		final String oldChooseStr = mCureentChooseStr;
		// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
		final int touchPosition = (int) (y / getHeight() * b.length);
		if (touchPosition < 0 || touchPosition >= b.length) {
			mCurrentIsTouch = false;
			touchLetterListener();
			setBackgroundColor(Color.TRANSPARENT);
			return true;
		}
		mCureentChooseStr = b[touchPosition];
		switch (action) {
		case MotionEvent.ACTION_UP:
			mCurrentIsTouch = false;
			// 移开设置背景透明
			setBackgroundColor(Color.TRANSPARENT);
			invalidate();
			touchLetterListener();
			break;
		default:
			mCurrentIsTouch = true;
			if (!mCureentChooseStr.equals(oldChooseStr)) {
				// 当手指在上面移动的时候设置我们自己的背景（可以在布局layout.xml中设置）
				setBackgroundResource(R.drawable.sidebar_background);
				// 重新绘制,响应监听事件
				invalidate();
				touchLetterListener();
			}
			break;
		}
		return true;
	}

	/**
	 * 回调触摸监听
	 */
	private void touchLetterListener() {
		if (mTouchingLetterListener != null) {
			mTouchingLetterListener.onTouchingLetterChanged(mCureentChooseStr,
					mCurrentIsTouch);
		}
	}

	/**
	 * 向外公开的设置监听方法
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterListener onTouchingLetterChangedListener) {
		this.mTouchingLetterListener = onTouchingLetterChangedListener;
	}

	/**
	 * 接口
	 */
	public interface OnTouchingLetterListener {
		// 当前选中的字母，手指是否触摸
		public void onTouchingLetterChanged(String letter, boolean isOnTouch);
	}

	/**
	 * 得到当前手指是否正在触摸
	 */
	public boolean isCurrentIsTouch() {
		return mCurrentIsTouch;
	}
}