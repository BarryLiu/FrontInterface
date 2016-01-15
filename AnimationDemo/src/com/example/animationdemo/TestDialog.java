package com.example.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class TestDialog extends Dialog implements
		android.view.View.OnClickListener {

	View root;
	private int itemHeight;
	private int itemWidth;
	private int viewY;
	private int viewX;
	private int screenWidth;
	private int screenHeight;
	private static final int SOHWTIME = 600;
	Handler handler;

	LinearLayout out_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.height = ViewGroup.LayoutParams.MATCH_PARENT;
		params.width = ViewGroup.LayoutParams.MATCH_PARENT;
		getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);
	}

	@SuppressWarnings("deprecation")
	public TestDialog(Context context, int[] location, View convertView) {
		super(context, R.style.dialog);
		root = LayoutInflater.from(context).inflate(R.layout.dialog_item, null);
		handler = new Handler();
		out_content = (LinearLayout) root.findViewById(R.id.out);
		out_content.setOnClickListener(this);
		this.setCanceledOnTouchOutside(false);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		detail_dialog_in = (TextView) root.findViewById(R.id.dialog_tv);
		detail_dialog_in.setTextSize(40);
		detail_dialog_in.setBackgroundColor(Color.GREEN);
		setContentView(root);
		WindowManager m = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		LinearLayout.LayoutParams p = (android.widget.LinearLayout.LayoutParams) detail_dialog_in
				.getLayoutParams();
		screenWidth = d.getWidth();
		screenHeight = d.getHeight();
		p.height = (int) (d.getHeight() * 0.80); // 高度设置为屏幕的0.75
		p.width = (int) (d.getWidth() * 0.85); // 宽度设置为屏幕的0.8
		detail_dialog_in.setLayoutParams(p);
		//
		viewX = location[0];
		viewY = location[1];
		itemWidth = convertView.getWidth();
		itemHeight = convertView.getHeight();
		initAnima();
	}

	TextView detail_dialog_in;
	private boolean isOutAnimaEnd;

	private void initAnima() {
		isOutAnimaEnd = false;
		ObjectAnimator a = ObjectAnimator.ofFloat(detail_dialog_in,
				"rotationY", 180, 360);
		// 动画坐标:屏幕中心点开始,view坐标:屏幕左上角开始,坐标点是view左上角
		ObjectAnimator b = ObjectAnimator.ofFloat(detail_dialog_in,
				"translationX", (viewX - (screenWidth / 2)) + (itemWidth / 2),
				0);
		ObjectAnimator b2 = ObjectAnimator.ofFloat(detail_dialog_in,
				"translationY",
				(viewY - (screenHeight / 2)) + (itemHeight / 2), 0);

		ObjectAnimator c = ObjectAnimator.ofFloat(detail_dialog_in, "alpha", 0,
				1);
		ObjectAnimator d = ObjectAnimator.ofFloat(detail_dialog_in, "scaleX",
				0.1f, 0.5f, 1);
		ObjectAnimator e = ObjectAnimator.ofFloat(detail_dialog_in, "scaleY",
				0.05f, 0.5f, 1);
		AnimatorSet set = new AnimatorSet();
		set.setDuration(SOHWTIME);
		set.setInterpolator(new LinearInterpolator());
		set.playTogether(a, b, b2, c, d, e);
		set.start();

	}

	public void outAnima() {
		// "scaleX", 1, 1.5f, 2: 放大
		// if (UpdateViewUtil.isDialogOut) {
		// UpdateViewUtil.isDialogOut = false;
		if (!isOutAnimaEnd) {
			isOutAnimaEnd = true;
			ObjectAnimator a = ObjectAnimator.ofFloat(detail_dialog_in,
					"rotationY", 360, 180);

			// 动画坐标:屏幕中心点开始,view坐标:屏幕左上角开始,坐标点是view左上角
			ObjectAnimator b = ObjectAnimator.ofFloat(detail_dialog_in,
					"translationX", 0, (viewX - (screenWidth / 2))
							+ (itemWidth / 2));
			ObjectAnimator b2 = ObjectAnimator.ofFloat(detail_dialog_in,
					"translationY", 0, (viewY - (screenHeight / 2))
							+ (itemHeight / 2));

			ObjectAnimator c = ObjectAnimator.ofFloat(detail_dialog_in,
					"alpha", 1, 0);
			ObjectAnimator d = ObjectAnimator.ofFloat(detail_dialog_in,
					"scaleX", 1, 0.5f, 0.1f);
			ObjectAnimator e = ObjectAnimator.ofFloat(detail_dialog_in,
					"scaleY", 1, 0.5f, 0.05f);
			AnimatorSet set = new AnimatorSet();
			set.setDuration(SOHWTIME);
			set.setInterpolator(new LinearInterpolator());
			set.playTogether(a, b, b2, c, d, e);
			set.start();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mdismissDialog();
				}
			}, SOHWTIME);
		}
	}

	public void mdismissDialog() {
		this.dismiss();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.out:
			outAnima();
			break;
		}
	};
}
