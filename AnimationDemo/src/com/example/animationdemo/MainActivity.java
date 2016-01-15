package com.example.animationdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	List<String> datas = new ArrayList<String>();
	GridView gridView;
	GViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getData();
		setContentView(R.layout.activity_main);
		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new GViewAdapter(this, datas);
		gridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		gridView.setOnItemClickListener(new OnItemClickListener() {
			private int[] location;

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				location = new int[2];
				v.getLocationInWindow(location); // ��ȡ�ڵ�ǰ�����ڵľ�������
				v.getLocationOnScreen(location);// ��ȡ��������Ļ�ڵľ�������
				new TestDialog(MainActivity.this, location, v).show();
			}
		});
	}

	void getData() {
		for (int i = 0; i < 30; i++) {
			datas.add("С��:" + i + "��");
		}
	}
}
