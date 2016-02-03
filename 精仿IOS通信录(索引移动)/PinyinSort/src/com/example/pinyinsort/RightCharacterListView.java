package com.example.pinyinsort;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * ClassName:RightCharacterListView 右侧字母表，快速定位
 * 
 * @author sy
 * @version 1.0
 * @since v1.0
 * @Date 2012-10-18 上午10:49:52
 */
public class RightCharacterListView extends View {
	// private String[] b = null;
	//
	// public void setB(String[] b) {
	// this.b = b;
	// }

	OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z", "#" };

	// String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "S", "W", "X",
	// "Z" };
	int choose = -1;
	Paint paint = new Paint();
	boolean showBkg = false;

	public RightCharacterListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public RightCharacterListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RightCharacterListView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// if (showBkg) {
		// canvas.drawColor(Color.parseColor("#40000000"));
		// }
		// 整体的视图的高度
		int height = getHeight();
		// 整体的视图的宽度
		int width = getWidth();
		// 每一个字母区域所占的高度
		int singleHeight = height / b.length;
		for (int i = 0; i < b.length; i++) {
			// 字母字体白色
			paint.setColor(Color.GRAY);
			paint.setTextSize(18);
			// 字体
			// paint.setTypeface(Typeface.DEFAULT_BOLD);
			// 去除画笔锯齿
			paint.setAntiAlias(true);
			// 给index为choose的字母设置
			if (i == choose) {
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
			}
			// 得到字母距离listView两边的距离
			float xPos = width / 2 - paint.measureText(b[i]) / 2;
			// 得到每一个字母区域的最下方距离listView的顶端的距离
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(b[i], xPos, yPos, paint);
			paint.reset();
		}

	}

	/**
	 * 获取焦点分发焦点在OnTouch之前执行
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		// 得到所点击的字母区域在数组中的index
		final int c = (int) (y / getHeight() * b.length);

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			showBkg = true;
			// 如果当前点击的index不是“#”
			if (oldChoose != c && listener != null) {
				if (c >= 0 && c < b.length) { // 如果第一个字母是#，无效点击的话，条件变为c>0
					listener.onTouchingLetterChanged(b[c]);
					choose = c;
					invalidate();
				}
			}

			break;
		case MotionEvent.ACTION_MOVE:
			if (oldChoose != c && listener != null) {
				if (c >= 0 && c < b.length) { // 如果第一个字母是#，无效点击的话，条件变为c>0
					listener.onTouchingLetterChanged(b[c]);
					choose = c;
					invalidate();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			showBkg = false;
			choose = -1;
			invalidate();
			break;
		}
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

}