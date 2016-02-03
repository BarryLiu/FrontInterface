package com.zhzq.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.Toast;

import com.woozzu.android.indexablelistview.R;

public class IndexableListViewActivity extends Activity {
	private ArrayList<String> mItems;
	private IndexableListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mItems = new ArrayList<String>();
		mItems.add("A");
		mItems.add("B");
		mItems.add("C");
		mItems.add("D");
		mItems.add("The Hunger Games");
		mItems.add("The LEGO Ideas Book");
		mItems.add("E");
		mItems.add("Cs");
		mItems.add("Eleed");
		mItems.add("Eld");
		mItems.add("Dn Fever");
		mItems.add("Berley");
		mItems.add("Dever");
		mItems.add("Diarr");
		mItems.add("Stev");
		mItems.add("Inle)");
		mItems.add("11/22/63: A Novel");
		mItems.add("Elrrd");
		mItems.add("T");
		mItems.add("LEGO Ideas Book");
		mItems.add("Plum Novel");
		mItems.add("Catching");
		mItems.add("E");
		mItems.add("De");
		Collections.sort(mItems);

		ContentAdapter adapter = new ContentAdapter(this, android.R.layout.simple_list_item_1, mItems);

		mListView = (IndexableListView) findViewById(R.id.listview);
		mListView.setAdapter(adapter);
		mListView.setFastScrollEnabled(true);
		
		
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "arg2 : " + arg2 + ",arg2 : " + arg2, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private class ContentAdapter extends ArrayAdapter<String> implements SectionIndexer {

		private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		public ContentAdapter(Context context, int textViewResourceId, List<String> objects) {
			super(context, textViewResourceId, objects);
		}
		
		@Override
		public int getPositionForSection(int section) {
			// If there is no item for current section, previous section will be selected
			for (int i = section; i >= 0; i--) {
				for (int j = 0; j < getCount(); j++) {
					if (i == 0) {
						// For numeric section
						for (int k = 0; k <= 9; k++) {
							if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k)))
								return j;
						}
					} else {
						if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i))))
							return j;
					}
				}
			}
			return 0;
		}

		@Override
		public int getSectionForPosition(int position) {
			return 0;
		}

		@Override
		public Object[] getSections() {
			String[] sections = new String[mSections.length()];
			for (int i = 0; i < mSections.length(); i++)
				sections[i] = String.valueOf(mSections.charAt(i));
			return sections;
		}
	}
}