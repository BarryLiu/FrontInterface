package com.sunday.customs;


import com.example.customs.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * 自定义控件 继承EditText实现 点后面的叉删除
 * @author Barry
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
