package com.letter.filter.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.letter.filter.R;
import com.letter.filter.adapter.SortAdapter;
import com.letter.filter.mode.SortModel;
import com.letter.filter.ui.LetterSideBar.OnTouchingLetterListener;
import com.letter.filter.util.CharacterParser;
import com.letter.filter.util.PinyinComparator;

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
public class LetterFilterListView extends RelativeLayout implements
		OnTouchingLetterListener, OnScrollListener,
		android.widget.AdapterView.OnItemClickListener {
	private Context mContext;
	private LetterSideBar mLatterSideBar;
	private TextView mSelectLetterShowTv;
	private ListView mSortLv;
	private LetterSection mSortAdapter = null;
	private OnFilterItemClickListener mItemClickListener;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;

	// 全部的数据集合
	private List<SortModel> mSourceDateList;
	// 根据String筛选的集合
	private List<SortModel> mFilterDateList;

	public LetterFilterListView(Context context) {
		this(context, null);
	}

	public LetterFilterListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LetterFilterListView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		initLayout();
		initLisenter();
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 实例化汉字转拼音类
		pinyinComparator = new PinyinComparator();
		characterParser = CharacterParser.getInstance();
		mFilterDateList = new ArrayList<SortModel>();
	}

	/**
	 * 初始化布局
	 */
	private void initLayout() {
		inflate(mContext, R.layout.ui_letter_filter_view, this);
		mLatterSideBar = (LetterSideBar) this
				.findViewById(R.id.latter_side_bar);
		mSelectLetterShowTv = (TextView) this
				.findViewById(R.id.select_letter_show);
		mSortLv = (ListView) this.findViewById(R.id.sort_lv);
	}

	/**
	 * 初始化监听
	 */
	private void initLisenter() {
		mLatterSideBar.setOnTouchingLetterChangedListener(this);
		mSortLv.setOnScrollListener(this);
	}

	/**
	 * 设置Adapter
	 */
	public void setAdapter(ListAdapter adapter) {
		if (adapter == null) {
			throw new NullPointerException("adapter is null~");
		}
		mSortLv.setAdapter(adapter);

		if (adapter instanceof LetterSection) {
			this.mSortAdapter = (LetterSection) adapter;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// ListView 滑动的时候对应的改变SideBar的选择状态
		if (mSortAdapter != null && mLatterSideBar != null
				&& !mLatterSideBar.isCurrentIsTouch()) {
			mLatterSideBar.drawCureentLetter(mSortAdapter
					.getFirstLetter(firstVisibleItem));
		}
	}

	@Override
	public void onTouchingLetterChanged(String letter, boolean isOnTouch) {
		// 如果手指触摸SideBar，显示中间文字，并设置当期触摸的文字
		if (isOnTouch) {
			mSelectLetterShowTv.setText(letter);
			mSelectLetterShowTv.setVisibility(View.VISIBLE);
			// ListView 滚动到对应的字母
			lsitViewScrollToPosition(letter);
		} else {
			// 如果手指没有触摸SideBar，隐藏
			mSelectLetterShowTv.setVisibility(View.GONE);
		}
	}

	/**
	 * ListeView滚动到对应首字母的位置
	 */
	private void lsitViewScrollToPosition(String letter) {
		if (mSortAdapter != null) {
			// 获取首字母出现的位置
			int position = mSortAdapter.letterFirstPosition(letter);
			mSortLv.setSelection(position);
		}
	}

	/**
	 * 汉字转拼音填充数据
	 */
	public final List<SortModel> filledData(ArrayList<String> filterData) {
		List<SortModel> mSortList = new ArrayList<SortModel>();
		for (int i = 0; i < filterData.size(); i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(filterData.get(i));
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(filterData.get(i));
			String sortString = pinyin.substring(0, 1).toUpperCase();
			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}
			mSortList.add(sortModel);
		}
		return mSortList;
	}

	/**
	 * 设置数据
	 */
	public void setFilterData(ArrayList<String> filterData) {
		mSourceDateList = filledData(filterData);
		// 根据a-z进行排序源数据
		Collections.sort(mSourceDateList, pinyinComparator);
		mSortAdapter = new SortAdapter(mContext, mSourceDateList);
		setAdapter((ListAdapter) mSortAdapter);
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	public void filterData(String filterStr) {
		mFilterDateList.clear();
		if (TextUtils.isEmpty(filterStr)) {
			mFilterDateList.addAll(mSourceDateList);
		} else {
			for (SortModel sortModel : mSourceDateList) {
				String name = sortModel.getName();
				// 循环判断 是否包含
				// indexOf 判断是否包含汉字,还要匹配拼音
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString().toLowerCase())) {
					mFilterDateList.add(sortModel);
				}
			}
		}
		// 根据a-z进行排序
		Collections.sort(mFilterDateList, pinyinComparator);
		mSortAdapter.updateListView(mFilterDateList);
	}

	public void setOnItemClickListener(
			OnFilterItemClickListener itemClickListener) {
		mSortLv.setOnItemClickListener(this);
		this.mItemClickListener = itemClickListener;
	}

	public interface OnFilterItemClickListener {
		public void onItemClick(String select);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		SortModel selectItem = (SortModel) ((ListAdapter) mSortAdapter)
				.getItem(position);
		mItemClickListener.onItemClick(selectItem.getName());
	}
}
