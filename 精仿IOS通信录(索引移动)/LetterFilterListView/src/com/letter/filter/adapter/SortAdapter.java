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
public class SortAdapter extends CommonAdapter<SortModel> implements LetterSection{

	public SortAdapter(Context context, List<SortModel> mDatas) {
		super(context, mDatas, R.layout.item, false);
	}

	/**
	 * ���ݷ����仯ʱ,���ô˷���������
	 */
	public void updateListView(List<SortModel> list) {
		this.mDatas = list;
		notifyDataSetChanged();
	}

	/**
	 * ���ݵ�ǰλ�û�ȡ����ĸ��Char ASCIIֵ
	 */
	public int getSectionForPosition(int position) {
		return mDatas.get(position).getSortLetters().charAt(0);
	}
	
	/**
	 * ���ݵ�ǰλ�û�ȡ����ĸ
	 */
	public String getFirstLetter(int position) {
		if(position< 0 || position >= mDatas.size()){
			return "A";
		}
		return mDatas.get(position).getSortLetters();
	}

	/**
	 * �õ���ĸ��һ�γ��ֵ�λ��
	 * 
	 * @return
	 */
	public int letterFirstPosition(String section) {
		return loopAccessPosition(section.charAt(0));
	}

	/**
	 * ѭ��������ȡλ��
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
		// ����position��ȡ���������ĸ��Char ASCIIֵ
		int section = getSectionForPosition(position);
		// �����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if (position == loopAccessPosition(section)) {
			holder.setViewVisible(R.id.catalog);
			holder.setText(R.id.catalog, bean.getSortLetters());
		} else {
			holder.setViewGone(R.id.catalog);
		}
		holder.setText(R.id.title, bean.getName());
	}
}