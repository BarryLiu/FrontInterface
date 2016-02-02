package com.example.expressiondemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AExpressionLib {
	private String packageName; //表情包名
	private int packageIcon; //表情包图标
	private List<Map.Entry<String, Integer>> expressions;// <编码，图标>
	
	/**
	 * @return 表情匹配正则表达式 
	 */
	public abstract String getMatchReg();
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getPackageIcon() {
		return packageIcon;
	}
	public void setPackageIcon(int packageIcon) {
		this.packageIcon = packageIcon;
	}
	
	public List<Map.Entry<String, Integer>> getExpressions() {
		return expressions;
	}
	
	public int getImgId(String name) {
		for(Map.Entry<String, Integer> entry : expressions) {
			if(entry.getKey().equals(name))
				return entry.getValue();
		}
		
		return -1;
	}
	
	public void setExpressions(Map<String, Integer> expressions) {
		this.expressions =
				new ArrayList<Map.Entry<String, Integer>>(expressions.entrySet());
		
		Collections.sort(this.expressions, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> lhs, Entry<String, Integer> rhs) {
				return lhs.getKey().compareTo(rhs.getKey());
			}

		});
	}
}
