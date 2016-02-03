package com.zsj.list_demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionUtils {

	public static boolean isNotNull(Collection<?> collection) {
		if (collection != null && collection.size() > 0) {
			return true;
		}
		return false;
	}
	
	/** list转map
	 *  以用户名为key
	  * @return Map<String,BmobChatUser>
	  * @throws
	  */
	public static Map<String,User> list2map(List<User> users){
		Map<String,User> friends = new HashMap<String, User>();
		for(User user : users){
			friends.put(user.getUserName(), user);
		}
		return friends;
	}
	
	
	/** map转list
	  * @Title: map2list
	  * @return List<BmobChatUser>
	  * @throws
	  */
	public static List<User> map2list(Map<String,User> maps){
		List<User> users = new ArrayList<User>();
		Iterator<Entry<String, User>> iterator = maps.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, User> entry = iterator.next();
			users.add(entry.getValue());
		}
		return users;
	}
}
