package com.letterlistdamo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 靠右的字母
 */
public class LetterView extends View {
    /**
     * 显示的背景颜色
     */
    public static final int COLOR_BG = 0x17000000;
    /**
     * 没有背景颜色
     */
    public static final int COLOR_NO_BG = 0x07000000;
    /**
     * 点击状态下字母的颜色
     */
    public static final int TEXT_COLOR_NORMAL = 0xff545454;
    /**
     * 选中的字母颜色
     */
    public static final int TEXT_COLOR_SELECTED = 0xffff5e00;
    /**
     * 没有点击状态下的字母颜色
     */
    public static final int TEXT_COLOR_UNTOUCH = 0xffa3a3a3;
    public static final String letters = "☆ABCDEFGHIJKLMNOPQRSTUVWXYZ#";
    private int width;
    private int height;
    //每个字母的高度
    private int abcHeight;
    private Paint paint;
    private int selectedIndex = 1;//被选中字母的下标

    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        //加粗
        paint.setFakeBoldText(true);
        paint.setTextSize(Float.parseFloat(getResources().getString(R.string.text_size)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isTouch) {
            setBackgroundColor(COLOR_BG);
        } else {
            setBackgroundColor(COLOR_NO_BG);
        }
        if (width == 0 || height == 0) {
            width = getWidth();
            height = getHeight();
            abcHeight = height / letters.length();
        }
        for (int i = 0, length = letters.length(); i < length; i++) {
            if (selectedIndex == i)//设置被选中的字母颜色
            {
                paint.setColor(TEXT_COLOR_SELECTED);
            } else {
                if (isTouch)//设置点击状态下的所有的字母颜色
                {
                    paint.setColor(TEXT_COLOR_NORMAL);
                } else//设置非点击状态下的所有的字母颜色
                {
                    paint.setColor(TEXT_COLOR_UNTOUCH);
                }
            }
            //计算字母绘制的xy坐标，paint.measureText(letters.charAt(i)+"")  得到字母的宽
            float x = (width - paint.measureText(letters.charAt(i) + "")) / 2;
            float y = abcHeight * i + abcHeight - paint.measureText(letters.charAt(i) + "") / 2;
            canvas.drawText(letters.charAt(i) + "", x, y, paint);
        }
    }

    private float y;//点击的y坐标
    private int lastSelectedIndex = -1;//记录上一次的位置
    private boolean isTouch = false;//是否处于按住的状态

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        y = event.getY();
        selectedIndex = (int) (y / abcHeight);
        if (selectedIndex <= 0) selectedIndex = 1;
        if (selectedIndex >= letters.length() - 1) selectedIndex = letters.length() - 2;
        if (selectedIndex != lastSelectedIndex) {
            invalidate();
            if (letterChangeListener != null)
            {
                letterChangeListener.onLetterChange(selectedIndex);
            }
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                break;
            case MotionEvent.ACTION_UP:
                letterChangeListener.onClickUp();
                isTouch = false;
                break;
        }
        invalidate();
        return true;
    }

    /**
     * 回调接口
     */
    //回调接口(当前是哪一栏，则字母就显示哪一个)
    public interface OnLetterChangeListener {
        void onLetterChange(int selectedIndex);
        void onClickUp();
    }

    private OnLetterChangeListener letterChangeListener;

    public void setOnLetterChangeListener(OnLetterChangeListener letterChangeListener) {
        this.letterChangeListener = letterChangeListener;
    }

    //设置当前那个字母被选中
    public void setSelected(int section) {

        this.selectedIndex = section;
        invalidate();
    }
}
