package com.justlcw.letterlistview.letter;

import android.widget.BaseAdapter;

/**
 * 带有侧边字母列表的listView适配器
 * 
 *@Title:
 *@Description:
 *@Author:Justlcw
 *@Since:2014-5-8
 *@Version:
 */
public abstract class LetterBaseAdapter extends BaseAdapter
{
    /** 字母表头部 **/
    protected static final char HEADER = '+';
    /** 字母表尾部 **/
    protected static final char FOOTER = '#';

    /**
     * 是否需要隐藏没有匹配到的字母
     * 
     * @return true 隐藏, false 不隐藏
     * @Description:
     * @Author Justlcw
     * @Date 2014-5-8
     */
    public abstract boolean hideLetterNotMatch();
    
    /**
     * 获取字母对应的位置
     * 
     * @return position
     * @Description:
     * @Author Justlcw
     * @Date 2014-5-8
     */
    public abstract int getIndex(char letter);
}
