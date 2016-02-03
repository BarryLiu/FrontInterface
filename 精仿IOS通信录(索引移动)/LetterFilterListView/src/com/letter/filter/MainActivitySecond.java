package com.letter.filter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.letter.filter.adapter.SortAdapter;
import com.letter.filter.ui.LetterSideBar;
import com.letter.filter.ui.LetterSideBar.OnTouchingLetterListener;
import com.letter.filter.util.TestDataUtil;
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
public class MainActivitySecond extends Activity implements OnScrollListener,
		OnTouchingLetterListener {
	private LetterSideBar mLatterSideBar;
	private TextView mSelectLetterShowTv;
	private ListView mSortLv;
	private SortAdapter mSortAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_second);
		initView();
		initLisenter();
		initData();
	}

	private void initData() {
		mSortAdapter = new SortAdapter(this, TestDataUtil.getTestSorts());
		mSortLv.setAdapter(mSortAdapter);
	}

	private void initView() {
		mLatterSideBar = (LetterSideBar) this
				.findViewById(R.id.latter_side_bar);
		mSelectLetterShowTv = (TextView) this
				.findViewById(R.id.select_letter_show);
		mSortLv = (ListView) this.findViewById(R.id.sort_lv);
	}

	private void initLisenter() {
		mLatterSideBar.setOnTouchingLetterChangedListener(this);
		mSortLv.setOnScrollListener(this);
	}

	@Override
	public void onTouchingLetterChanged(String letter, boolean isOnTouch) {
		if (isOnTouch) {
			mSelectLetterShowTv.setText(letter);
			mSelectLetterShowTv.setVisibility(View.VISIBLE);
			lsitViewScrollToPosition(letter);
		} else {
			mSelectLetterShowTv.setVisibility(View.GONE);
		}
	}

	/**
	 * ListeView滚动到对应首字母的位置
	 */
	private void lsitViewScrollToPosition(String letter) {
		// 获取首字母出现的位置
		int position = mSortAdapter.letterFirstPosition(letter);
		mSortLv.setSelection(position);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// ListView 滑动的时候对应的改变SideBar的选择状态
		if (mSortAdapter != null && mLatterSideBar != null && !mLatterSideBar.isCurrentIsTouch()) {
			mLatterSideBar.drawCureentLetter(mSortAdapter
					.getFirstLetter(firstVisibleItem));
		}
	}
}
