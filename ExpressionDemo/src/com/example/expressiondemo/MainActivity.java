package com.example.expressiondemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Context context;
	private MyPopupWindow commentPopwindow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		((TextView)findViewById(R.id.tv_world)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				commentPopwindow = new MyPopupWindow((Activity)context, new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						
						commentPopwindow.dismiss();
						if(!commentPopwindow.getText().equals("")){
							
							commentPopwindow.getText();//回复内容
							}
					}
				});
				commentPopwindow.showAtLocation(((TextView)findViewById(R.id.tv_world)),Gravity.BOTTOM, 0, 0);
			}
		});
		
		/*SpannableString spannableString = ExpressionUtil
				.getExpressionString(context, "content");//"content"是你回复的内容
		((TextView)findViewById(R.id.tv_world)).setText("");*/
	}
}
