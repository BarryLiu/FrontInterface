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
	 * ����ƴ��������ListView�����������
	 */
	private PinyinComparator pinyinComparator;
	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;

	// ȫ�������ݼ���
	private List<SortModel> mSourceDateList;
	// ����Stringɸѡ�ļ���
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
	 * ��ʼ������
	 */
	private void initData() {
		// ʵ��������תƴ����
		pinyinComparator = new PinyinComparator();
		characterParser = CharacterParser.getInstance();
		mFilterDateList = new ArrayList<SortModel>();
	}

	/**
	 * ��ʼ������
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
	 * ��ʼ������
	 */
	private void initLisenter() {
		mLatterSideBar.setOnTouchingLetterChangedListener(this);
		mSortLv.setOnScrollListener(this);
	}

	/**
	 * ����Adapter
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
		// ListView ������ʱ���Ӧ�ĸı�SideBar��ѡ��״̬
		if (mSortAdapter != null && mLatterSideBar != null
				&& !mLatterSideBar.isCurrentIsTouch()) {
			mLatterSideBar.drawCureentLetter(mSortAdapter
					.getFirstLetter(firstVisibleItem));
		}
	}

	@Override
	public void onTouchingLetterChanged(String letter, boolean isOnTouch) {
		// �����ָ����SideBar����ʾ�м����֣������õ��ڴ���������
		if (isOnTouch) {
			mSelectLetterShowTv.setText(letter);
			mSelectLetterShowTv.setVisibility(View.VISIBLE);
			// ListView ��������Ӧ����ĸ
			lsitViewScrollToPosition(letter);
		} else {
			// �����ָû�д���SideBar������
			mSelectLetterShowTv.setVisibility(View.GONE);
		}
	}

	/**
	 * ListeView��������Ӧ����ĸ��λ��
	 */
	private void lsitViewScrollToPosition(String letter) {
		if (mSortAdapter != null) {
			// ��ȡ����ĸ���ֵ�λ��
			int position = mSortAdapter.letterFirstPosition(letter);
			mSortLv.setSelection(position);
		}
	}

	/**
	 * ����תƴ���������
	 */
	public final List<SortModel> filledData(ArrayList<String> filterData) {
		List<SortModel> mSortList = new ArrayList<SortModel>();
		for (int i = 0; i < filterData.size(); i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(filterData.get(i));
			// ����ת����ƴ��
			String pinyin = characterParser.getSelling(filterData.get(i));
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

	/**
	 * ��������
	 */
	public void setFilterData(ArrayList<String> filterData) {
		mSourceDateList = filledData(filterData);
		// ����a-z��������Դ����
		Collections.sort(mSourceDateList, pinyinComparator);
		mSortAdapter = new SortAdapter(mContext, mSourceDateList);
		setAdapter((ListAdapter) mSortAdapter);
	}

	/**
	 * ����������е�ֵ���������ݲ�����ListView
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
