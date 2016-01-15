package net.android.dialog;

import java.util.Timer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DiaLogN extends Activity {
	/** Called when the activity is first created. */
	private Button btn_dialog1;
	private Button btn_custom;
	private Button btn_custom2;
	private TextView show;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btn_dialog1 = (Button) findViewById(R.id.btn_dialog1);
		btn_dialog1.setOnClickListener(new btn_dialo1onClick());
		
		btn_custom = (Button) findViewById(R.id.btn_custom);
		btn_custom.setOnClickListener(new btn_customClick());
		
		btn_custom2 = (Button) findViewById(R.id.btn_custom2);
		btn_custom2.setOnClickListener(new btn_custom2Click());
		show = (TextView) findViewById(R.id.show);
	}
	class btn_custom2Click implements OnClickListener {

		@Override
    	public void onClick(View v) {
			Context context=DiaLogN.this;
			CustomDialog2 cd = new CustomDialog2(context);   
		    cd.show();   
    	}
	}
	
	
	class CustomDialog2 extends Dialog {
		public CustomDialog2(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		
		 protected void onCreate(Bundle savedInstanceState){
			 super.onCreate(savedInstanceState);
			 
			 setContentView(R.layout.custom_dialog);
			 setTitle("选择播放列表");

			 TextView text = (TextView)findViewById(R.id.text);
			 text.setText("Hello, this is a custom dialog!");
			 ImageView image = (ImageView)findViewById(R.id.image);
			 image.setImageResource(R.drawable.music_ico);
			 
			 Button buttonYes = (Button) findViewById(R.id.button_yes);
			 buttonYes.setHeight(5);
			 buttonYes.setOnClickListener(new Button.OnClickListener(){

					public void onClick(View v) {
						show.setText("单击YES");
						dismiss();
						
					}
		        });
			 Button buttonNo = (Button) findViewById(R.id.button_no);
			 buttonNo.setSingleLine(true);
			 buttonNo.setOnClickListener(new Button.OnClickListener(){

					public void onClick(View v) {
						show.setText("单击NO");
						// TODO Auto-generated method stub
						dismiss();
						
					}
		        });
		 }
		 
		 //called when this dialog is dismissed
		 protected void onStop() {
			 Log.d("TAG","+++++++++++++++++++++++++++");
		 }
		 

	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	class btn_dialo1onClick implements OnClickListener {

		@Override
    	public void onClick(View v) {
    		show.setText("click me!");
    		AlertDialog.Builder ad=new AlertDialog.Builder(DiaLogN.this);
    		/*
    		setTitle()：给对话框设置title.
    		setIcon():给对话框设置图标。
    		setMessage():设置对话框的提示信息
    		setItems()：设置对话框要显示的一个list,一般用于要显示几个命令时
    		setSingleChoiceItems():设置对话框显示一个单选的List
    		setMultiChoiceItems():用来设置对话框显示一系列的复选框。
    		setPositiveButton():给对话框添加”Yes”按钮。
    		setNegativeButton():给对话框添加”No”按钮。
    		show():显示对话框，一般放最后
    		*/
    		 
    				ad.setTitle("选项");//设置对话框标题
    				//ad.setMessage("操作");//设置对话框内容
    				ad.setPositiveButton("确认", new DialogInterface.OnClickListener() {
    		 
    					@Override
    					public void onClick(DialogInterface dialog, int i) {
    		 
    					}
    				});
    				ad.setNegativeButton("官方网站",new DialogInterface.OnClickListener() {
    					//显示官方网站按钮，点击打开浏览器，转向www.pocketdigi.com
    					@Override
    					public void onClick(DialogInterface dialog, int i) {
    						Uri uri=Uri.parse("http://www.pocketdigi.com");
    						Intent intent=new Intent(Intent.ACTION_VIEW,uri);
    						startActivity(intent);
    					}
    				});
    				
    				ad.setItems(new String[]{"播放","从列表中移除"},new itemListonClick());
    				ad.show();//显示对话框
    				
    	}
	}
	class itemListonClick implements  android.content.DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch(which){
				case 0:
					show.setText("播放");
					break;
				case 1:
					show.setText("从播放列表移除");
					break;
				}
		}
	}
	
	
	class btn_customClick implements OnClickListener {

		@Override
    	public void onClick(View v) {
    		show.setText("click me! 自定义对话框");
    		// TODO Auto-generated method stub   
    		int m_count = 0;   
    	    //声明进度条对话框   
    	    final ProgressDialog m_pDialog; 
            //创建ProgressDialog对象   
            m_pDialog = new ProgressDialog(DiaLogN.this);   

            // 设置进度条风格，风格为圆形，旋转的   
            m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);   

            // 设置ProgressDialog 标题   
          //  m_pDialog.setTitle("提示");   
              
            // 设置ProgressDialog 提示信息   
            m_pDialog.setMessage("正在处理中...");   

            // 设置ProgressDialog 标题图标   
          //  m_pDialog.setIcon(R.drawable.img1);   

            // 设置ProgressDialog 的进度条是否不明确   
            m_pDialog.setIndeterminate(false);   
              
            // 设置ProgressDialog 是否可以按退回按键取消   
            m_pDialog.setCancelable(true);   
              
            // 设置ProgressDialog 的一个Button   
            /*
            m_pDialog.setButton("确定", new DialogInterface.OnClickListener() {   
                public void onClick(DialogInterface dialog, int i)   
                {   
                    //点击“确定按钮”取消对话框   
                    
                }   
            });   
			*/
            // 让ProgressDialog显示   
            m_pDialog.show();  
            Timer timer = new Timer();
            class MyTask extends java.util.TimerTask{ 
                int num=0;
                     public void run() {
                         num++;
                         System.out.println("  己花费   "+num+" S");
                        if(num>=4)
                        {
                        	  m_pDialog.cancel(); 
                        }
                     }
                 }

            timer.schedule(new MyTask(), 1, 1000); //在1毫秒后执行此任务,每次间隔1秒
    	}
	}
	
	
	
}