package com.letter.filter.ui;

import java.util.List;

import com.letter.filter.mode.SortModel;


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
public interface LetterSection {
	/**
	 * 得到字母第一次出现的位置
	 */
	public int letterFirstPosition(String section);

	/**
	 * 根据当前位置获取首字母
	 */
	public String getFirstLetter(int position);
	
	
	/**
	 * 根据当前位置获取首字母的Char ASCII值
	 */
	public int getSectionForPosition(int position);
	
	
	/**
	 * 当数据发生变化时,调用此方法来更新
	 */
	public void updateListView(List<SortModel> list);
}
