package com.geniuseoe2012.dialog;

import java.util.List;

import android.content.Context;
import android.view.View;

import com.geniuseoe2012.demo.MultiChoicAdapter;
import com.geniuseoe2012.demo.R;
import com.geniuseoe2012.demo.Utils;

public class MultiChoicDialog extends AbstractChoickDialog{

	private MultiChoicAdapter<String> mMultiChoicAdapter;

	public MultiChoicDialog(Context context, List<String> list, boolean[] flag) {
		super(context, list);
		
		initData(flag);
	}
	

	protected void initData(boolean flag[]) {
		// TODO Auto-generated method stub
		mMultiChoicAdapter = new MultiChoicAdapter<String>(mContext, mList, flag, R.drawable.selector_checkbox1);
		
		mListView.setAdapter(mMultiChoicAdapter);
		mListView.setOnItemClickListener(mMultiChoicAdapter);   
		
		Utils.setListViewHeightBasedOnChildren(mListView);

	}


	public boolean[] getSelectItem()
	{
		return mMultiChoicAdapter.getSelectItem();
	}
	
}
