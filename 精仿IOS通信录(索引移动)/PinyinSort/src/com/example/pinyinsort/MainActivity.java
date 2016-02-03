package com.example.pinyinsort;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pinyinsort.RightCharacterListView.OnTouchingLetterChangedListener;

public class MainActivity extends Activity implements
		OnTouchingLetterChangedListener {
	private ListView listView;
	private RightCharacterListView characterListView;
	List<Country> listCountry = new ArrayList<Country>();
	ListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initValues();
		initView();
	}

	private void initView() {
		listView = (ListView) findViewById(R.id.listview);
		characterListView = (RightCharacterListView) findViewById(R.id.right_list);
		characterListView.setOnTouchingLetterChangedListener(this);
		listAdapter = new ListAdapter(this);
		listAdapter.setList(listCountry);
		listView.setAdapter(listAdapter);
	}

	@Override
	public void onTouchingLetterChanged(String s) {
		listAdapter.notifyListSelect(s, listView);
	}

	private void initValues() {
		Country c = new Country();
		c.name = "安徽省";
		c.code = "1";
		listCountry.add(c);
		c = new Country();
		c.name = "江苏省";
		c.code = "2";
		listCountry.add(c);
		c = new Country();
		c.name = "浙江省";
		c.code = "3";
		listCountry.add(c);
		c = new Country();
		c.name = "江西省";
		c.code = "5";
		listCountry.add(c);
		c = new Country();
		c.name = "山东省";
		c.code = "6";
		listCountry.add(c);
		c = new Country();
		c.name = "北京市";
		c.code = "7";
		listCountry.add(c);
		c = new Country();
		c.name = "天津市";
		c.code = "8";
		listCountry.add(c);
		c = new Country();
		c.name = "新疆省";
		c.code = "9";
		listCountry.add(c);
		c = new Country();
		c.name = "上海市";
		c.code = "10";
		listCountry.add(c);

		c = new Country();
		c.name = "====上海市";
		c.code = "11";
		listCountry.add(c);
	}
}
