package com.geniuseoe2012.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.geniuseoe2012.dialog.MultiChoicDialog;
import com.geniuseoe2012.dialog.SingleChoiceDialog;
import com.geniuseoe2012.popwindow.MultiChoicePopWindow;
import com.geniuseoe2012.popwindow.SingleChoicePopWindow;

 
public class CustomDialogActivity extends Activity implements OnClickListener {

	private View mRootView;
	private Button mBtn1;
	private Button mBtn2;
	private Button mBtn3;
	private Button mBtn4;

	private SingleChoicePopWindow mSingleChoicePopWindow;
	private MultiChoicePopWindow mMultiChoicePopWindow;
	private SingleChoiceDialog mSingleChoiceDialog;
	private MultiChoicDialog mMultiChoicDialog;

	private List<String> mSingleDataList;
	private List<String> mMultiDataList;

	private Context mContext;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mContext = this;

		initView();

		initData();
	}

	public void initView() {
		mBtn1 = (Button) findViewById(R.id.button1);
		mBtn1.setOnClickListener(this);

		mBtn2 = (Button) findViewById(R.id.button2);
		mBtn2.setOnClickListener(this);

		mBtn3 = (Button) findViewById(R.id.button3);
		mBtn3.setOnClickListener(this);

		mBtn4 = (Button) findViewById(R.id.button4);
		mBtn4.setOnClickListener(this);

		mRootView = findViewById(R.id.rootView);
	}

	private final static int COUNT = 3;

	public void initData() {
		mSingleDataList = new ArrayList<String>();
		mMultiDataList = new ArrayList<String>();
		boolean booleans[] = new boolean[COUNT * 5];

		for (int i = 0; i < COUNT; i++) {
			String string1 = "geniuseoe2012 -->" + i;
			mSingleDataList.add(string1);
		}

		for (int i = 0; i < COUNT * 2; i++) {
			String string2 = "talent -->" + i;
			mMultiDataList.add(string2);
		}

		initPopWindow(booleans);

		initDialog(booleans);

	}

	public void initPopWindow(boolean[] booleans) {
		mSingleChoicePopWindow = new SingleChoicePopWindow(this, mRootView,
				mSingleDataList);

		mSingleChoicePopWindow.setTitle("genius single title");
		mSingleChoicePopWindow.setOnOKButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int selItem = mSingleChoicePopWindow.getSelectItem();
				Toast.makeText(mContext, "selItem = " + selItem,
						Toast.LENGTH_SHORT).show();
			}
		});

		mMultiChoicePopWindow = new MultiChoicePopWindow(this, mRootView,
				mMultiDataList, booleans);
		mMultiChoicePopWindow.setTitle("genius multi title");
		mMultiChoicePopWindow.setOnOKButtonListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean[] selItems = mMultiChoicePopWindow.getSelectItem();
				int size = selItems.length;
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < size; i++) {
					if (selItems[i]) {
						stringBuffer.append(i + " ");
					}

				}

				Toast.makeText(mContext,
						"selItems = " + stringBuffer.toString(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void initDialog(boolean[] booleans) {
		mSingleChoiceDialog = new SingleChoiceDialog(this, mSingleDataList);
		mSingleChoiceDialog.setTitle("genius single title");
		mSingleChoiceDialog
				.setOnOKButtonListener(new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						int selItem = mSingleChoiceDialog.getSelectItem();
						Toast.makeText(mContext, "selItem = " + selItem,
								Toast.LENGTH_SHORT).show();
					}

				});

		mMultiChoicDialog = new MultiChoicDialog(this, mMultiDataList, booleans);
		mMultiChoicDialog.setTitle("genius multi title");
		mMultiChoicDialog
				.setOnOKButtonListener(new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						boolean[] selItems = mMultiChoicDialog.getSelectItem();
						int size = selItems.length;
						StringBuffer stringBuffer = new StringBuffer();
						for (int i = 0; i < size; i++) {
							if (selItems[i]) {
								stringBuffer.append(i + " ");
							}

						}

						Toast.makeText(mContext,
								"selItems = " + stringBuffer.toString(),
								Toast.LENGTH_SHORT).show();
					}

				});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			showSingleChoiceWindow();
			break;
		case R.id.button2:
			showMultiChoiceWindow();
			break;
		case R.id.button3:
			showSingleChoiceDialog();
			break;
		case R.id.button4:
			showMultiChoiceDialog();
			break;
		}
	}

	public void showSingleChoiceWindow() {
		mSingleChoicePopWindow.show(true);

	}

	public void showMultiChoiceWindow() {
		mMultiChoicePopWindow.show(true);

	}

	public void showSingleChoiceDialog() {
		mSingleChoiceDialog.show();
	}

	public void showMultiChoiceDialog() {

		mMultiChoicDialog.show();
	}
}
