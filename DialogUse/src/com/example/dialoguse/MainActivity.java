package com.example.dialoguse;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;


public class MainActivity extends Activity {
private Context mContext;
private CommonCustomDialog dialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mContext=this;
    }
    
    public void showDefaultDialog(View v)
    {
    	dialog1 = new CommonCustomDialog(mContext);
    	dialog1.setTitleInCenter();
    	dialog1.setLeftBtnListener(new MyLeftBtnClickListener(dialog1));
    	dialog1.setRightBtnListener(new MyRightBtnClickListener(dialog1));
    	dialog1.show();
    }

    @SuppressLint("ResourceAsColor") 
    public void showDefinedDialog(View v)
    {
    	dialog1 = new CommonCustomDialog(mContext);
    	dialog1.setTitleInCenter();
    	dialog1.setDialogContent("xxxxxxxxxx");
    	dialog1.setDialogTitleSize(R.dimen.px30);
    	dialog1.setDialogContentSize(R.dimen.px17);
    	dialog1.setLeftBtnText("确定");
    	dialog1.setRightBtnText("取消");
    	dialog1.setDialogTitleBacColor(R.color.black); //将标题背景色改为黑色
    	dialog1.setDialogContentBacColor(R.color.m146621); //将内容背景色改为墨绿色
    	dialog1.setDialogBtnTextColor(R.color.m146621,R.color.black);//设置左右两个按钮的文本颜色
    	dialog1.setLeftBtnListener(new MyLeftBtnClickListener(dialog1));
    	dialog1.setRightBtnListener(new MyRightBtnClickListener(dialog1));
    	dialog1.show();//Dialog要想居中显示,需要在Activity与Dialog中都去掉Title
    }

    public void showSelecrotDefinedDialog(View v)
    {
    	dialog1 = new CommonCustomDialog(mContext);
    	dialog1.setTitleInCenter();
    	dialog1.setBtnSelector(R.color.mediumvioletred, R.color.moccasin, R.color.moccasin);
    	dialog1.setLeftBtnListener(new MyLeftBtnClickListener(dialog1));
    	dialog1.setRightBtnListener(new MyRightBtnClickListener(dialog1));
    	dialog1.show();
    	//设置宽度要这样设置
    	dialog1.getWindow().setLayout(mContext.getResources().getDimensionPixelSize(R.dimen.x535), LayoutParams.WRAP_CONTENT);
    }
    
    private class MyLeftBtnClickListener implements OnClickListener
    {
    	private CommonCustomDialog dialog;
    	
		public MyLeftBtnClickListener(CommonCustomDialog dialog) {
			super();
			this.dialog = dialog;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(mContext, "点中了左边的按钮!", Toast.LENGTH_SHORT).show();
			dialog.dismiss();
		}
    }
    
    private class MyRightBtnClickListener implements OnClickListener
    {
    	private CommonCustomDialog dialog;
    	
    	public MyRightBtnClickListener(CommonCustomDialog dialog) {
			super();
			this.dialog = dialog;
		}

		@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		Toast.makeText(mContext, "点中了右边的按钮!", Toast.LENGTH_SHORT).show();
    		dialog.dismiss();
    	}
    }
}
