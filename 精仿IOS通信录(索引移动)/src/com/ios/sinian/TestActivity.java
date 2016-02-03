package com.ios.sinian;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ios.sinian.AddressBook.R;
import com.ios.sinian.AddressBook.StickyListHeadersListView;
import com.ios.sinian.AddressBook.StickyListHeadersListView.OnHeaderClickListener;

public class TestActivity extends Activity implements OnScrollListener,
		AdapterView.OnItemClickListener, OnHeaderClickListener {

	private static final String KEY_LIST_POSITION = "KEY_LIST_POSITION";
	private int firstVisible;
	private TestBaseAdapter adapter;

	private ArrayList<String> text = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);

		StickyListHeadersListView stickyList = (StickyListHeadersListView) findViewById(R.id.list);
		stickyList.setOnScrollListener(this);
		stickyList.setOnItemClickListener(this);
		stickyList.setOnHeaderClickListener(this);

		if (savedInstanceState != null) {
			firstVisible = savedInstanceState.getInt(KEY_LIST_POSITION);
		}

		stickyList.setEmptyView(findViewById(R.id.empty));
		for (int i = 0; i < 26; i++) {
			char ch = (char) ('a' + i);
			for (int j = 0; j < 5; j++) {
				text.add(ch + "测试联系人姓名" + j);
			}
		}
		adapter = new TestBaseAdapter(this, text);
		stickyList.setAdapter(adapter);
		stickyList.setSelection(firstVisible);

		stickyList.setDrawingListUnderStickyHeader(true);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_LIST_POSITION, firstVisible);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.firstVisible = firstVisibleItem;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "Item " + position + " 被点击!", 0).show();
	}

	@Override
	public void onHeaderClick(StickyListHeadersListView l, View header,
			int itemPosition, long headerId, boolean currentlySticky) {
		Toast.makeText(this, "header   " + (char) headerId + " 被点击!", 0).show();
	}

}