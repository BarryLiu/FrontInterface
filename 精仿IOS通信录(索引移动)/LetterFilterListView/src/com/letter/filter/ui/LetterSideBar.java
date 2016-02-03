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
 * project name : ��ĸ���������б�
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
	// �����¼�
	private OnTouchingLetterListener mTouchingLetterListener;
	// 26����ĸ
	public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z", "#" };
	private String mCureentChooseStr;

	// Ĭ�ϣ���ѡ�еĻ���
	private Paint mDefaultPaint, mChoosePaint;
	// ��Ⱥ͸߶�
	private int mViewWidth = 0, mViewHeight = 0;

	// ��ǰ��ָ�Ƿ����ڴ���
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
	 * ��ʼ������(��Щ���������Էŵ��Զ���������)
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
			// ������0ʱ���ܣ���ֹ���̵���ʱ�ı�ԭ���ĸ߶ȣ����̵���ʱ�����onMeasure������
			mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
		}
		// �õ���ȵĲ���ģʽ
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		
		if (widthMode == MeasureSpec.AT_MOST
				|| widthMode == MeasureSpec.UNSPECIFIED) {
			// �����wrap_content �� match_parent,������
			mViewWidth = (int) mDefaultPaint.measureText(b[1])
					+ getPaddingLeft() + getPaddingRight();
		}
		setMeasuredDimension(mViewWidth, mViewHeight);
	}

	/**
	 * ���»��Ƶ�ǰѡ��״̬
	 */
	public void drawCureentLetter(String letter) {
		if (!letter.equals(mCureentChooseStr)) {
			// ���»���
			this.mCureentChooseStr = letter;
			invalidate();
		}
	}

	/**
	 * ��д�������
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int singleHeight = mViewHeight / b.length;// ��ȡÿһ����ĸ�ĸ߶�
		
		for (int i = 0; i < b.length; i++) {
			float measureTextWidth =  mDefaultPaint.measureText(b[i]);
			// x��������м�-�ַ�����ȵ�һ��.
			float xPos = mViewWidth / 2 -  measureTextWidth/ 2;
			float yPos = singleHeight * i + singleHeight- measureTextWidth/ 2;

			// ��Ĭ��״̬
			canvas.drawText(b[i], xPos, yPos, mDefaultPaint);

			// ѡ�е�״̬
			if (b[i].equals(mCureentChooseStr)) {
				canvas.drawText(mCureentChooseStr, xPos, yPos, mChoosePaint);
			}
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();// ��ָy����
		final String oldChooseStr = mCureentChooseStr;
		// ���y������ռ�ܸ߶ȵı���*b����ĳ��Ⱦ͵��ڵ��b�еĸ���.
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
			// �ƿ����ñ���͸��
			setBackgroundColor(Color.TRANSPARENT);
			invalidate();
			touchLetterListener();
			break;
		default:
			mCurrentIsTouch = true;
			if (!mCureentChooseStr.equals(oldChooseStr)) {
				// ����ָ�������ƶ���ʱ�����������Լ��ı����������ڲ���layout.xml�����ã�
				setBackgroundResource(R.drawable.sidebar_background);
				// ���»���,��Ӧ�����¼�
				invalidate();
				touchLetterListener();
			}
			break;
		}
		return true;
	}

	/**
	 * �ص���������
	 */
	private void touchLetterListener() {
		if (mTouchingLetterListener != null) {
			mTouchingLetterListener.onTouchingLetterChanged(mCureentChooseStr,
					mCurrentIsTouch);
		}
	}

	/**
	 * ���⹫�������ü�������
	 */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterListener onTouchingLetterChangedListener) {
		this.mTouchingLetterListener = onTouchingLetterChangedListener;
	}

	/**
	 * �ӿ�
	 */
	public interface OnTouchingLetterListener {
		// ��ǰѡ�е���ĸ����ָ�Ƿ���
		public void onTouchingLetterChanged(String letter, boolean isOnTouch);
	}

	/**
	 * �õ���ǰ��ָ�Ƿ����ڴ���
	 */
	public boolean isCurrentIsTouch() {
		return mCurrentIsTouch;
	}
}