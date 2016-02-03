package com.letter.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.hui.util.log.LogUtils;
import com.letter.filter.adapter.SortAdapter;
import com.letter.filter.mode.SortModel;
import com.letter.filter.ui.LetterFilterListView;
import com.letter.filter.util.CharacterParser;
import com.letter.filter.util.PinyinComparator;
import com.letter.filter.util.PlaceUtil;
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
public class MainActivityThird extends Activity implements TextWatcher {
	private LetterFilterListView mLetterFilterListView;
	private SortAdapter mSortAdapter;
	/**
	 * ����ƴ��������ListView�����������
	 */
	private PinyinComparator pinyinComparator;
	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;

	private List<SortModel> mSourceDateList;

	private EditText mFilterEt;

	private List<SortModel> mFilterDateList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_third);
		initView();
		initData();
	}

	private void initData() {
		// ʵ��������תƴ����
		pinyinComparator = new PinyinComparator();
		characterParser = CharacterParser.getInstance();

		mSourceDateList = filledData(PlaceUtil.getCity(this));
		mFilterDateList = new ArrayList<SortModel>();

		// ����a-z��������Դ����
		Collections.sort(mSourceDateList, pinyinComparator);

		mSortAdapter = new SortAdapter(this, mSourceDateList);
		mLetterFilterListView.setAdapter(mSortAdapter);
	}

	private void initView() {
		mLetterFilterListView = (LetterFilterListView) findViewById(R.id.letter_filter_lv);
		mFilterEt = (EditText) findViewById(R.id.filter_et);
		mFilterEt.addTextChangedListener(this);
	}

	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		mFilterDateList.clear();
		if (TextUtils.isEmpty(filterStr)) {
			mFilterDateList.addAll(mSourceDateList);
		} else {
			for (SortModel sortModel : mSourceDateList) {
				String name = sortModel.getName();
				// ѭ���ж� �Ƿ����
				// indexOf �ж��Ƿ��������,��Ҫƥ��ƴ��
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString().toLowerCase())) {
					mFilterDateList.add(sortModel);
				}
			}
		}
		// ����a-z��������
		Collections.sort(mFilterDateList, pinyinComparator);
		mSortAdapter.updateListView(mFilterDateList);
	}

	/**
	 * ����תƴ��
	 */
	private List<SortModel> filledData(ArrayList<String> citys) {
		List<SortModel> mSortList = new ArrayList<SortModel>();
		for (int i = 0; i < citys.size(); i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(citys.get(i));
			// ����ת����ƴ��
			String pinyin = characterParser.getSelling(citys.get(i));
			String sortString = pinyin.substring(0, 1).toUpperCase();
			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}
			mSortList.add(sortModel);
		}
		return mSortList;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		LogUtils.e(s.toString());
		filterData(s.toString());
	}

	@Override
	public void afterTextChanged(Editable s) {

	}
}
