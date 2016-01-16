package com.vatty.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vatty.activity.R;
import com.vatty.model.Contact;

public class ContactAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Contact> list = new ArrayList<Contact>();
	
	public ContactAdapter(Context context,ArrayList<Contact> list){
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Contact hh = list.get(position);
		H h = null;
		if(view==null){
			h = new H();
			view = LayoutInflater.from(context).inflate(R.layout.list3, parent, false);
			h.pic = (ImageView)view.findViewById(R.id.tx1);
			h.name = (TextView)view.findViewById(R.id.tx2);
			
			view.setTag(h);
		}else{
			h = (H)view.getTag();
		}
		
		h.pic.setImageResource(Integer.parseInt(hh.getTxPath()));
		h.name.setText(hh.getName());
		
		return view;
	}

	class H{
		ImageView pic;
		TextView name;
	}
}
