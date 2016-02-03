package com.letter.filter.ui;

import java.util.List;

import com.letter.filter.mode.SortModel;


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
public interface LetterSection {
	/**
	 * �õ���ĸ��һ�γ��ֵ�λ��
	 */
	public int letterFirstPosition(String section);

	/**
	 * ���ݵ�ǰλ�û�ȡ����ĸ
	 */
	public String getFirstLetter(int position);
	
	
	/**
	 * ���ݵ�ǰλ�û�ȡ����ĸ��Char ASCIIֵ
	 */
	public int getSectionForPosition(int position);
	
	
	/**
	 * �����ݷ����仯ʱ,���ô˷���������
	 */
	public void updateListView(List<SortModel> list);
}
