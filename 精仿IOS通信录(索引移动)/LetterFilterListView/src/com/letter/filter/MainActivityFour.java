package com.letter.filter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.letter.filter.ui.LetterFilterListView;
import com.letter.filter.ui.LetterFilterListView.OnFilterItemClickListener;
import com.letter.filter.util.PlaceUtil;

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
public class MainActivityFour extends Activity implements TextWatcher,
		OnFilterItemClickListener {
	private LetterFilterListView mLetterFilterListView;
	private EditText mFilterEt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_third);
		initView();
		initData();
	}

	private void initData() {
		mLetterFilterListView.setFilterData(PlaceUtil.getCity(this));
	}

	private void initView() {
		mLetterFilterListView = (LetterFilterListView) findViewById(R.id.letter_filter_lv);
		mFilterEt = (EditText) findViewById(R.id.filter_et);
		mFilterEt.addTextChangedListener(this);
		mLetterFilterListView.setOnItemClickListener(this);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		mLetterFilterListView.filterData(s.toString());
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void onItemClick(String select) {
		Toast.makeText(this, select, Toast.LENGTH_SHORT).show();
	}
}
