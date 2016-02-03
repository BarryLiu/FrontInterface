package com.letter.filter.adapter;

import java.util.List;

import android.content.Context;

import com.hui.adapter.CommonAdapter;
import com.hui.adapter.ViewHolder;
import com.letter.filter.R;
import com.letter.filter.mode.SortModel;
import com.letter.filter.ui.LetterSection;
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
public class SortAdapter extends CommonAdapter<SortModel> implements LetterSection{

	public SortAdapter(Context context, List<SortModel> mDatas) {
		super(context, mDatas, R.layout.item, false);
	}

	/**
	 * 数据发生变化时,调用此方法来更新
	 */
	public void updateListView(List<SortModel> list) {
		this.mDatas = list;
		notifyDataSetChanged();
	}

	/**
	 * 根据当前位置获取首字母的Char ASCII值
	 */
	public int getSectionForPosition(int position) {
		return mDatas.get(position).getSortLetters().charAt(0);
	}
	
	/**
	 * 根据当前位置获取首字母
	 */
	public String getFirstLetter(int position) {
		if(position< 0 || position >= mDatas.size()){
			return "A";
		}
		return mDatas.get(position).getSortLetters();
	}

	/**
	 * 得到字母第一次出现的位置
	 * 
	 * @return
	 */
	public int letterFirstPosition(String section) {
		return loopAccessPosition(section.charAt(0));
	}

	/**
	 * 循环遍历获取位置
	 */
	private int loopAccessPosition(int letter) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = mDatas.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == letter) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void convert(ViewHolder holder, SortModel bean, int position) {
		// 根据position获取分类的首字母的Char ASCII值
		int section = getSectionForPosition(position);
		// 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == loopAccessPosition(section)) {
			holder.setViewVisible(R.id.catalog);
			holder.setText(R.id.catalog, bean.getSortLetters());
		} else {
			holder.setViewGone(R.id.catalog);
		}
		holder.setText(R.id.title, bean.getName());
	}
}