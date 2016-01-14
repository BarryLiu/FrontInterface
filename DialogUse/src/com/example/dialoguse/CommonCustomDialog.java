package com.example.dialoguse;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class CommonCustomDialog extends Dialog {
	private Context mContext;
	private View view;
	private LinearLayout llDialogRoot;//对话框根布局
	private LinearLayout llDialogBtn;//Dialog中按钮的外层布局
	private TextView tvTitle, tvContent;// 标题,内容
	private Button btnLeft, btnRight;// 取消,确定按钮
	private StateListDrawable stateListDrawableLeft, stateListDrawableRight;
	private static final int colorNormalId=R.color.white;
	private static final int colorPressedId=R.color.m29c741;
	private static final int colorUnavailableId=R.color.m146621;

	/**
	 * Dialog 构造器
	 * 
	 * @param context
	 *            上下文
	 * @param idNormal
	 * @param idPressed
	 * @param idUnavailable
	 */
	public CommonCustomDialog(Context context) {
		this(context, R.style.alarm_delete_dialog);
		// TODO Auto-generated constructor stub
	}

	public CommonCustomDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		mContext = context;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		view = LayoutInflater.from(mContext).inflate(
				R.layout.common_dialog_layout, null);
		setCanceledOnTouchOutside(false);// 默认点击外围不可取消
		initView(view);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(view);
	}

	// 一:设置文本内容

	public void setDialogTitle(String str) {
		tvTitle.setText(str);
	}

	public void setDialogTitle(int strId) {
		tvTitle.setText(strId);
	}

	public void setDialogContent(int conetId) {
		tvContent.setVisibility(View.VISIBLE);
		tvContent.setText(conetId);
	}

	public void setDialogContent(String content) {
		tvContent.setVisibility(View.VISIBLE);
		tvContent.setText(content);
	}

	public void setLeftBtnText(String content) {
		btnLeft.setText(content);
	}

	public void setLeftBtnText(int contentId) {
		btnLeft.setText(contentId);
	}

	public void setRightBtnText(String content) {
		btnRight.setText(content);
	}

	public void setRightBtnText(int contentId) {
		btnRight.setText(contentId);
	}

	// 二:设置字体大小

	/**
	 * 设置对话框标题字体大小
	 * 
	 * @param titleSizeId
	 */
	public void setDialogTitleSize(int titleSizeId) {
		// 设置字体尺寸最好用px,如果是dp或者sp,需要乘以density
		float mSize = mContext.getResources()
				.getDimensionPixelSize(titleSizeId);
		tvTitle.setTextSize(mSize);
	}

	/**
	 * 设置对话框内容字体大小
	 * 
	 * @param titleSizeId
	 */
	public void setDialogContentSize(int contentSizeId) {
		// 设置字体尺寸最好用px,如果是dp或者sp,需要乘以density
		float mSize = mContext.getResources().getDimension(
				contentSizeId);
		tvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,mSize);
	}

	/**
	 * 设置两个按钮的字体大小
	 * 
	 * @param titleSizeId
	 */
	public void setDialogBtnSize(int contentSizeId) {
		// 设置字体尺寸最好用px,如果是dp或者sp,需要乘以density
		float mSize = mContext.getResources().getDimension(
				contentSizeId);
		btnLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX,mSize);
		btnRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,mSize);
	}

	/**设置标题控件的内边距   注意:标题的边距与标题居中不能同时设置,否则出现居中后的偏移
	 * @param paddingLeftSizeId
	 * @param paddingTopSizeId
	 */
	public void setTitleTextPadding(int paddingLeftSizeId,int paddingTopSizeId)
	{
		int paddingLeft=mContext.getResources().getDimensionPixelSize(paddingLeftSizeId);
		int paddingTop=mContext.getResources().getDimensionPixelSize(paddingTopSizeId);
		tvTitle.setPadding(paddingLeft, paddingTop, 0, 0);
	}
	
	/**
	 * 标题居中    注意:标题的边距与标题居中不能同时设置,否则出现居中后的偏移
	 */
	public void setTitleInCenter()
	{
		tvTitle.setGravity(Gravity.CENTER);
	}
	
	/**
	 * 三:隐藏对话框内容
	 */
	public void hideDialogContent() {
		tvContent.setVisibility(View.GONE);
	}

	// 四:设置颜色

	/**
	 * 设置对话框的标题背景色
	 */
	public void setDialogTitleBacColor(int colorId) {
		int mColor = mContext.getResources().getColor(colorId);
		GradientDrawable bgShape = (GradientDrawable) tvTitle.getBackground();
		bgShape.setColor(mColor);
	}

	/**
	 * 设置内容的背景色
	 */
	public void setDialogContentBacColor(int colorId) {
		int mColor = mContext.getResources().getColor(colorId);
		tvContent.setBackgroundColor(mColor);
	}

	/**设置标题字体颜色
	 * @param colorId
	 */
	public void setTitleTextColor(int colorId)
	{
		tvTitle.setTextColor(colorId);
	}
	
	/**
	 * 设置两个按钮的字体颜色,0为不设置,默认的黑色
	 * 
	 * @param colorLeft
	 *            左边按钮的字体颜色
	 * @param ColorRight
	 *            右边按钮的字体颜色
	 */
	public void setDialogBtnTextColor(int colorLeft, int ColorRight) {
		if(colorLeft!=0)
		{
			int mColorLeft = mContext.getResources().getColor(colorLeft);
			btnLeft.setTextColor(mColorLeft);
		}
		if(ColorRight!=0)
		{
			int mColorRight = mContext.getResources().getColor(ColorRight);
			btnRight.setTextColor(mColorRight);
		}
	}

	// 五:设置点击事件

	/**
	 * 对话框左侧按钮的点击事件
	 * 
	 * @param listener
	 */
	public void setLeftBtnListener(android.view.View.OnClickListener listener) {
		this.dismiss();
		btnLeft.setOnClickListener(listener);
	}

	/**
	 * 对话框右侧按钮的点击事件
	 * 
	 * @param listener
	 */
	public void setRightBtnListener(android.view.View.OnClickListener listener) {
		this.dismiss();
		btnRight.setOnClickListener(listener);
	}

	//六:设置selector
	/**设置两个按钮的selector颜色
	 * @param context
	 *            上下文
	 * @param idNormal
	 *            正常状态的图片资源ID
	 * @param idPressed
	 *            按下状态的图片资源ID
	 * @param idUnavailable
	 *            不可用状态的图片资源ID
	 */
	public void setBtnSelector(int colorIdNormal, int colorIdPressed,
			int colorIdUnavailable) {

		// 左侧按钮------------------------------
		// 正常图片
		GradientDrawable drawableNormalL = (GradientDrawable) mContext
				.getResources().getDrawable(R.drawable.dialog_left_btn_bg);
		drawableNormalL.setColor(mContext.getResources()
				.getColor(colorIdNormal));
		// 按下图片
		GradientDrawable drawablePreL = (GradientDrawable) mContext
				.getResources().getDrawable(
						R.drawable.dialog_left_btn_bg_pressed);
		drawablePreL.setColor(mContext.getResources().getColor(colorIdPressed));
		// 失效图片
		GradientDrawable drawableUnavailableL = (GradientDrawable) mContext
				.getResources().getDrawable(
						R.drawable.dialog_left_btn_bg_unavailable);
		drawableUnavailableL.setColor(mContext.getResources().getColor(
				colorIdUnavailable));
		// 不可点击状态
		stateListDrawableLeft = new StateListDrawable();
		stateListDrawableLeft.addState(
				new int[] { -android.R.attr.state_enabled },
				drawableUnavailableL);
		// 点击状态
		stateListDrawableLeft.addState(
				new int[] { android.R.attr.state_pressed }, drawablePreL);
		// 普通状态
		stateListDrawableLeft.addState(new int[] {
				-android.R.attr.state_focused, -android.R.attr.state_pressed },
				drawableNormalL);
		btnLeft.setBackgroundDrawable(stateListDrawableLeft);

		// 右侧按钮----------------------------------
		// 正常图片
		GradientDrawable drawableNormalR = (GradientDrawable) mContext
				.getResources().getDrawable(R.drawable.dialog_right_btn_bg);
		drawableNormalR.setColor(mContext.getResources()
				.getColor(colorIdNormal));
		// 按下图片
		GradientDrawable drawablePreR = (GradientDrawable) mContext
				.getResources().getDrawable(
						R.drawable.dialog_right_btn_bg_pressed);
		drawablePreR.setColor(mContext.getResources().getColor(colorIdPressed));
		// 失效图片
		GradientDrawable drawableUnavailableR = (GradientDrawable) mContext
				.getResources().getDrawable(
						R.drawable.dialog_right_btn_bg_unavailable);
		drawableUnavailableR.setColor(mContext.getResources().getColor(
				colorIdUnavailable));
		// 不可点击状态
		stateListDrawableRight = new StateListDrawable();
		stateListDrawableRight.addState(
				new int[] { -android.R.attr.state_enabled },
				drawableUnavailableR);
		// 点击状态
		stateListDrawableRight.addState(
				new int[] { android.R.attr.state_pressed }, drawablePreR);
		// 正常状态
		stateListDrawableRight.addState(new int[] {
				-android.R.attr.state_focused, -android.R.attr.state_pressed },
				drawableNormalR);
		btnRight.setBackgroundDrawable(stateListDrawableRight);

	}

	
	/**
	 * 设置标题的高度
	 */
	public void setTilteHeigt(int sizeResId)
	{
		tvTitle.getLayoutParams().height=mContext.getResources().getDimensionPixelSize(sizeResId);
	}
	
	/**
	 * 设置按钮的高度
	 */
	public void setButtonHeigt(int sizeResId)
	{
		llDialogBtn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,mContext.getResources().getDimensionPixelSize(sizeResId)));
	}
	
	private void initView(View view) {
		// TODO Auto-generated method stub
		llDialogRoot=(LinearLayout)view.findViewById(R.id.llDialogRoot);
		tvTitle = (TextView) view.findViewById(R.id.tvTitle);
		tvContent = (TextView) view.findViewById(R.id.tvContent);
		llDialogBtn=(LinearLayout) view.findViewById(R.id.llDialogBtn);
		btnLeft = (Button) view.findViewById(R.id.btnLeft);
		btnRight = (Button) view.findViewById(R.id.btnRight);
		initDefaultColor();
	}

	@SuppressLint("ResourceAsColor")
	private void initDefaultColor() {
		// TODO Auto-generated method stub
		setDialogTitleBacColor(R.color.m29c741);// 标题背景默认绿色
		setDialogContentBacColor(R.color.white);// 内容背景默认白色
		// 左侧按钮默认的selector
		GradientDrawable drawableNormalL = (GradientDrawable) mContext
				.getResources().getDrawable(R.drawable.dialog_left_btn_bg);
		drawableNormalL.setColor(mContext.getResources()
				.getColor(colorNormalId));
		// 按下图片
		GradientDrawable drawablePreL = (GradientDrawable) mContext
				.getResources().getDrawable(
						R.drawable.dialog_left_btn_bg_pressed);
		drawablePreL
				.setColor(mContext.getResources().getColor(colorPressedId));
		// 失效图片
		GradientDrawable drawableUnavailableL = (GradientDrawable) mContext
				.getResources().getDrawable(
						R.drawable.dialog_left_btn_bg_unavailable);
		drawableUnavailableL.setColor(mContext.getResources().getColor(colorUnavailableId));
		stateListDrawableLeft = new StateListDrawable();
		// 不可点击状态
		stateListDrawableLeft.addState(
				new int[] { -android.R.attr.state_enabled },
				drawableUnavailableL);
		// 点击状态
		stateListDrawableLeft.addState(
				new int[] { android.R.attr.state_pressed }, drawablePreL);
		// 普通状态
		stateListDrawableLeft.addState(new int[] {
				-android.R.attr.state_focused, -android.R.attr.state_pressed },
				drawableNormalL);
		btnLeft.setBackgroundDrawable(stateListDrawableLeft);

		// 右侧按钮默认的--------------------------------------------------------
		GradientDrawable drawableNormalR = (GradientDrawable) mContext
				.getResources().getDrawable(R.drawable.dialog_right_btn_bg);
		drawableNormalR.setColor(mContext.getResources()
				.getColor(colorNormalId));
		// 按下图片
		GradientDrawable drawablePreR = (GradientDrawable) mContext
				.getResources().getDrawable(
						R.drawable.dialog_right_btn_bg_pressed);
		drawablePreR
				.setColor(mContext.getResources().getColor(colorPressedId));
		// 失效图片
		GradientDrawable drawableUnavailableR = (GradientDrawable) mContext
				.getResources().getDrawable(
						R.drawable.dialog_right_btn_bg_unavailable);
		drawableUnavailableR.setColor(mContext.getResources().getColor(colorUnavailableId));
		stateListDrawableRight = new StateListDrawable();
		// 不可点击状态
		stateListDrawableRight.addState(
				new int[] { -android.R.attr.state_enabled },
				drawableUnavailableR);
		// 点击状态
		stateListDrawableRight.addState(
				new int[] { android.R.attr.state_pressed }, drawablePreR);
		// 普通状态
		stateListDrawableRight.addState(new int[] {
				-android.R.attr.state_focused, -android.R.attr.state_pressed },
				drawableNormalR);
		btnRight.setBackgroundDrawable(stateListDrawableRight);
	}

}
