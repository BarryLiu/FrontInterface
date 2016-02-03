package com.zsj.list_demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zsj.list_demo.MyLetterView.OnTouchingLetterChangedListener;

public class MainActivity extends ActionBarActivity {

	private ListView list_friends;
	ImageView iv_msg_tips;
	TextView tv_new_name;
	LinearLayout layout_new;// 新朋友
	LinearLayout layout_near;// 附近的人
	List<User> friends;
	MyLetterView right_letter;
	private UserFriendAdapter userAdapter;

	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list_friends = (ListView) findViewById(R.id.list_friends);
		friends = new ArrayList<User>();
		pinyinComparator = new PinyinComparator();
		characterParser = CharacterParser.getInstance();
		
		initListView();

		right_letter = (MyLetterView) findViewById(R.id.right_letter);
		right_letter.setOnTouchingLetterChangedListener(new LetterListViewListener());
	}
	
	
	private void filledDatas(List<User> user) {
		for (User sortModel : user) {
			String pinyin = characterParser.getSelling(sortModel.getUserName());
			String sortString = pinyin.substring(0, 1).toUpperCase();
			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}
			friends.add(sortModel);
		}

		Collections.sort(friends, pinyinComparator);
	}

	
	private void queryDatas(){
		
		CustomApplcation.getInstance().setContactList(CollectionUtils.list2map(getUsers()));
		
		Map<String,User> users = CustomApplcation.getInstance().getContactList();
		//组装新的User
		filledDatas(CollectionUtils.map2list(users));
		if(userAdapter==null){
			userAdapter = new UserFriendAdapter(this, friends);
			list_friends.setAdapter(userAdapter);
		}else{
			userAdapter.notifyDataSetChanged();
		}
	}
	
	
	private void initListView() {
		RelativeLayout headView = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.include_new_friend, null);
		iv_msg_tips = (ImageView) headView.findViewById(R.id.iv_msg_tips);
		layout_new = (LinearLayout) headView.findViewById(R.id.layout_new);
		layout_near = (LinearLayout) headView.findViewById(R.id.layout_near);
		layout_new.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		layout_near.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		list_friends.addHeaderView(headView);
//		userAdapter = new UserFriendAdapter(this, friends);
//		list_friends.setAdapter(userAdapter);
		queryDatas();

	}

	private List<User> getUsers() {
		List<User> list = new ArrayList<User>();
		User user = new User();
		user.setUserName("卡哇伊");
		list.add(user);

		User user1 = new User();
		user1.setUserName("liming");
		list.add(user1);

		User user2 = new User();
		user2.setUserName("张里");
		list.add(user2);

		User user3 = new User();
		user3.setUserName("chat");
		list.add(user3);

		User user4 = new User();
		user4.setUserName("hit");
		list.add(user4);

		User user5 = new User();
		user5.setUserName("wechat");
		list.add(user5);

		User user6 = new User();
		user6.setUserName("好小子");
		list.add(user6);

		User user7 = new User();
		user7.setUserName("ala");
		list.add(user7);

		User user8 = new User();
		user8.setUserName("潘仔");
		list.add(user8);

		return list;
	}

	private class LetterListViewListener implements
			OnTouchingLetterChangedListener {

		@Override
		public void onTouchingLetterChanged(String s) {
			// 该字母首次出现的位置
			int position = userAdapter.getPositionForSection(s.charAt(0));
			if (position != -1) {
				list_friends.setSelection(position);
			}
		}
	}
}
