package com.geniuseoe2012.dialog;

import java.util.List;

import com.geniuseoe2012.demo.R;
import com.geniuseoe2012.demo.SingleChoicAdapter;
import com.geniuseoe2012.demo.Utils;

import android.content.Context;

public class SingleChoiceDialog extends AbstractChoickDialog {

	private SingleChoicAdapter<String> mSingleChoicAdapter;

	public SingleChoiceDialog(Context context, List<String> list) {
		super(context, list);

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
