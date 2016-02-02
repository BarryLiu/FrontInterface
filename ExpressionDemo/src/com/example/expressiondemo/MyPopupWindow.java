package com.example.expressiondemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

@SuppressLint("ViewConstructor")
public class MyPopupWindow extends PopupWindow {
	private Button sendButton;
	private EditText ev;
	private View mView;
	private String text = "";
	private ArrayList<GridView> grids;
	private int[] expressionImages;
	private String[] expressionImageNames;
	private int[] expressionImages1;
	private String[] expressionImageNames1;
	private int[] expressionImages2;
	private String[] expressionImageNames2;
	private ImageButton faceButton;
	private ViewPager viewPager;
	private GridView gView1;
	private GridView gView2;
	private GridView gView3;
	private LinearLayout page_select;
	private ImageView page0;
	private ImageView page1;
	private ImageView page2;
	private Context ctx;

	public MyPopupWindow(Context context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = inflater.inflate(R.layout.comment, null);
		this.ctx  = context;
		sendButton = (Button) mView.findViewById(R.id.sendButton);
		sendButton.setOnClickListener(itemsOnClick);
		faceButton = (ImageButton) mView.findViewById(R.id.cam_comment_emo);
		faceButton.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("static-access")
			public void onClick(View v) {
//				faceButton.setVisibility(faceButton.GONE);
				viewPager.setVisibility(viewPager.VISIBLE);
				initViewPager();
				page_select.setVisibility(page_select.VISIBLE);
			}
		});
		page_select = (LinearLayout) mView.findViewById(R.id.page_select);
		page0 = (ImageView) mView.findViewById(R.id.page0_select);
		page1 = (ImageView) mView.findViewById(R.id.page1_select);
		page2 = (ImageView) mView.findViewById(R.id.page2_select);
		// 引入表情
		expressionImages = Expressions.expressionImgs;
		expressionImageNames = Expressions.expressionImgNames;
		expressionImages1 = Expressions.expressionImgs1;
		expressionImageNames1 = Expressions.expressionImgNames1;
		expressionImages2 = Expressions.expressionImgs2;
		expressionImageNames2 = Expressions.expressionImgNames2;

		viewPager = (ViewPager) mView.findViewById(R.id.viewpager);

		ev = (EditText) mView.findViewById(R.id.commentText);
		ev.setFocusable(true);
		ev.setFocusableInTouchMode(true);
		ev.requestFocus();
		text = ev.getText().toString();

		this.setContentView(mView);
		// 设置弹出框的宽
		this.setWidth(LayoutParams.FILL_PARENT);
		// 设置弹出的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置窗体可点击
		this.setFocusable(true);
		// 设置弹出的效果
		// this.setAnimationStyle(R.style.AppTheme);
		// 设置弹出背景为半透明
		// ColorDrawable dw = new ColorDrawable(0xb0000000);
		// this.setBackgroundDrawable(dw);
		// 设置窗体弹出需要软件盘
		this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
		// 设置模式
		this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 设置键盘延迟自动弹出
		InputMethodManager inputManager = (InputMethodManager) ev.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(ev, 0);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				InputMethodManager inputManager = (InputMethodManager) ev
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				inputManager.showSoftInput(ev, 0);

			}

		}, 998);

		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int height = mView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}
	@SuppressWarnings("unused")
	private void initViewPager() {
		LayoutInflater inflater = LayoutInflater.from(ctx);
		grids = new ArrayList<GridView>();
		gView1 = (GridView) inflater.inflate(R.layout.grid1, null);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		//生成24个表情
		for (int i = 0; i < 24; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", expressionImages[i]);
			listItems.add(listItem);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(ctx, listItems,
				R.layout.singleexpression, new String[] { "image" },
				new int[] { R.id.image });
		gView1.setAdapter(simpleAdapter);
		gView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Bitmap bitmap = null;
				bitmap = BitmapFactory.decodeResource(ctx.getResources(),
						expressionImages[arg2 % expressionImages.length]);
				ImageSpan imageSpan = new ImageSpan(ctx, bitmap);
				SpannableString spannableString = new SpannableString(
						expressionImageNames[arg2].substring(4,
								expressionImageNames[arg2].length() - 1));
				spannableString.setSpan(imageSpan, 0,
						expressionImageNames[arg2].length() - 5,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// 编辑框设置数据
				ev.append(spannableString);
				System.out.println("edit的内容 = " + spannableString);
			}
		});
		grids.add(gView1);

		gView2 = (GridView) inflater.inflate(R.layout.grid2, null);
		grids.add(gView2);

		gView3 = (GridView) inflater.inflate(R.layout.grid3, null);
		grids.add(gView3);
		System.out.println("GridView的长度 = " + grids.size());

		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return grids.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(grids.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(grids.get(position));
				return grids.get(position);
			}
		};

		viewPager.setAdapter(mPagerAdapter);
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}
	// ** 指引页面改监听器 */
		class GuidePageChangeListener implements OnPageChangeListener {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				System.out.println("页面滚动" + arg0);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				System.out.println("换页了" + arg0);
			}

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					page0.setImageDrawable(ctx.getResources().getDrawable(
							R.drawable.page_focused));
					page1.setImageDrawable(ctx.getResources().getDrawable(
							R.drawable.page_unfocused));

					break;
				case 1:
					page1.setImageDrawable(ctx.getResources().getDrawable(
							R.drawable.page_focused));
					page0.setImageDrawable(ctx.getResources().getDrawable(
							R.drawable.page_unfocused));
					page2.setImageDrawable(ctx.getResources().getDrawable(
							R.drawable.page_unfocused));
					List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
					// 生成24个表情
					for (int i = 0; i < 24; i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("image", expressionImages1[i]);
						listItems.add(listItem);
					}

					SimpleAdapter simpleAdapter = new SimpleAdapter(ctx,
							listItems, R.layout.singleexpression,
							new String[] { "image" }, new int[] { R.id.image });
					gView2.setAdapter(simpleAdapter);
					gView2.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							Bitmap bitmap = null;
							bitmap = BitmapFactory.decodeResource(ctx.getResources(),
									expressionImages1[arg2
											% expressionImages1.length]);
							ImageSpan imageSpan = new ImageSpan(ctx, bitmap);
							SpannableString spannableString = new SpannableString(
									expressionImageNames1[arg2]
											.substring(4,
													expressionImageNames1[arg2]
															.length() - 1));
							spannableString.setSpan(imageSpan, 0,
									expressionImageNames1[arg2].length() - 5,
									Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
							// 编辑框设置数据
							ev.append(spannableString);
							System.out.println("edit的内容 = " + spannableString);
						}
					});
					break;
				case 2:
					page2.setImageDrawable(ctx.getResources().getDrawable(
							R.drawable.page_focused));
					page1.setImageDrawable(ctx.getResources().getDrawable(
							R.drawable.page_unfocused));
					page0.setImageDrawable(ctx.getResources().getDrawable(
							R.drawable.page_unfocused));
					List<Map<String, Object>> listItems1 = new ArrayList<Map<String, Object>>();
					// 生成24个表情
					for (int i = 0; i < 24; i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("image", expressionImages2[i]);
						listItems1.add(listItem);
					}

					SimpleAdapter simpleAdapter1 = new SimpleAdapter(ctx,
							listItems1, R.layout.singleexpression,
							new String[] { "image" }, new int[] { R.id.image });
					gView3.setAdapter(simpleAdapter1);
					gView3.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							Bitmap bitmap = null;
							bitmap = BitmapFactory.decodeResource(ctx.getResources(),
									expressionImages2[arg2
											% expressionImages2.length]);
							ImageSpan imageSpan = new ImageSpan(ctx, bitmap);
							SpannableString spannableString = new SpannableString(
									expressionImageNames2[arg2]
											.substring(4,
													expressionImageNames2[arg2]
															.length() - 1));
							spannableString.setSpan(imageSpan, 0,
									expressionImageNames2[arg2].length() - 5,
									Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
							// 编辑框设置数据
							ev.append(spannableString);
							System.out.println("edit的内容 = " + spannableString+"");
						}
					});
					break;
				}
			}
		}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return ev.getText().toString();
	}

	public void setHint(String hint) {
		ev.setHint(hint);
	}

}
