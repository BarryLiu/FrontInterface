package com.ios.sinian;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.ios.sinian.AddressBook.R;
import com.ios.sinian.AddressBook.StickyListHeadersAdapter;

public class TestBaseAdapter extends BaseAdapter implements
		StickyListHeadersAdapter, SectionIndexer {

	/** 内容 */
	private ArrayList<String> countries;
	/** head */
	private ArrayList<String> sections;

	private LayoutInflater inflater;

	public TestBaseAdapter(Context context, ArrayList<String> countries) {
		inflater = LayoutInflater.from(context);
		this.countries = countries;
	}

	@Override
	public int getCount() {
		return countries.size();
	}

	@Override
	public Object getItem(int position) {
		return countries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.test_list_item_layout,
					parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.text.setText(countries.get(position));

		return convertView;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HeaderViewHolder holder;
		if (convertView == null) {
			holder = new HeaderViewHolder();
			convertView = inflater.inflate(R.layout.header, parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		} else {
			holder = (HeaderViewHolder) convertView.getTag();
		}
		String headerText = "" + countries.get(position).charAt(0);
		holder.text.setText(headerText);
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		return countries.get(position).charAt(0);
	}

	class HeaderViewHolder {
		TextView text;
	}

	class ViewHolder {
		TextView text;
	}

	@Override
	public int getPositionForSection(int section) {
		if (section >= sections.size()) {
			section = sections.size() - 1;
		} else if (section < 0) {
			section = 0;
		}

		int position = 0;
		char sectionChar = sections.get(section).charAt(0);
		for (int i = 0; i < countries.size(); i++) {
			if (sectionChar == countries.get(position).charAt(0)) {
				position = i;
				break;
			}
		}
		return position;
	}

	@Override
	public int getSectionForPosition(int position) {
		if (position >= countries.size()) {
			position = countries.size() - 1;
		} else if (position < 0) {
			position = 0;
		}

		return sections.indexOf("" + countries.get(position).charAt(0));
	}

	@Override
	public Object[] getSections() {
		return sections.toArray(new String[sections.size()]);
	}

	public void clearAll() {
		countries.clear();
		sections.clear();
	}

}
