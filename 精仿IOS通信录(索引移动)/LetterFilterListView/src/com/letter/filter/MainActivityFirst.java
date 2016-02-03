package com.letter.filter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.letter.filter.ui.LetterSideBar;
import com.letter.filter.ui.LetterSideBar.OnTouchingLetterListener;
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
public class MainActivityFirst extends Activity implements OnTouchingLetterListener {

	private LetterSideBar mLatterSideBar;
	private TextView mSelectLetterShowTv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_first);
		initView();
		initLisenter();
	}

	private void initView() {
		mLatterSideBar = (LetterSideBar) this
				.findViewById(R.id.latter_side_bar);
		mSelectLetterShowTv = (TextView) this
				.findViewById(R.id.select_letter_show);
	}

	private void initLisenter() {
		mLatterSideBar.setOnTouchingLetterChangedListener(this);
	}

	@Override
	public void onTouchingLetterChanged(String letter, boolean isOnTouch) {
		if(isOnTouch){
			mSelectLetterShowTv.setText(letter);
			mSelectLetterShowTv.setVisibility(View.VISIBLE);
		}else{
			mSelectLetterShowTv.setVisibility(View.GONE);
		}
	}
	
}
