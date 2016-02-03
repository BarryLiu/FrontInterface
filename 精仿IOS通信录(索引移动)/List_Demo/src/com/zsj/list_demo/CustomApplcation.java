package com.zsj.list_demo;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;

/**
 * 自定义全局Applcation类
 * @ClassName: CustomApplcation
 * @Description: TODO
 * @author smile
 * @date 2014-5-19 下午3:25:00
 */
public class CustomApplcation extends Application {

	public static CustomApplcation mInstance;


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 是否开启debug模式--默认开启状态
		mInstance = this;
	}

	public static CustomApplcation getInstance() {
		return mInstance;
	}

	private Map<String, User> contactList = new HashMap<String, User>();

	/**
	 * 获取内存中好友user list
	 * 
	 * @return
	 */
	public Map<String, User> getContactList() {
		return contactList;
	}

	/**
	 * 设置好友user list到内存中
	 * @param contactList
	 */
	public void setContactList(Map<String, User> contactList) {
		if (this.contactList != null) {
			this.contactList.clear();
		}
		this.contactList = contactList;
	}


}
