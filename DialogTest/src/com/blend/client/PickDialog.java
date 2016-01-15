package com.blend.client;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PickDialog extends Dialog{
	private Context context;
	private String title;
	private LinearLayout blend_dialog_preview;
	private ListView blend_dialog_nextview;
	private ArrayList<String> items=new ArrayList<String>();
	private PickDialogListener pickDialogListener;
	DialogListViewAdapter adapter;

	public PickDialog(Context context,String title,PickDialogListener pickDialogListener) {
		super(context, R.style.blend_theme_dialog);
		this.context=context;
		this.title=title;
		this.pickDialogListener=pickDialogListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LayoutInflater inflater =LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.blend_dialog_preview_layout, null);

		TextView titleTextview = (TextView) layout.findViewById(R.id.blend_dialog_title);
		titleTextview.setText(title);
		TextView cancleTextView = (TextView) layout.findViewById(R.id.blend_dialog_cancle_btn);
		cancleTextView.setText(context.getResources().getString(R.string.no));
		blend_dialog_preview = (LinearLayout) layout.findViewById(R.id.blend_dialog_preview);
		blend_dialog_nextview = (ListView) layout.findViewById(R.id.blend_dialog_nextview);
		

		this.setCanceledOnTouchOutside(true);
		this.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
		cancleTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dismiss();
				
			}

			
		});
		
		
		this.setContentView(layout);
	}
	
	public void initListViewData(ArrayList<String> list){
		items=list;
		blend_dialog_preview.setVisibility(View.GONE);
		blend_dialog_nextview.setVisibility(View.VISIBLE);
		adapter = new DialogListViewAdapter(context, list);
		blend_dialog_nextview.setAdapter(adapter);
		blend_dialog_nextview.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				dismiss();
				if(pickDialogListener!=null){
					pickDialogListener.onListItemClick(position, items.get(position));
				}
			}
		});
	}




}
