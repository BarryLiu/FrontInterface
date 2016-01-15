package com.blend.client;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button button;
	private PickDialog pickDialog;
	private ArrayList<String> list=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//for test 填充测试数据
		for(int i=0;i<10;i++){
			list.add("第"+i+"条");
		}
		
		button=(Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pickDialog=new PickDialog(MainActivity.this, getString(R.string.title), new PickDialogListener() {

					@Override
					public void onRightBtnClick() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onListItemLongClick(int position, String string) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onListItemClick(int position, String string) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onLeftBtnClick() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});
				pickDialog.show();
				
				//for test 延迟三秒加载
				new Handler().postDelayed(new Runnable(){   

				    public void run() {   

				    //execute the task 
				    	pickDialog.initListViewData(list);

				    }   

				 }, 3000);   
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
