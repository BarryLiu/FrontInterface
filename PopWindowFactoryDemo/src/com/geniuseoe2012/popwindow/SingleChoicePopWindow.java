package com.geniuseoe2012.popwindow;

import java.util.List;

import com.geniuseoe2012.demo.R;
import com.geniuseoe2012.demo.SingleChoicAdapter;
import com.geniuseoe2012.demo.Utils;

import android.content.Context;
import android.view.View;

public class SingleChoicePopWindow extends AbstractChoicePopWindow {

	private SingleChoicAdapter<String> mSingleChoicAdapter;

	public SingleChoicePopWindow(Context context, View parentView,
			List<String> list) {
		super(context, parentView, list);

		initData();
	}

	protected void initData() {
		mSingleChoicAdapter = new SingleChoicAdapter<String>(mContext, mList,
				R.drawable.selector_checkbox2);

		mListView.setAdapter(mSingleChoicAdapter);
		mListView.setOnItemClickListener(mSingleChoicAdapter);

		Utils.setListViewHeightBasedOnChildren(mListView);

	}

	public int getSelectItem() {
		return mSingleChoicAdapter.getSelectItem();
	}

}
