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
			 setTitle("ѡ�񲥷��б�");

			 TextView text = (TextView)findViewById(R.id.text);
			 text.setText("Hello, this is a custom dialog!");
			 ImageView image = (ImageView)findViewById(R.id.image);
			 image.setImageResource(R.drawable.music_ico);
			 
			 Button buttonYes = (Button) findViewById(R.id.button_yes);
			 buttonYes.setHeight(5);
			 buttonYes.setOnClickListener(new Button.OnClickListener(){

					public void onClick(View v) {
						show.setText("����YES");
						dismiss();
						
					}
		        });
			 Button buttonNo = (Button) findViewById(R.id.button_no);
			 buttonNo.setSingleLine(true);
			 buttonNo.setOnClickListener(new Button.OnClickListener(){

					public void onClick(View v) {
						show.setText("����NO");
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
    		setTitle()�����Ի�������title.
    		setIcon():���Ի�������ͼ�ꡣ
    		setMessage():���öԻ������ʾ��Ϣ
    		setItems()�����öԻ���Ҫ��ʾ��һ��list,һ������Ҫ��ʾ��������ʱ
    		setSingleChoiceItems():���öԻ�����ʾһ����ѡ��List
    		setMultiChoiceItems():�������öԻ�����ʾһϵ�еĸ�ѡ��
    		setPositiveButton():���Ի�����ӡ�Yes����ť��
    		setNegativeButton():���Ի�����ӡ�No����ť��
    		show():��ʾ�Ի���һ������
    		*/
    		 
    				ad.setTitle("ѡ��");//���öԻ������
    				//ad.setMessage("����");//���öԻ�������
    				ad.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
    		 
    					@Override
    					public void onClick(DialogInterface dialog, int i) {
    		 
    					}
    				});
    				ad.setNegativeButton("�ٷ���վ",new DialogInterface.OnClickListener() {
    					//��ʾ�ٷ���վ��ť��������������ת��www.pocketdigi.com
    					@Override
    					public void onClick(DialogInterface dialog, int i) {
    						Uri uri=Uri.parse("http://www.pocketdigi.com");
    						Intent intent=new Intent(Intent.ACTION_VIEW,uri);
    						startActivity(intent);
    					}
    				});
    				
    				ad.setItems(new String[]{"����","���б����Ƴ�"},new itemListonClick());
    				ad.show();//��ʾ�Ի���
    				
    	}
	}
	class itemListonClick implements  android.content.DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch(which){
				case 0:
					show.setText("����");
					break;
				case 1:
					show.setText("�Ӳ����б��Ƴ�");
					break;
				}
		}
	}
	
	
	class btn_customClick implements OnClickListener {

		@Override
    	public void onClick(View v) {
    		show.setText("click me! �Զ���Ի���");
    		// TODO Auto-generated method stub   
    		int m_count = 0;   
    	    //�����������Ի���   
    	    final ProgressDialog m_pDialog; 
            //����ProgressDialog����   
            m_pDialog = new ProgressDialog(DiaLogN.this);   

            // ���ý�������񣬷��ΪԲ�Σ���ת��   
            m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);   

            // ����ProgressDialog ����   
          //  m_pDialog.setTitle("��ʾ");   
              
            // ����ProgressDialog ��ʾ��Ϣ   
            m_pDialog.setMessage("���ڴ�����...");   

            // ����ProgressDialog ����ͼ��   
          //  m_pDialog.setIcon(R.drawable.img1);   

            // ����ProgressDialog �Ľ������Ƿ���ȷ   
            m_pDialog.setIndeterminate(false);   
              
            // ����ProgressDialog �Ƿ���԰��˻ذ���ȡ��   
            m_pDialog.setCancelable(true);   
              
            // ����ProgressDialog ��һ��Button   
            /*
            m_pDialog.setButton("ȷ��", new DialogInterface.OnClickListener() {   
                public void onClick(DialogInterface dialog, int i)   
                {   
                    //�����ȷ����ť��ȡ���Ի���   
                    
                }   
            });   
			*/
            // ��ProgressDialog��ʾ   
            m_pDialog.show();  
            Timer timer = new Timer();
            class MyTask extends java.util.TimerTask{ 
                int num=0;
                     public void run() {
                         num++;
                         System.out.println("  ������   "+num+" S");
                        if(num>=4)
                        {
                        	  m_pDialog.cancel(); 
                        }
                     }
                 }

            timer.schedule(new MyTask(), 1, 1000); //��1�����ִ�д�����,ÿ�μ��1��
    	}
	}
	
	
	
}