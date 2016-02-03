package com.letter.filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button first, second, third, fourth;

	@Override
	public void onCreate(Bundle savedInstaceState) {
		super.onCreate(savedInstaceState);
		setContentView(R.layout.activity_main);
		first = (Button) findViewById(R.id.first);
		second = (Button) findViewById(R.id.second);
		third = (Button) findViewById(R.id.third);
		fourth = (Button) findViewById(R.id.fourth);

		first.setOnClickListener(this);
		second.setOnClickListener(this);
		third.setOnClickListener(this);
		fourth.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.first:
			intent = new Intent(this, MainActivityFirst.class);
			break;
		case R.id.second:
			intent = new Intent(this, MainActivitySecond.class);
			break;
		case R.id.third:
			intent = new Intent(this, MainActivityThird.class);
			break;
		case R.id.fourth:
			intent = new Intent(this, MainActivityFour.class);
			break;
		}

		startActivity(intent);
	}
}
